package com.usermanagement.data;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.usermanagement.dto.Item;

@FeignClient(url = "http://localhost:9900/items", name = "Item-detail")
public interface ItemDao {

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Item getItem(@PathVariable(value = "id") long id);
	
}

