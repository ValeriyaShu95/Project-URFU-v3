package org.example.project.repo;


import org.example.project.entities.Templates;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplatesRepo extends CrudRepository<Templates, Long> {
}
