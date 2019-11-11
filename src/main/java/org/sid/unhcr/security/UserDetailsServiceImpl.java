package org.sid.unhcr.security;

import java.util.ArrayList;
import java.util.Collection;

import org.sid.unhcr.entitie.AppUser;
import org.sid.unhcr.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private AccountService accountService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user = accountService.loadUserByUsername(username);
		System.out.println("----user----- "+user);
		
		if(user==null) throw new UsernameNotFoundException(" invalid username");
		if(user.getStatut()=="INACTIF") new UsernameNotFoundException(" user inactif");
		//recuperer la listes des autorisation, roles
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(r->{
			authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
		});
		
		return new User(user.getUsername(), user.getPassword(), authorities);
	}

}
