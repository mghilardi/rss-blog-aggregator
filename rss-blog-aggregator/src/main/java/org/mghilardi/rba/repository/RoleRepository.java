package org.mghilardi.rba.repository;

import org.mghilardi.rba.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByName(String name);

}
