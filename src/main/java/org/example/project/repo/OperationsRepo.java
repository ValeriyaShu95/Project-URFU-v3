package org.example.project.repo;

import org.example.project.entities.Operations;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface OperationsRepo extends CrudRepository<Operations, Long> {
    @Query("SELECT price FROM Operations WHERE nameOperation = :name")
    //алиас
    BigDecimal findOperationPrice(String name);

    @Query("SELECT o FROM Operations o")
    List<Operations> findAllOperations();
}
