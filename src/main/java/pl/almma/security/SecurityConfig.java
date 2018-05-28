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

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	private DataSource dataSource;

	@Autowired
	public SecurityConfig(BCryptPasswordEncoder bCryptPasswordEncoder, DataSource dataSource) {
		super();
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.dataSource = dataSource;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().usersByUsernameQuery("SELECT email, pass, active FROM user where email = ?")
				.authoritiesByUsernameQuery(
						"SELECT u.email, r.role FROM user u inner join role r on u.role_id = r.id WHERE u.email = ?")
				.dataSource(dataSource).passwordEncoder(bCryptPasswordEncoder);

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/admin/*","/articles/add", "/admin/clubEdit/").hasAuthority("ROLE_ADMIN").antMatchers("/trainer/*")
				.hasAnyAuthority("Trainer", "ROLE_ADMIN").antMatchers("/users/*")
				.hasAnyAuthority("Player", "Trainer", "ROLE_ADMIN").anyRequest().permitAll().and().formLogin()
				.loginPage("/login").failureUrl("/login?error=true").defaultSuccessUrl("/calendar")
				.usernameParameter("email").passwordParameter("pass").and().logout().logoutUrl("/logout")
				.logoutSuccessUrl("/");
	}
}
