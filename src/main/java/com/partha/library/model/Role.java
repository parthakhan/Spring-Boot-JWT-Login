package com.partha.library.model;

import java.util.List;
import java.util.Set;


import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
    private long id;
	@Column(name="name")
    private String name;
    
	
    //@OneToMany(mappedBy = "role")    
   // private List<User> users;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
        
}