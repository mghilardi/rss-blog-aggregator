package org.mghilardi.rba.service;

import java.util.List;

import org.mghilardi.rba.entity.Item;
import org.mghilardi.rba.entity.User;
import org.mghilardi.rba.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;
	
	public List<Item> getItems(User user) {
		// return itemRepository.findAll(new PageRequest(0, 20, Direction.DESC, "publishedDate")).getContent();
		return itemRepository.findItensByUserId(user.getId(), new PageRequest(0, 20, Direction.DESC, "publishedDate"));
		
	}
}
