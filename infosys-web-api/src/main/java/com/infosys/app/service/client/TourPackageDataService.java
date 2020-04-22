package com.infosys.app.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("backend-data")
public interface TourPackageDataService {

    @RequestMapping(method = RequestMethod.GET, path = "/api/pub/packages")
    ResponseEntity<?> findAll();
}