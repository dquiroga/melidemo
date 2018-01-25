package com.mercadolibre.repository;

import org.springframework.data.repository.CrudRepository;

import com.mercadolibre.domian.Person;

public interface PersonRepository extends CrudRepository<Person,String>{

}
