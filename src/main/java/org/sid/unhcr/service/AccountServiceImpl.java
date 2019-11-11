package org.sid.unhcr.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import org.sid.unhcr.dao.AppRoleRepository;
import org.sid.unhcr.dao.AppUserRepository;
import org.sid.unhcr.entitie.AppRole;
import org.sid.unhcr.entitie.AppUser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{
	
	//@Autowired
	private AppRoleRepository appRoleRepository;
		
	//@Autowired
	private AppUserRepository appUserRepository;
		
	//crypter le mot de passe avec Bcrypt
	//@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	//private 
	
	public AccountServiceImpl(AppRoleRepository appRoleRepository, AppUserRepository appUserRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.appRoleRepository = appRoleRepository;
		this.appUserRepository = appUserRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	public AppUser saveUser(String username, String password, String confirmed, String email, String telephone) {
		// TODO Auto-generated method stub
		
		AppUser user = appUserRepository.findByEmail(email);
		System.out.println("-----user by email --- "+user);
		AppUser user2 = appUserRepository.findByUsername(username);
		System.out.println("-----user by username --- "+user2);
		//verifier si l'utilisateur n'existe pas
		if(user!=null)throw new RuntimeException("UserName already exists");
		if(user2!=null)throw new RuntimeException("UserEmail already exists");
		//verifier la conformite des deux password
		if(!password.equals(confirmed))throw new RuntimeException("please confirme your password");
		//creation de l'user
		AppUser appUser = new AppUser();
		appUser.setDateCreation(new Date());
		appUser.setUsername(username);
		appUser.setEmail(email);
		appUser.setActived(true);
		appUser.setTelephone(telephone);
		appUser.setStatut("ACTIF");
		appUser.setPassword(bCryptPasswordEncoder.encode(password));
		appUserRepository.save(appUser);
		
		//ajouter un role a un utilisateur
		addRoleToUser(username, "USER");
		
		return appUser;
	}

	@Override
	public AppRole saveRole(String rolename) {
		
		AppRole rol2 = appRoleRepository.findByRoleName(rolename);
		//verifier si le role existe pas
		if(rol2!=null)throw new RuntimeException("Role already exists");
		AppRole rol = new AppRole();
		rol.setRoleName(rolename);
		appRoleRepository.save(rol);
		return rol;
	}

	@Override
	public AppUser loadUserByUsername(String username) {
		// TODO Auto-generated method stub
		return appUserRepository.findByUsername(username);
	}

	@Override
	public AppUser loadUserByEmail(String email) {
		// TODO Auto-generated method stub
		return appUserRepository.findByEmail(email);
	}

	@Override
	public AppUser addRoleToUser(String username, String rolename) {
		// TODO Auto-generated method stub
		AppUser user = appUserRepository.findByUsername(username);
		if(user==null)throw new RuntimeException("User not exists");
		AppRole role = appRoleRepository.findByRoleName(rolename);
		if(role==null)throw new RuntimeException("Role not exists");
		user.getRoles().add(role);
		
		return user;
		
	}

	@Override
	public AppRole loadRoleByRolename(String rolename) {
		// TODO Auto-generated method stub
		return appRoleRepository.findByRoleName(rolename);
	}

	@Override
	public AppUser updateUserPassword(String username, String password, String lastpassword) {
		// TODO Auto-generated method stub
		//recuperer l'user en fonction de username
		
		System.out.println("username "+username);
		System.out.println("password "+password);
		System.out.println("lastpassword "+lastpassword);
		AppUser user = appUserRepository.findByUsername(username);
		if(user==null)throw new RuntimeException("User not exists");
		String pass = user.getPassword();
		System.out.println("user password "+pass);
		System.out.println("password enter "+bCryptPasswordEncoder.matches(pass, lastpassword));
		//if(bCryptPasswordEncoder.matches(pass, lastpassword)==false)throw new RuntimeException("User password is different");
		user.setPassword(bCryptPasswordEncoder.encode(password));
		appUserRepository.saveAndFlush(user);
		return user;
	}

	@Override
	public AppUser updateUser(long iduser, String username, String email, String telephone) {
		// TODO Auto-generated method stub
		System.out.println("username "+username);
		System.out.println("email "+email);
		System.out.println("iduser "+iduser);
		System.out.println("telephone "+telephone);
		AppUser user = appUserRepository.findByIdUser(iduser);
		System.out.println("user "+user);
		if(user==null)throw new RuntimeException("User not exists");
		//String password = user.getPassword();
		
		user.setEmail(email);
		//user.setPassword(password);
		user.setTelephone(telephone);
		user.setUsername(username);
		appUserRepository.saveAndFlush(user);
		return user;
	}

	@Override
	public AppUser loadUserById(int id) {
		// TODO Auto-generated method stub
		return appUserRepository.findByIdUser(id);
	}

	@Override
	public AppRole loadRoleById(int id) {
		// TODO Auto-generated method stub
		return appRoleRepository.findById(id);
	}

	@Override
	public List<AppUser> listUser() {
		// TODO Auto-generated method stub
		return appUserRepository.findAll();
	}

	@Override
	public List<AppUser> RechUser(String value, String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AppRole> listRole() {
		// TODO Auto-generated method stub
		return appRoleRepository.findAll();
	}

	@Override
	public List<AppRole> RechRole(String value, String search) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppUser desactiveUser(int id) {
		// TODO Auto-generated method stub
		AppUser user = appUserRepository.findByIdUser(id);
		if(user==null)throw new RuntimeException("User not exists");
		//verifier si l'utilisateur est actif
		String statut = user.getStatut();
		if(statut.equals("ACTIF")) {
			user.setStatut("INACTIF");
		}else if(statut.equals("INACTIF")) {
			user.setStatut("ACTIF");
		}
		
		appUserRepository.saveAndFlush(user);
		return user;
	}


}
