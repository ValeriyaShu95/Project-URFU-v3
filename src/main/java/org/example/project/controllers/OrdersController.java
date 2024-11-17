package org.example.project.controllers;

import org.example.project.entities.Operations;
import org.example.project.entities.Orders;
import org.example.project.repo.OperationsRepo;
import org.example.project.repo.OrdersRepo;
import org.example.project.repo.TemplatesRepo;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class OrdersController {
    private OrdersRepo ordersRepo;

    @Autowired
    public OrdersController(OrdersRepo ordersRepo) {
        this.ordersRepo = ordersRepo;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/addOrders")
    public ResponseEntity<String> addOrders(@RequestParam String name, @RequestParam JSONObject json,
                                                @RequestParam BigDecimal finalAmount,
                                                @RequestParam String status) {
        try {
            Orders orders = new Orders(name, json, finalAmount, status);
            ordersRepo.save(orders);
            return ResponseEntity.ok("Данные успешно добавлены!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ошибка при добавлении данных в базу.");
        }
    }

}
