package io.springbox.recommendations.config;

import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.neo4j.rest.SpringRestGraphDatabase;

@Configuration
@Profile("cloud")
public class CloudConfig  {

    @Bean
    GraphDatabaseService graphDatabaseService() {

        return new SpringRestGraphDatabase("http://springbox.sb05.stations.graphenedb.com:24789/db/data","springbox","dD31yCWjenueI4MQCXU8");
    }
}
