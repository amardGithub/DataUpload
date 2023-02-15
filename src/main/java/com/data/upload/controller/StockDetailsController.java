package com.data.upload.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.data.upload.model.StockDetails;
import com.data.upload.service.StockDetailsService;

@RestController
@RequestMapping("/v1")
public class StockDetailsController {

	@Autowired
	private StockDetailsService service;

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws Exception {
		service.uploadFile(file);
		return new ResponseEntity<>("Data upload successful!",HttpStatus.CREATED);
	}

	@RequestMapping(value = "/quaterlyStock", method = RequestMethod.GET)
	public ResponseEntity<List<StockDetails>> quaterlyStock(@RequestParam("quarter") int number,
			@RequestParam("stock") String stock) throws Exception {
		List<StockDetails> stockData = service.getQuaterlyStock(number, stock);
		return new ResponseEntity<>(stockData,HttpStatus.OK);
	}

	@RequestMapping(value = "/addRecord", method = RequestMethod.POST)
	public ResponseEntity<String> addRecord(@RequestBody StockDetails stockData) throws Exception {
		service.addRecord(stockData);
		return new ResponseEntity<>("Record added successfully!",HttpStatus.CREATED);
	}
}
