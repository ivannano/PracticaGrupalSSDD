package es.codeurjc.PracticaGrupalSSDD_1.Usuarios;

import java.util.List;
import java.util.Optional;

public class UserService {
	
	private UserRepository repository;
	
	public UserService(UserRepository repo) {
		this.repository = repo;
	}
	
	public User save(User user) {
		User newUser = repository.save(user);
		return newUser;
	}
	
	public void delete(Long Id) {
		repository.deleteById(Id);
	}
	
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public Optional<User> findOne(long id) {
		return repository.findById(id);
	}
	
	public boolean exist(long id) {
		return repository.existsById(id);
	}
}
