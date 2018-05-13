package runner;

import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

/**
 * @author <a href="mailto:mat.per.vt@gmail.com">Matej Perejda</a>
 */
public class Datum implements Serializable {

   private static final long serialVersionUID = -3167109820836696765L;

   private int den;
   private int mesiac;
   @Field("rok")
   private int rok;

   public Datum() {
      // empty constructor
   }

   public Datum(final int den, final int mesiac, final int rok) {
      this.den = den;
      this.mesiac = mesiac;
      this.rok = rok;
   }

   public int getDen() {
      return den;
   }

   public void setDen(final int den) {
      this.den = den;
   }

   public int getMesiac() {
      return mesiac;
   }

   public void setMesiac(final int mesiac) {
      this.mesiac = mesiac;
   }

   public int getRok() {
      return rok;
   }

   public void setRok(final int rok) {
      this.rok = rok;
   }

   @Override
   public String toString() {
      return "Datum{" +
            "den=" + den +
            ", mesiac=" + mesiac +
            ", rok=" + rok +
            '}';
   }
}
