package org.example.project.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Materials")
public class Materials {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;

    public Materials(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public Materials() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
