package org.example.project.repo;


import org.example.project.entities.Orders;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepo extends CrudRepository<Orders, Long> {
}
