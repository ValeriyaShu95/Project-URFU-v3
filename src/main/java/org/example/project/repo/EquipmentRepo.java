package org.example.project.repo;

import org.example.project.entities.Equipment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepo extends CrudRepository<Equipment,Long> {
}
