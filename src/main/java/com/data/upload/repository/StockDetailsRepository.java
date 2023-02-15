package com.data.upload.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.data.upload.model.StockDetails;

@Repository
public interface StockDetailsRepository extends JpaRepository<StockDetails, Integer> {

	@Query("SELECT s FROM StockDetails s WHERE s.quarter =:quarter AND LOWER(s.stock) = LOWER(:stock)")
	public List<StockDetails> find(@Param("quarter") int quarter, @Param("stock") String stock);

}
