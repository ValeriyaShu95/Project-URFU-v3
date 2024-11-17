package org.example.project.repo;
import org.example.project.entities.Materials;
import org.example.project.entities.Operations;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface MaterialsRepo extends CrudRepository<Materials,Long> {
    @Query("SELECT price FROM Materials WHERE name = :name")
        //алиас
    BigDecimal findMaterialsPrice(String name);
}
