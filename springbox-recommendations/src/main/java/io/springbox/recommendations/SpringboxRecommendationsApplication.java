package io.springbox.recommendations;

import javax.sql.DataSource;

import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.model.jdbc.ReloadFromJDBCDataModel;
import org.apache.mahout.cf.taste.model.DataModel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@SpringBootApplication
@EnableDiscoveryClient
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringboxRecommendationsApplication extends ResourceServerConfigurerAdapter{

    public static void main(String[] args) {
        SpringApplication.run(SpringboxRecommendationsApplication.class, args);
    }



    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()

                //.antMatchers(HttpMethod.POST, "/recommendations/**").authenticated()
//                .antMatchers(HttpMethod.POST,"/people/**").authenticated()
//                .antMatchers(HttpMethod.POST,"/movie/**").authenticated()
//                .antMatchers(HttpMethod.GET,"/does/**/").authenticated()
//
//                .and().authorizeRequests()
                .anyRequest().permitAll();

    }

    @Bean
    public DataModel dataModel(DataSource dataSource) throws Exception {
        return new ReloadFromJDBCDataModel(new MySQLJDBCDataModel(dataSource,"ratings","user_id","movie_id","rating","timestamp"));
    }


    
}
