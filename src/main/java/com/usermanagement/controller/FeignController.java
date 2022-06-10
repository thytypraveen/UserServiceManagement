package com.usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.usermanagement.dao.CityDao;
import com.usermanagement.dto.CityDetails;

@Controller
@RequestMapping("/city")
public class FeignController {

	@Autowired
	CityDao cityDao;

	@RequestMapping(value = "/citydetail", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getCity() throws Exception {

		try {

			System.out.println("reaching in getcitydetails");

			CityDetails cd = cityDao.getCity();

			return new ResponseEntity<CityDetails>(cd, HttpStatus.OK);

		} catch (Exception e) {
			throw new Exception("NO_CREDENTIALS", e);
		}

	}

}
