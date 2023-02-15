package com.data.upload.model;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity  
@Table  
public class StockDetails {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="quarter")
	private int quarter;
	
	@Column(name="stock")
	private String stock;
	
	@Column(name="date")
	private String date;
	
	@Column(name="open")
	private String open;
	
	@Column(name="high")
	private String high;
	
	@Column(name="low")
	private String low;
	
	@Column(name="close")
	private String close;
	
	@Column(name="volume")
	private long volume;
	
	@Column(name="percent_change_price")
	private double percent_change_price;
	
	@Column(name="percent_change_volume_over_last_wk")
	private double percent_change_volume_over_last_wk;
	
	@Column(name="previous_weeks_volume")
	private long previous_weeks_volume;
	
	@Column(name="next_weeks_open")
	private String next_weeks_open;
	
	@Column(name="next_weeks_close")
	private String next_weeks_close;
	
	@Column(name="percent_change_next_weeks_price")
	private double percent_change_next_weeks_price;
	
	@Column(name="days_to_next_dividend")
	private long days_to_next_dividend;
	
	
	public StockDetails() {
		super();
	}
	
	public int getQuarter() {
		return quarter;
	}
	public void setQuarter(int quarter) {
		this.quarter = quarter;
	}
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public String getHigh() {
		return high;
	}
	public void setHigh(String high) {
		this.high = high;
	}
	public String getLow() {
		return low;
	}
	public void setLow(String low) {
		this.low = low;
	}
	public String getClose() {
		return close;
	}
	public void setClose(String close) {
		this.close = close;
	}
	public long getVolume() {
		return volume;
	}
	public void setVolume(long volume) {
		this.volume = volume;
	}
	public double getPercent_change_price() {
		return percent_change_price;
	}
	public void setPercent_change_price(double percent_change_price) {
		this.percent_change_price = percent_change_price;
	}
	public double getPercent_change_volume_over_last_wk() {
		return percent_change_volume_over_last_wk;
	}
	public void setPercent_change_volume_over_last_wk(double percent_change_volume_over_last_wk) {
		this.percent_change_volume_over_last_wk = percent_change_volume_over_last_wk;
	}
	public long getPrevious_weeks_volume() {
		return previous_weeks_volume;
	}
	public void setPrevious_weeks_volume(long previous_weeks_volume) {
		this.previous_weeks_volume = previous_weeks_volume;
	}
	public String getNext_weeks_open() {
		return next_weeks_open;
	}
	public void setNext_weeks_open(String next_weeks_open) {
		this.next_weeks_open = next_weeks_open;
	}
	public String getNext_weeks_close() {
		return next_weeks_close;
	}
	public void setNext_weeks_close(String next_weeks_close) {
		this.next_weeks_close = next_weeks_close;
	}
	public double getPercent_change_next_weeks_price() {
		return percent_change_next_weeks_price;
	}
	public void setPercent_change_next_weeks_price(double percent_change_next_weeks_price) {
		this.percent_change_next_weeks_price = percent_change_next_weeks_price;
	}
	public long getDays_to_next_dividend() {
		return days_to_next_dividend;
	}
	public void setDays_to_next_dividend(long days_to_next_dividend) {
		this.days_to_next_dividend = days_to_next_dividend;
	}
	public double getPercent_return_next_dividend() {
		return percent_return_next_dividend;
	}
	public void setPercent_return_next_dividend(double percent_return_next_dividend) {
		this.percent_return_next_dividend = percent_return_next_dividend;
	}
	private double percent_return_next_dividend;

}
