package org.example.project.controllers;

import org.example.project.entities.Operations;
import org.example.project.repo.OperationsRepo;
import org.example.project.repo.TemplatesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class OperationsController {
    private TemplatesRepo templatesRepo;
    private static OperationsRepo operationsRepo;

    @Autowired
    public OperationsController(TemplatesRepo templatesRepo, OperationsRepo operationsRepo) {
        this.templatesRepo = templatesRepo;
        this.operationsRepo = operationsRepo;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/addOperations")
    public ResponseEntity<String> addOperations(@RequestParam String nameOperation,
                                                @RequestParam BigDecimal price) {
        try {
            Operations operations = new Operations(nameOperation, price);
            operationsRepo.save(operations);
            return ResponseEntity.ok("Данные успешно добавлены!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ошибка при добавлении данных в базу.");
        }
    }

}
