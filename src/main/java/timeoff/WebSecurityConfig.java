package timeoff;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@Configuration
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
			http
				.csrf().disable()
				.authorizeRequests()
					.antMatchers("/static/**", "/test").permitAll()
					.antMatchers("/api/groups/**").hasRole("super")
					.anyRequest().authenticated();
			http
				.csrf().disable()
				.formLogin()
					.loginPage("/login")
					.permitAll()
					.and()
				.logout()
					.permitAll();
    }

    @Configuration
    protected static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter {
        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
					auth
						.inMemoryAuthentication()
						.withUser("adam")
								.password("pass")
								.roles("super", "user").and()
							.withUser("amy")
								.password("pass")
								.roles("user").and()
							.withUser("aaron")
								.password("pass")
								.roles("user").and()
						.withUser("brooke")
								.password("pass")
								.roles("super", "user").and()
							.withUser("brent")
								.password("pass")
								.roles("user").and()
							.withUser("beth")
								.password("user")
								.roles("user");
        }
    }

}