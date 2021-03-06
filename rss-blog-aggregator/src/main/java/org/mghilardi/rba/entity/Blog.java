package org.mghilardi.rba.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

// @NamedNativeQueries({ @NamedNativeQuery(name = "findBlogsByUser", query = "select * from blog b where b.app_user.id = :userId", resultClass = Blog.class) })
@Entity
public class Blog {

	@Id
	@GeneratedValue
	private Integer id;

	@Size(min = 1, message = "Invalid URL!")
	@URL(message = "Invalid URL!")
	@Column(length = 1000)
	private String url;

	@Size(min = 1, message = "Name must be at least a character!")
	private String name;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "blog", cascade = CascadeType.REMOVE)
	private List<Item> items;

	public Integer getId() {
		return id;
	}

	public List<Item> getItems() {
		return items;
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public User getUser() {
		return user;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
