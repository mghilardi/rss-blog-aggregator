package org.mghilardi.rba.repository;

import java.util.List;

import org.mghilardi.rba.entity.Blog;
import org.mghilardi.rba.entity.Item;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemRepository extends JpaRepository<Item, Integer> {

	public static final String FIND_ITEM_BY_USER_ID = 
		"SELECT i " + 
		"FROM Item i " + 
		"LEFT JOIN i.blog b " + 
		"LEFT JOIN b.user u " + 
		"WHERE u.id = :userId " + 
		"ORDER BY i.publishedDate DESC";

	List<Item> findByBlog(Blog blog, Pageable pageable);

	Item findByBlogAndLink(Blog blog, String link);

	@Query(FIND_ITEM_BY_USER_ID)
	List<Item> findItensByUserId(@Param("userId") Integer userId, Pageable pageable);
}
