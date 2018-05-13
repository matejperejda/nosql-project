package runner;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import sk.gursky.nosql.aislike.entity.Student;
import sk.gursky.nosql.aislike.entity.Studium;

/**
 * @author <a href="mailto:mat.per.vt@gmail.com">Matej Perejda</a>
 */
@Document(collection = "students")
public class MongoStudent implements Serializable {
   private static final long serialVersionUID = 8600653183336129451L;

   private String id;
   private String meno;
   private String priezvisko;
   private char kodpohlavie;
   private String skratkaakadtitul;
   @Field("studium")
   private List<MongoStudium> studium = new ArrayList<MongoStudium>();

   public MongoStudent() {
      // empty constructor
   }

   public MongoStudent(Student student) {
      this.id = String.valueOf(student.getId());
      this.meno = student.getMeno();
      this.priezvisko = student.getPriezvisko();
      this.kodpohlavie = student.getKodpohlavie();
      this.skratkaakadtitul = student.getSkratkaakadtitul();
      for (Studium studium : student.getStudium()) {
         this.studium.add(new MongoStudium(studium));
      }
   }

   public String getId() {
      return id;
   }

   public void setId(final String id) {
      this.id = id;
   }

   public String getMeno() {
      return meno;
   }

   public void setMeno(final String meno) {
      this.meno = meno;
   }

   public String getPriezvisko() {
      return priezvisko;
   }

   public void setPriezvisko(final String priezvisko) {
      this.priezvisko = priezvisko;
   }

   public char getKodpohlavie() {
      return kodpohlavie;
   }

   public void setKodpohlavie(final char kodpohlavie) {
      this.kodpohlavie = kodpohlavie;
   }

   public String getSkratkaakadtitul() {
      return skratkaakadtitul;
   }

   public void setSkratkaakadtitul(final String skratkaakadtitul) {
      this.skratkaakadtitul = skratkaakadtitul;
   }

   public List<MongoStudium> getStudium() {
      return studium;
   }

   public void setStudium(final List<MongoStudium> studium) {
      this.studium = studium;
   }

   @Override
   public String toString() {
      return "MongoStudent{" +
            "id=" + id +
            ", meno='" + meno + '\'' +
            ", priezvisko='" + priezvisko + '\'' +
            ", kodpohlavie=" + kodpohlavie +
            ", skratkaakadtitul='" + skratkaakadtitul + '\'' +
            ", studium=" + studium +
            '}';
   }

}
