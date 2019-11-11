package org.sid.unhcr.entitie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AppUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUser;
	@Column(unique = true, nullable = false)
	private String username;
	private boolean actived;
	private Date dateCreation;
	@Column(unique = true, nullable = false)
	private String email;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column(unique = true, nullable = false)
	private String password;
	@Column(unique = true, nullable = false)
	private String telephone;
	private String statut;
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<AppRole> roles = new ArrayList<>();

}
