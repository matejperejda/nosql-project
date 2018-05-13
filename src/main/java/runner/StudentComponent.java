package runner;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static sk.gursky.nosql.Convertors.getJSONFromObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;

import sk.gursky.nosql.aislike.DaoFactory;
import sk.gursky.nosql.aislike.StudentDao;
import sk.gursky.nosql.aislike.entity.Student;

/**
 * @author <a href="mailto:mat.per.vt@gmail.com">Matej Perejda</a>
 */
@Component
public class StudentComponent implements CommandLineRunner {
   private static final Logger logger = LoggerFactory.getLogger(StudentComponent.class);

   @Autowired
   private StudentRepository repository;

   @Autowired
   MongoTemplate mongoTemplate;

   @Autowired
   private MongoOperations mongoOperations;

   private static StudentDao studentDao;

   private long mongoStudentsCount;

   @PostConstruct
   public void init() {
      studentDao = DaoFactory.INSTANCE.getStudentDao();
   }

   @Override
   public void run(final String... args) throws Exception {
      mySqlToMongo();
      getStudentsCount();

      // 1. uloha
      prvaUloha();

      // 2. uloha
      druhaUloha();

      // 3. uloha
      tretiaUloha();

      // 4. uloha
      stvrtaUloha();
   }

   public void stvrtaUloha() {
      Aggregation aggregation = newAggregation(
            unwind("studium"),
            group(Fields.fields("studium.studijnyProgram.skratka")).count().as("total"),
            project("total").and("skratka").previousOperation(),
            sort(Direction.DESC, "total")
      ); // funguje grupovanie skratiek a vypis poctu ich vysktov

      // TODO: nie je dokoncene groupovanie rokov

      AggregationResults<StudentAggregation> output
            = mongoTemplate.aggregate(aggregation, MongoStudent.class, StudentAggregation.class);
      List<StudentAggregation> result = output.getMappedResults();
      logger.info(result.toString());
   }

   public void tretiaUloha() {
      long start = System.currentTimeMillis();

      druhaUloha();
      logger.info("Time: " + (System.currentTimeMillis() - start));

      createIndex(MongoStudijnyProgram.class, "id", Direction.DESC);
      start = System.currentTimeMillis();
      druhaUloha();
      logger.info("Time (index): " + (System.currentTimeMillis() - start));
   }

   public void druhaUloha() {
      int rok = 2000;
      String skratkaStudijnehoProgramu = "M";
      List<MongoStudent> studentiByRokAndStudijnyProgram = repository.findByRokAndStudijnyProgram(rok, skratkaStudijnehoProgramu);
      logger.info("\nŠtudenti by rok <" + rok + ">"
            + " a študijný program <" + skratkaStudijnehoProgramu + ">:\n"
            + studentiByRokAndStudijnyProgram
            + "\nPočet: " + studentiByRokAndStudijnyProgram.size()
            + " z " + mongoStudentsCount);
   }

   public void prvaUloha() {
      String skratka = "Mgr.";
      List<MenoAPriezvisko> menaAPriezviskaByAkademickyTitul = repository.findBySkratkaakadtitul(skratka);

      logger.info("\nMená a priezviská by akademický titul <" + skratka + ">: \n"
            + menaAPriezviskaByAkademickyTitul
            + "\nPočet: " + menaAPriezviskaByAkademickyTitul.size()
            + " z " + mongoStudentsCount);
   }

   public void createIndex(Class<? extends Object> className, String attribute, Direction direction) {
      mongoOperations.indexOps(className).ensureIndex(new Index().on(attribute, direction));
   }

   private void mySqlToMongo() {
      repository.deleteAll();

      List<Student> students = studentDao.getAll();
      List<MongoStudent> mongoStudents = students.stream().map(s -> new MongoStudent(s)).collect(Collectors.toList());
      repository.saveAll(mongoStudents);
   }

   /**** METODY PRE JEDNODUCHE OTESTOVANIE FUNKCNOSTI MONGO REPOZITARA ****/

   private void saveSomeAndPrint() {
      repository.deleteAll();

      MongoStudent student = new MongoStudent();
      student.setMeno("Janko");
      student.setPriezvisko("Hrasko");
      student.setKodpohlavie("M".charAt(0));
      student.setSkratkaakadtitul("Mgr.");
      student.setStudium(null);
      repository.save(student);

      for (MongoStudent st : repository.findAll()) {
         logger.info(st.toString());
      }
   }

   private void findAll() throws JsonProcessingException {
      List<MongoStudent> mongoStudents = repository.findAll();
      logger.info("Students in dbs: " + getJSONFromObject(mongoStudents));
      logger.info("Students count: " + mongoStudents.size());
   }

   private void findById() {
      MongoStudent thirdStudent = repository.findAll().get(2);
      logger.info("Found by ID " + thirdStudent.getId() + ": " + repository.findById(thirdStudent.getId()));
   }

   private void getStudentsCount() {
      mongoStudentsCount = repository.findAll().size();
   }

}
