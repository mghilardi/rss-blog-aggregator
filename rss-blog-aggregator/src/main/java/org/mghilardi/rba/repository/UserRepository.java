package org.mghilardi.rba.repository;

import org.mghilardi.rba.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByName(String name);

}
