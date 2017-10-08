package org.savand.budget_manager.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "email", name = "unique_email") })
public @Data class User extends BaseEntity{

	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(nullable = false, unique = true)
	@Email(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
	private String email;
	
    @Column(nullable = false)
    @NotEmpty
    @Length(min = 5)
	private String password;
	
	@Column(nullable = false)
	private boolean enabled = true;
	
    @Enumerated(EnumType.STRING)
	@CollectionTable(name = "users_roles", uniqueConstraints = @UniqueConstraint(columnNames = { "user_id",
	      "role" }), joinColumns = @JoinColumn(name = "user_id"))
	@ElementCollection(fetch = FetchType.EAGER)
	@Column(name = "role")
	@JsonIgnore
	private Set<Role> roles;
    
    @ManyToMany(mappedBy = "contributors")
    private List<Budget> budgets;
    
    public enum Role {
    	  ROLE_USER, ROLE_ADMIN;
    	}
	
}
