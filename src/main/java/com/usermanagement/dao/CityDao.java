package com.usermanagement.dao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.usermanagement.dto.CityDetails;

@FeignClient(url = "https://ipinfo.io/161.185.160.93", name = "USA-city")
public interface CityDao {

	@RequestMapping(value = "/geo", method = RequestMethod.GET)
	public CityDetails getCity();

}
