package org.sid.unhcr.service;

import java.util.List;

import org.sid.unhcr.entitie.AppRole;
import org.sid.unhcr.entitie.AppUser;

public interface AccountService {
	
	public AppUser saveUser(String username, String password, String confirmed, String email, String telephone);
	public AppRole saveRole(String rolename);
	public AppUser loadUserByUsername(String username);
	public AppUser loadUserById(int id);
	public AppRole loadRoleById(int id);
	public AppRole loadRoleByRolename(String rolename);
	public AppUser loadUserByEmail(String email);
	public AppUser desactiveUser(int id);
	public AppUser addRoleToUser(String username, String rolename);
	public AppUser updateUserPassword(String username, String password, String lastpassword);
	public AppUser updateUser(long iduser, String username, String email, String telephone);
	public List<AppUser> listUser();
	public List<AppUser> RechUser(String value, String search);
	public List<AppRole> listRole();
	public List<AppRole> RechRole(String value, String search);

}
