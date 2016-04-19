package io.springbox.authserver;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.springbox.authserver.domain.User;
import io.springbox.authserver.security.UserRepositoryUserDetailsService;

@SpringBootApplication
@RestController
@EnableResourceServer
@EnableAuthorizationServer
public class SpringboxAuthServerApplication {

    @RequestMapping("/user")
    public Object user(Principal user) {
        OAuth2Authentication authentication = (OAuth2Authentication) user;
        Authentication userAuthentication = authentication.getUserAuthentication();
        return userAuthentication.getPrincipal();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringboxAuthServerApplication.class, args);
    }

    @Configuration
    @EnableAuthorizationServer
    protected static class OAuth2Config extends AuthorizationServerConfigurerAdapter {

        @Autowired
        private AuthenticationManager authenticationManager;

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints.authenticationManager(authenticationManager).accessTokenConverter(jwtAccessTokenConverter());
        }



        @Bean
        public JwtAccessTokenConverter jwtAccessTokenConverter() {
            JwtAccessTokenConverter converter = new CustomTokenEnhancer();
            converter.setSigningKey("foobar");
            return converter;
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer)
                throws Exception {

            oauthServer
                    .allowFormAuthenticationForClients()
                    .tokenKeyAccess("permitAll()").checkTokenAccess(
                    "permitAll()");
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

            clients.inMemory()
                    .withClient("springbox")
                    .secret("springrocks")

                    .authorizedGrantTypes("authorization_code", "refresh_token",
                            "password").scopes("openid");
        }

    }

    @Configuration
    protected static class AuthenticationManagerConfiguration extends GlobalAuthenticationConfigurerAdapter {

        @Autowired
        private UserRepositoryUserDetailsService userDetailsService;

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService);
        }

    }


    protected static class CustomTokenEnhancer extends JwtAccessTokenConverter{

        @Override
        public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
            User user = (User) authentication.getPrincipal();
            final Map<String, Object> additionalInfo = new HashMap<>();

            additionalInfo.put("user_id", user.getId());

            DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);
            customAccessToken.setAdditionalInformation(additionalInfo);
            return super.enhance(customAccessToken, authentication);
        }

    }

    @Configuration
    protected static class SecurityConfig extends ResourceServerConfigurerAdapter{
        @Override
		public void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/user")
                        .authorizeRequests()
                            .anyRequest()
                                .authenticated();
        }
    }
}
