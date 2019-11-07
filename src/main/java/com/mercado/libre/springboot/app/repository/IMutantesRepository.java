package com.mercado.libre.springboot.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mercado.libre.springboot.app.models.Resultado;

@Repository
public interface IMutantesRepository extends MongoRepository<Resultado, String>{

}
