package runner;

import java.io.Serializable;

import sk.gursky.nosql.aislike.entity.Studium;

/**
 * @author <a href="mailto:mat.per.vt@gmail.com">Matej Perejda</a>
 */
public class MongoStudium implements Serializable {
   private static final long serialVersionUID = 9010153859996755393L;

   private Long id;
   private Datum zaciatokStudia;
   private Datum koniecStudia;
   private MongoStudijnyProgram studijnyProgram;

   public MongoStudium() {
      // empty constructor
   }

   public MongoStudium(Studium studium) {
      this.id = studium.getId();

      if (!studium.getZaciatokStudia().equals("")) {
         String[] zaciatok = splitStringDate(studium.getZaciatokStudia());
         this.zaciatokStudia = new Datum(Integer.valueOf(zaciatok[0]), Integer.valueOf(zaciatok[1]), Integer.valueOf(zaciatok[2]));
      } else {
         this.zaciatokStudia = new Datum(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
      }

      if (!studium.getKoniecStudia().equals("")) {
         String[] koniec = splitStringDate(studium.getKoniecStudia());
         this.koniecStudia = new Datum(Integer.valueOf(koniec[0]), Integer.valueOf(koniec[1]), Integer.valueOf(koniec[2]));
      } else {
         this.koniecStudia = new Datum(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
      }

      this.studijnyProgram = new MongoStudijnyProgram(studium.getStudijnyProgram());
   }

   public Long getId() {
      return id;
   }

   public void setId(final Long id) {
      this.id = id;
   }

   public Datum getZaciatokStudia() {
      return zaciatokStudia;
   }

   public void setZaciatokStudia(final Datum zaciatokStudia) {
      this.zaciatokStudia = zaciatokStudia;
   }

   public Datum getKoniecStudia() {
      return koniecStudia;
   }

   public void setKoniecStudia(final Datum koniecStudia) {
      this.koniecStudia = koniecStudia;
   }

   public MongoStudijnyProgram getStudijnyProgram() {
      return studijnyProgram;
   }

   public void setStudijnyProgram(final MongoStudijnyProgram studijnyProgram) {
      this.studijnyProgram = studijnyProgram;
   }

   @Override
   public String toString() {
      return "MongoStudium{" +
            "id=" + id +
            ", zaciatokStudia='" + zaciatokStudia + '\'' +
            ", koniecStudia='" + koniecStudia + '\'' +
            ", studijnyProgram=" + studijnyProgram +
            '}';
   }

   private String[] splitStringDate(String date) {
      return date.split("\\.");
   }
}
