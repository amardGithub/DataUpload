package com.data.upload.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.data.upload.model.StockDetails;
import com.data.upload.repository.StockDetailsRepository;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;


@Service
public class StockDetailsService {

	@Autowired
	private StockDetailsRepository repo;
	
	public void uploadFile(MultipartFile file) throws Exception {
	List<StockDetails> stockDetails = new ArrayList<>();
	InputStream inputStream = file.getInputStream();
	CsvParserSettings parserSetting = new CsvParserSettings();
	parserSetting.setHeaderExtractionEnabled(true);
	CsvParser parser = new CsvParser(parserSetting);
	List<Record> records = parser.parseAllRecords(inputStream);
	records.forEach(record -> {
		StockDetails stock = new StockDetails();
		stock.setQuarter(Integer.parseInt(record.getString("quarter")));
		stock.setStock(record.getString("stock"));
		stock.setDate(record.getString("date"));
		stock.setOpen(record.getString("open"));
		stock.setHigh(record.getString("high"));
		stock.setLow(record.getString("low"));
		stock.setClose(record.getString("close"));
		stock.setVolume(Long.parseLong(record.getString("volume")));
		stock.setPercent_change_price(Double.parseDouble(record.getString("percent_change_price")));
		try {
			stock.setPercent_change_volume_over_last_wk(
					Double.parseDouble(replaceNull(record.getString("percent_change_volume_over_last_wk"))));
			stock.setPrevious_weeks_volume(Long.parseLong(replaceNull(record.getString("previous_weeks_volume"))));
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
		}

		stock.setNext_weeks_open(record.getString("next_weeks_open"));
		stock.setNext_weeks_close(record.getString("next_weeks_close"));
		stock.setPercent_change_next_weeks_price(
				Double.parseDouble(record.getString("percent_change_next_weeks_price")));
		stock.setDays_to_next_dividend(Long.parseLong(record.getString("days_to_next_dividend")));
		stockDetails.add(stock);
	});

	repo.saveAll(stockDetails);
}
	public ResponseEntity<List<StockDetails>> getQuaterlyStock(int number, String stock)
			throws Exception {
		List<StockDetails> StockDetails = repo.find(number, stock);
		return new ResponseEntity<>(StockDetails, HttpStatus.OK);
	}
	
	public void addRecord(@RequestBody StockDetails stockData) throws Exception {

		repo.save(stockData);
	}
	public static String replaceNull(String str) {
		return str == null ? "" : str;
	}
}
