package com.taller4.backend.service.implementation;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taller4.backend.model.person.UserApp;
import com.taller4.backend.model.person.UserType;
import com.taller4.backend.repository.UserRepository;
import com.taller4.backend.service.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	@Transactional
	public void save(UserApp user) {
		userRepository.save(user);
	}
	
	@Override
	@Transactional
	public void update(long id, UserApp user) {
		Optional<UserApp> toChange = userRepository.findById(id);
		if(toChange.isPresent()) {
			UserApp u = toChange.get();
			u.setUsername(user.getUsername());
			u.setPassword(user.getPassword());
			u.setType(user.getType());
			userRepository.save(u);
		}
	}
	
	@Override
	@Transactional
	public void delete(UserApp user) {
		userRepository.delete(user);
	}

	@Override
	@Transactional
	public Optional<UserApp> findById(long id) {
		return userRepository.findById(id);
	}

	@Override
	@Transactional
	public Iterable<UserApp> findAll() {
		return userRepository.findAll();
	}

	@Override
	@Transactional
	public Iterable<UserApp> findAllAdmins() {
		return userRepository.findByType(UserType.ADMIN);
	}

	@Override
	@Transactional
	public Iterable<UserApp> findAllOperators() {
		return userRepository.findByType(UserType.OPERATOR);
	}

	@Override
	@Transactional
	public UserType[] getTypes() {
		return UserType.values();
	}

}
