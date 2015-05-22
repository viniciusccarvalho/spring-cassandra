package org.springframework.cassandra;

import com.datastax.driver.core.DataType;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.schemabuilder.Create;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cassandra.core.keyspace.CreateTableSpecification;
import org.springframework.cloud.config.java.ServiceScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import javax.annotation.PostConstruct;

/**
 * Created by vcarvalho on 5/7/15.
 */
@SpringBootApplication
@ServiceScan
@EnableCassandraRepositories
public class Application {


    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.run(args);
    }


    @Bean
    public CassandraOperations cassandraTemplate(Session session){
        CassandraTemplate template = new CassandraTemplate(session);
        template.execute("CREATE TABLE IF NOT EXISTS temperature  (\n" +
                "weatherstation_id text,\n" +
                "event_time bigint,\n" +
                "temperature double,\n" +
                "PRIMARY KEY (weatherstation_id,event_time)\n" +
                ");");
        return template;
    }



}
