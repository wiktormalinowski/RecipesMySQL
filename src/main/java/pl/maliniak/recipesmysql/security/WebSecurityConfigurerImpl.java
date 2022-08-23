package pl.maliniak.recipesmysql.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import pl.maliniak.recipesmysql.services.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
public class WebSecurityConfigurerImpl extends WebSecurityConfigurerAdapter {


    private final UserDetailsServiceImpl userDetailsService;
    private final EncoderConfig encoder;

    @Value("${app.defaultPassword}")
    String defaultPassword;

    public WebSecurityConfigurerImpl(UserDetailsServiceImpl userDetailsService, EncoderConfig encoder) {
        this.userDetailsService = userDetailsService;
        this.encoder = encoder;
    }


    @Override
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        DaoAuthenticationProvider daoAuth = new DaoAuthenticationProvider();
        daoAuth.setUserDetailsService(userDetailsService);
        daoAuth.setPasswordEncoder(encoder.passwordEncoder());


        auth.authenticationProvider(daoAuth);

        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(encoder.passwordEncoder().encode(defaultPassword))
                .roles("USER");


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().headers().frameOptions().sameOrigin();

        http.authorizeRequests()
                .antMatchers("/api/register", "/h2-console/**", "/actuator/shutdown").permitAll()
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);




        http.httpBasic();

    }



}
