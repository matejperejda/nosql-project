package runner;

import java.io.Serializable;

/**
 * @author <a href="mailto:mat.per.vt@gmail.com">Matej Perejda</a>
 */
public class MenoAPriezvisko implements Serializable {
   private static final long serialVersionUID = -8116078401220155773L;

   private String meno;

   private String priezvisko;

   public MenoAPriezvisko(final String meno, final String priezvisko) {
      this.meno = meno;
      this.priezvisko = priezvisko;
   }

   @Override
   public String toString() {
      return meno + " " + priezvisko;
   }
}
