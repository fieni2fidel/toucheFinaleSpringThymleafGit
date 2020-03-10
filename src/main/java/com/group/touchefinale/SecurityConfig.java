package com.group.touchefinale;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	DataSource dataSource;
	
	
	  @Bean 
	  public UserDetailsService userDetailsService() {
	  
	  User.UserBuilder users = User.withDefaultPasswordEncoder();
	  InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	  manager.createUser(users.username("user").password("password").roles("USER").
	  build());
	  manager.createUser(users.username("admin").password("password").roles("USER",
	  "ADMIN").build());
	  manager.createUser(users.username("fidel").password("fidel").roles("FIDEL").
	  build());
	  
	  return manager;
	  
	  }
	
	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception {
	 * 
	 * auth.jdbcAuthentication() .dataSource(dataSource)
	 * .usersByUsernameQuery("select username as principal, password as credentials, true from users where username=?"
	 * )
	 * .authoritiesByUsernameQuery("select user_username as principal, roles_role as role from users_roles where user_username=?"
	 * ) .rolePrefix("ROLE_");
	 * 
	 * }
	 */
	
	/*
	 * @Bean public PasswordEncoder passwordEncoder() { return new
	 * BCryptPasswordEncoder(); }
	 */
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		

		http.cors().and().csrf().disable().authorizeRequests()
			.antMatchers("/","/menuprincipal",
				"/artiste/getphotoimage","/getphotoimage",
				"/artiste","/artistes","recherche_alphabetique",
				"/evenements","/afrique","/europe","/amerique",
				"/tofs_artistes/banniere_tooche_finale.jpg","/tofs_artistes/xfacebook.png")
					.permitAll()
						.anyRequest()
							.authenticated();
					
		http.formLogin()
			.loginPage("/tf_connect")
				.permitAll()
					.failureForwardUrl("/login_error")
						.successForwardUrl("/afroo_admin_menu")
							.and()
								.logout()
									.logoutUrl("/deconnexion")
										.and()
											.exceptionHandling()
												.accessDeniedPage("/403");
								
	}
}
