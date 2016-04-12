package io.springbox.recommendations;

import javax.sql.DataSource;

import io.springbox.recommendations.services.DBLoader;
import io.springbox.recommendations.services.RecommendationService;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.jdbc.MySQLJDBCDataModel;
import org.apache.mahout.cf.taste.impl.model.jdbc.ReloadFromJDBCDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.JDBCDataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
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
        return new ReloadFromJDBCDataModel(new MySQLJDBCDataModel(dataSource,"review","user_id","movie_id","rating","timestamp"));
    }

//    @Bean
//    public Recommender userRecommender(DataModel dataModel) throws Exception{
//        PearsonCorrelationSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
//        UserNeighborhood neighborhood = new NearestNUserNeighborhood(5,similarity,dataModel);
//        return new CachingRecommender(new GenericUserBasedRecommender(dataModel,neighborhood,similarity));
//    }
//
//
//
//    @Bean
//    public Recommender movieRecommender(DataModel dataModel) throws Exception{
//        PearsonCorrelationSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
//        return new CachingRecommender(new GenericItemBasedRecommender(dataModel,similarity));
//    }
    
}
