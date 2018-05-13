package runner;

import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

import sk.gursky.nosql.aislike.entity.StudijnyProgram;

/**
 * @author <a href="mailto:mat.per.vt@gmail.com">Matej Perejda</a>
 */
public class MongoStudijnyProgram implements Serializable {
   private static final long serialVersionUID = 3576960990656770608L;

   private Long id;
   @Field("skratka")
   private String skratka;
   @Field("popis")
   private String popis;

   public MongoStudijnyProgram() {
      // empty constructor
   }

   public MongoStudijnyProgram(StudijnyProgram studijnyProgram) {
      this.id = studijnyProgram.getId();
      this.skratka = studijnyProgram.getSkratka();
      this.popis = studijnyProgram.getPopis();
   }

   public Long getId() {
      return id;
   }

   public void setId(final Long id) {
      this.id = id;
   }

   public String getSkratka() {
      return skratka;
   }

   public void setSkratka(final String skratka) {
      this.skratka = skratka;
   }

   public String getPopis() {
      return popis;
   }

   public void setPopis(final String popis) {
      this.popis = popis;
   }

   @Override
   public String toString() {
      return "MongoStudijnyProgram{" +
            "id=" + id +
            ", skratka='" + skratka + '\'' +
            ", popis='" + popis + '\'' +
            '}';
   }
}
