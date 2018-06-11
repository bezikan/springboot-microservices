package com.oceanboa.stock.dbservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.oceanboa.stock.dbservice.model.Blip;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
public interface BlipRepository extends CrudRepository<Blip, Long> {

}