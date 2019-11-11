package org.sid.unhcr;


import org.sid.unhcr.entitie.AppRole;
import org.sid.unhcr.entitie.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class GestionIncidentSecuriteApplication implements CommandLineRunner{

	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;

	public static void main(String[] args) {
		SpringApplication.run(GestionIncidentSecuriteApplication.class, args);
		// exposer l'id

	}

	// le bean de Swagger
	@Bean
	public Docket gestionIncidentApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("org.unhcr.sid")).build();
	}
	
	@Override
	public void run(String... args) throws Exception {

		// exposer l'id
		repositoryRestConfiguration.exposeIdsFor(AppRole.class, AppUser.class);

		 
	}

	/*
	 * @Bean CommandLineRunner start(AccountService accountService) {
	 * System.out.println("--------ici------------"); return args->{
	 * accountService.saveRole("USER"); accountService.saveRole("ADMIN"); ;
	 * accountService.saveRole("RECEPTION"); accountService.saveRole("FINANCES");
	 * 
	 * 
	 * accountService.saveUser("djoman", "1234", "1234", "fideledjoman64@gmail.com",
	 * "59146800"); accountService.saveUser("kris", "1234", "1234",
	 * "kris@gmail.com", "45567890"); accountService.saveUser("moustafa", "1234",
	 * "1234", "moustafa@gmail.com", "987654309");
	 * 
	 * accountService.addRoleToUser("djoman", "ADMIN");
	 * accountService.addRoleToUser("djoman", "RECEPTION");
	 * accountService.addRoleToUser("djoman", "FINANCES");
	 * accountService.addRoleToUser("djoman", "USER");
	 * accountService.addRoleToUser("kris", "ADMIN");
	 * accountService.addRoleToUser("kris", "RECEPTION");
	 * accountService.addRoleToUser("kris", "FINANCES");
	 * accountService.addRoleToUser("kris", "USER");
	 * accountService.addRoleToUser("moustafa", "FINANCES");
	 *
	 * }; }
	 */

	@Bean
	BCryptPasswordEncoder getBCPE() {
		return new BCryptPasswordEncoder();
	}

}
