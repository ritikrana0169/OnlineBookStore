package com.onlineBookStore.model;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	    private Long id;
	    private String name;
	    private String email;
	    private Set<String> roles;
	    }