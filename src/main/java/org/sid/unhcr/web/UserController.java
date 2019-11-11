package org.sid.unhcr.web;

import java.util.List;

import org.sid.unhcr.entitie.AppRole;
import org.sid.unhcr.entitie.AppUser;
import org.sid.unhcr.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.Data;

@RestController
public class UserController {

	@Autowired
	private AccountService accountService;
	
	//methode pour retourner la liste des User en fonction de l'id
		@GetMapping(path="/UserById/{id}")
		public AppUser getUser(@PathVariable("id") int id) {
			AppUser op = accountService.loadUserById(id);
			System.out.println("User trouve "+op);
			return op;
		}
		
	//methode pour retourner la liste des User en fonction de l'id
	@GetMapping(path="/Desactive/{id}")
	public AppUser DesactivUser(@PathVariable("id") int id) {
		AppUser op = accountService.desactiveUser(id);
		
		System.out.println("User trouve "+op);
		return op;
	}
	
	//methode pour retourner la liste des User
	@GetMapping("/listUsers")
	public List<AppUser> listUser(){
		System.out.println("liste des users "+accountService.listUser());
		return accountService.listUser();
	}
	
	//methode pour retourner la liste des Role en fonction de l'id
		@GetMapping(path="/RoleById/{id}")
		public AppRole getRole(@PathVariable("id") int id) {
			AppRole op = accountService.loadRoleById(id);
			System.out.println("Role trouve "+op);
			return op;
		}
		
		//methode pour retourner la liste des User
		@GetMapping("/listRoles")
		public List<AppRole> listrole(){
			System.out.println("liste des roles "+accountService.listRole());
			return accountService.listRole();
		}
	
	//methode permettant de retourner un objet de type AppUser
	@PostMapping("/register")
	public AppUser register(@RequestBody UserForm userForm) {
		
		System.out.println("-----user form security--- "+userForm);
		return accountService.saveUser(userForm.getUsername(), userForm.getPassword(), userForm.getConfirmedPassword(),
				userForm.getEmail(), userForm.getTelephone());
	}
	

	//methode permettant de retourner un objet de type AppUser
	@PostMapping("/addRole")
	public AppRole addRole(@RequestBody RoleForm roleForm) {
		return accountService.saveRole(roleForm.getRolename());
	}
	
	//methode permettant de retourner un objet de type AppUser
		@PostMapping("/updateUserPassword")
		public AppUser updateUserPassword(@RequestBody UserChangePasswordForm changePasswordForm) {
			return accountService.updateUserPassword(changePasswordForm.getUsername(), changePasswordForm.getPassword(),
					changePasswordForm.getLastpassword());
		}
		
	// methode permettant de retourner un objet de type AppUser
	@PostMapping("/updateUser")
	public AppUser updateUser(@RequestBody UserUpdateForm userUpdateForm) {
		return accountService.updateUser(userUpdateForm.getIduser(), userUpdateForm.getUsername(), 
				userUpdateForm.getEmail(), userUpdateForm.getTelephone());
	}
	
	//methode permettant de retourner un objet de type AppUser
		@PostMapping("/addRoleToUser")
		public AppUser addroleToUser(@RequestBody RoleUserForm roleUserForm) {
			return accountService.addRoleToUser(roleUserForm.getUsername(), 
					roleUserForm.getRolename());
		}
}

@Data
class RoleUserForm{
	private String username;
	private String rolename;
}


@Data
class UserForm{
	private String username;
	private String password;
	private String confirmedPassword;
	private String telephone;
	private String email;
}

@Data
class UserUpdateForm{
	private long iduser;
	private String username;
	private String telephone;
	private String email;
}

@Data
class UserChangePasswordForm{
	private String username;
	private String password;
	private String lastpassword;
}

@Data
class RoleForm{
	private String rolename;
	
}
