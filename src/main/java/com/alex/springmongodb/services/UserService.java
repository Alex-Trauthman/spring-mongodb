package com.alex.springmongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alex.springmongodb.domain.User;
import com.alex.springmongodb.dto.UserDTO;
import com.alex.springmongodb.exceptions.ObjectNotFoundException;
import com.alex.springmongodb.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	public User findById(String id) {
		Optional<User> user = repository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	public User insert(User user) {
		return repository.insert(user);
	}
	public User fromDTO(UserDTO userdto) {
		return new User(userdto.getId(),userdto.getName(),userdto.getEmail());
	}
	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}
	public User update(User user) {
		User newObj = repository.findById(user.getId()).get();
		updateData(newObj, user);
		return repository.save(newObj);
	}
	private void updateData(User newObj, User user) {
		newObj.setName(user.getName());
		newObj.setEmail(user.getEmail());
	}
}
