package com.oceanboa.stock.db2service.repository;

import com.oceanboa.stock.db2service.model.Blip;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
public interface BlipRepository extends CrudRepository<Blip, Long> {

}