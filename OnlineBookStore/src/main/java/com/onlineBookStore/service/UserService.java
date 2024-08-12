package com.onlineBookStore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.onlineBookStore.exception.DuplicateEmailException;
import com.onlineBookStore.model.User;
import com.onlineBookStore.model.UserDto;
import com.onlineBookStore.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	private boolean findUserById(Long id) {
		Optional<User> u1=userRepository.findById(id);
		if(u1.isPresent()) {
			return false;
		}
		return true;
	}
	private boolean findUserByEmail(String email) {
		Optional<User> u1=userRepository.findByEmail(email);
		if(u1.isPresent()) {
			return false;
		}
		return true;
	}
	
	
	public UserDto createNewUser(User user) {
        try {
            User savedUser = userRepository.save(user);
            return new UserDto(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getRoles()
            );
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEmailException("Email '" + user.getEmail() + "' is already in use.");
        }
    }
	 public List<UserDto> getAllUsers() {
	        List<User> users = userRepository.findAll();
	        List<UserDto> userDTOs = new ArrayList<>();

	        for (User user : users) {
	            UserDto userDTO = new UserDto(
	                user.getId(),
	                user.getName(),
	                user.getEmail(),
	                user.getRoles()
	            );
	            userDTOs.add(userDTO);
	        }

	        return userDTOs;
	    }
	public boolean disableUserAccount(Long userId) {
		// TODO Auto-generated method stub
		Optional<User> user=userRepository.findById(userId);
		if(user.isPresent()) {
			
			User usr=user.get();
			usr.setActive(false);
			userRepository.save(usr);
			return true;
		}
		return false;
	}

}
