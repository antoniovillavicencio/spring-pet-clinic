package com.javc.springpetclinic.repositories;

import com.javc.springpetclinic.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet, Long> {}
