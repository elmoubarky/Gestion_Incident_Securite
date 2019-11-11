package org.sid.unhcr.dao;

import org.sid.unhcr.entitie.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AppRoleRepository extends JpaRepository<AppRole, Long>{
	
	public AppRole findByRoleName(String roleName);
	public AppRole findById(int id);

}
