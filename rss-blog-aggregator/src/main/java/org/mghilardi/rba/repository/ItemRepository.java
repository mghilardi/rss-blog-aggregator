package org.mghilardi.rba.repository;

import java.util.List;

import org.mghilardi.rba.entity.Blog;
import org.mghilardi.rba.entity.Item;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Integer> {

	List<Item> findByBlog(Blog blog, Pageable pageable);

	Item findByBlogAndLink(Blog blog, String link);

}
