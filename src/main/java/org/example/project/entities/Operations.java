package org.example.project.entities;

import jakarta.persistence.*;
import org.json.simple.JSONObject;

import java.math.BigDecimal;

@Entity
@Table(name = "Operations")
public class Operations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameOperation;
    private BigDecimal price;

    public Operations() {
    }

    public Operations(String nameOperation, BigDecimal price) {
        this.nameOperation = nameOperation;
        this.price = price;
    }

    public String toText1() {
        return nameOperation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameOperation() {
        return nameOperation;
    }

    public void setNameOperation(String nameOperation) {
        this.nameOperation = nameOperation;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
