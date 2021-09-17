package com.smartcontactmanager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.smartcontactmanager.entities.User;
import com.smartcontactmanager.repository.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userrepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//fetching user from database
		User user = userrepository.getUserByUserName(username);
		if(user == null) {
			throw new UsernameNotFoundException("couldn't found user");
		}
		
		CustomUserDetails customUserDetail = new CustomUserDetails(user);
		
		return customUserDetail;
	}

}
