package pl.almma.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private DataSource dataSource;
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth
			.jdbcAuthentication()
			.usersByUsernameQuery("SELECT email, pass, active FROM player where email=? ")
			.authoritiesByUsernameQuery("SELECT p.email, r.role FROM player p inner join role r on p.role_id = r.id WHERE p.email = ?")
			.dataSource(dataSource)
			.passwordEncoder(bCryptPasswordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) {
		try {
			http
				.authorizeRequests()
				.antMatchers("/admin/*").hasAuthority("Admin")
				.antMatchers("/trainer/*").hasAnyAuthority("Trainer", "Admin")
				.antMatchers("/player/*").hasAnyAuthority("Player", "Trainer", "Admin")
				.anyRequest().permitAll()
				.and()
				.formLogin().loginPage("/login").failureUrl("/login?error=true").defaultSuccessUrl("/")
				.usernameParameter("email")
				.passwordParameter("pass")
				.and()
				.logout().logoutUrl("/logout").logoutSuccessUrl("/");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


	
}
