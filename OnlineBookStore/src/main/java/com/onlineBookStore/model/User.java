package com.onlineBookStore.model;

import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Users")
@Entity
public class User {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String name;
	    private String password;
	    private boolean active=true;
	    @Column(nullable = false, unique = true)
	    private String email;
	    @ElementCollection(fetch = FetchType.EAGER)
	    @CollectionTable(name = "roles", joinColumns = @JoinColumn(name = "user_id"))
	    @Column(name = "role")
	    private Set<String> roles;
	    }