package com.usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.usermanagement.dao.CityDao;
import com.usermanagement.data.ItemDao;
import com.usermanagement.dto.CityDetails;
import com.usermanagement.dto.Item;

@Controller
@RequestMapping("/items")
public class ItemController {

	


	@Autowired
	ItemDao itemDao;

	@RequestMapping(value="/{id}", method = RequestMethod.GET)

	public @ResponseBody ResponseEntity<?> getItem(@PathVariable(value = "id") long id) throws Exception {

		try {

			System.out.println("reaching in getitemdetail");
			System.out.println("displaying id :"+id);

			Item item = itemDao.getItem(id);

			return new ResponseEntity<Item>(item, HttpStatus.OK);

		} catch (Exception e) {
			throw new Exception("NO_CREDENTIALS", e);
			
		}

	}


	
	
}
