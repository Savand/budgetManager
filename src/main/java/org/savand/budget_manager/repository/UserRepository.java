package org.savand.budget_manager.repository;

import org.savand.budget_manager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{	
	User findByEmail(String email);
}
