package runner;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author <a href="mailto:mat.per.vt@gmail.com">Matej Perejda</a>
 */
public class StudentAggregation {

   //@Field("studium.zaciatokStudia.rok")
   @Field("rok")
   private int rok;

   //@Field("studium.studijnyProgram.skratka")
   @Field("skratka")
   private String skratka;

   @Field("total")
   private long total;

   public StudentAggregation() {
   }

   public StudentAggregation(final int rok, final String skratka, final long total) {
      this.rok = rok;
      this.skratka = skratka;
      this.total = total;
   }

   public int getRok() {
      return rok;
   }

   public void setRok(final int rok) {
      this.rok = rok;
   }

   public String getSkratka() {
      return skratka;
   }

   public void setSkratka(final String skratka) {
      this.skratka = skratka;
   }

   public long getTotal() {
      return total;
   }

   public void setTotal(final long total) {
      this.total = total;
   }

   @Override
   public String toString() {
      return "\nrok=" + rok +
            "\tskratka=" + skratka +
            "\t\t\t\t\ttotal=" + total;
   }
}
