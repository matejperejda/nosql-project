package runner;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author <a href="mailto:mat.per.vt@gmail.com">Matej Perejda</a>
 */
@Repository
public interface StudentRepository extends MongoRepository<MongoStudent, String> {

   // 1. uloha: získanie iba mien a priezvisk (projekcia) na základe akademického titulu
   List<MenoAPriezvisko> findBySkratkaakadtitul(String skratkaakadtitul);

   // 2. uloha: zobrazenie všetkých študentov, ktorí študujú v danom roku daný študijný program
   @Query("{'$and' : [{'studium.zaciatokStudia.rok' : {'$lte' : ?0}}, {'studium.koniecStudia.rok' : {'$gte' : ?0} }, {'studium.studijnyProgram.skratka' : {'$regex' : '?1$'}}] }")
   List<MongoStudent> findByRokAndStudijnyProgram(int rokStudia, String skratkaStudijnehoProgramu);
}
