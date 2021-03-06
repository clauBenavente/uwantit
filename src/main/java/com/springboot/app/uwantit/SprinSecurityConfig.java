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

import com.springboot.app.uwantit.models.service.UsuarioServiceImp;

	@Configuration
	public class SprinSecurityConfig extends WebSecurityConfigurerAdapter{

		@Autowired
		UsuarioServiceImp usuarioService;
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().antMatchers("/", "/css**","/uploads/**", "/listar" , "/form","/producto/**", "/formularioRecuperar", "/comprobarUsername/**", "/preguntasFrecuentes").permitAll()
			.antMatchers("/formularioProducto").hasAnyRole("USER")
			.antMatchers("/formProducto").hasAnyRole("USER")
			.antMatchers("/eliminar/producto/**").hasAnyRole("USER")
			.anyRequest().authenticated()
			.and()
			.formLogin().loginPage("/login")
			.permitAll()
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
			
			builder.userDetailsService(usuarioService).passwordEncoder(passwordEncoder());
		}
	}

