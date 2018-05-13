package config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import runner.StudentRepository;

/**
 * @author <a href="mailto:mat.per.vt@gmail.com">Matej Perejda</a>
 */
@Configuration
@EnableMongoRepositories(basePackageClasses = StudentRepository.class)
@ComponentScan(basePackages = "config")
public class MongoConfig extends AbstractMongoConfiguration {

   private final String hostIP = "127.0.0.1";
   private final String databaseName = "test";
   private final int port = 27017;

   @Override
   public MongoClient mongoClient() {
      return new MongoClient(hostIP, port);
   }

   @Override
   protected String getDatabaseName() {
      return databaseName;
   }
}