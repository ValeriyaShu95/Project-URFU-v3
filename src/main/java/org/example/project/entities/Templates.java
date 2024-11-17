package org.example.project.entities;
import org.json.simple.JSONArray;

import org.json.simple.JSONObject;

import org.json.simple.parser.*;


import jakarta.persistence.*;

import java.math.BigDecimal;
@Entity
@Table(name = "Templates")
public class Templates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private JSONObject jsong;

    public Templates() {
    }

    public Templates(String name, JSONObject jsong) {
        this.name = name;
        this.jsong = jsong;
    }
}
