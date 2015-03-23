package org.mghilardi.rba.repository;

import java.util.List;

import org.mghilardi.rba.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {

	public static final String FIND_BLOG_BY_USER_ID = "SELECT b FROM Blog b WHERE b.user.id = :userId";

	// the @Param "userId" should be on the query string.
	@Query(FIND_BLOG_BY_USER_ID)
	List<Blog> findByUserId(@Param("userId") Integer userId);
}
