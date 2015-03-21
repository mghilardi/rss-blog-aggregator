package org.mghilardi.rba.repository;

import java.util.List;

import org.mghilardi.rba.entity.Blog;
import org.mghilardi.rba.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Integer> {

	List<Blog> findByUser(User user);
}
