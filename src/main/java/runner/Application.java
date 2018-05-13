package runner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author <a href="mailto:mat.per.vt@gmail.com">Matej Perejda</a>
 */
@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = StudentRepository.class)
public class Application {

   public static void main(String[] args) {
      SpringApplication.run(Application.class, args);
   }
}
