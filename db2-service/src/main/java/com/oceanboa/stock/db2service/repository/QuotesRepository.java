package com.oceanboa.stock.db2service.repository;


import com.oceanboa.stock.db2service.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface QuotesRepository extends JpaRepository<Quote, Integer>{

    List<Quote> findByUserName(String username);
}
