package com.springboot.app.uwantit;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.context.annotation.Bean;
	import org.springframework.context.annotation.Configuration;
	import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
	import org.springframework.security.config.annotation.web.builders.HttpSecurity;
	import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
	import org.springframework.security.core.userdetails.User;
	import org.springframework.security.core.userdetails.User.UserBuilder;
	import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
	import org.springframework.security.crypto.password.PasswordEncoder;

	@Configuration
	public class SprinSecurityConfig extends WebSecurityConfigurerAdapter{

		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			//deja ver listar con su estilo y fotos, ademas deja navegar por cada producto
			http.authorizeRequests().antMatchers("/", "/css**","/uploads/**", "/listar" ,"/producto/**").permitAll()
			//registro y comprobacion de producto
			.antMatchers("/formularioProducto").hasAnyRole("USER")
			.antMatchers("/formProducto").hasAnyRole("USER")
			//Para eliminar el producto indicado
			.antMatchers("/eliminar/producto/**").hasAnyRole("USER")
			.anyRequest().authenticated()
			.and()
			.formLogin().permitAll()
			.and()
			.logout().permitAll();
		}
		
		@Bean
		public BCryptPasswordEncoder passwordEncoder() {
			
			return new BCryptPasswordEncoder();
		}
		@Autowired
		public void configurerGlobal(AuthenticationManagerBuilder builder)throws Exception {
			PasswordEncoder encoder = passwordEncoder();
			UserBuilder users = User.builder().passwordEncoder(encoder::encode);
			
			builder.inMemoryAuthentication()
			.withUser(users.username("admin").password("12345").roles("ADMIN", "USER"))
			.withUser(users.username("andres").password("12345").roles("USER"));
		}
	}

