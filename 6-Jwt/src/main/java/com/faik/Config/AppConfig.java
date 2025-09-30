package com.faik.Config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.faik.Model.User;
import com.faik.Repository.UserRepository;

@Configuration
public class AppConfig {
	
	/*
	 * Spring'te bir bean oluşuyor. Bu bean artık jwt Filter'da kullanılacak loadByUserName kısmı artık buradan çağırılacak
	 * ve veritabanındaki soruguyu artık burada atacağız
	 * */
	
	@Autowired
	private UserRepository userRepository;
	
	@Bean
	 UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				Optional<User> optional = userRepository.findByUsername(username);
				if(optional.isPresent()) {
					return optional.get();
				}
				return null;
			}
		};
		
		

	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		
		return authenticationProvider;
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); // Arka tarafta benim girdiğim şifre ile db deki şifreyi karşılaştırır
	}
}
