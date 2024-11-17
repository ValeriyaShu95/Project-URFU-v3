package org.example.project.controllers;

import org.example.project.entities.Materials;
import org.example.project.entities.Operations;
import org.example.project.repo.EquipmentRepo;
import org.example.project.repo.MaterialsRepo;
import org.example.project.repo.OperationsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.Soundbank;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MainController {
    private EquipmentRepo equipmentRepo;
    private OperationsRepo operationsRepo;
    private MaterialsRepo materialsRepo;

    @Autowired
    public MainController(EquipmentRepo equipmentRepo, OperationsRepo operationsRepo, MaterialsRepo materialsRepo) {
        this.equipmentRepo = equipmentRepo;
        this.operationsRepo = operationsRepo;
        this.materialsRepo = materialsRepo;

    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getAllOperationsAndMaterials")
    public List<String> getAllOperationsAndMaterials(Model model) {
        List<Operations> operationsList = (List<Operations>) operationsRepo.findAll();
        List<Materials> materialsList = (List<Materials>) materialsRepo.findAll();
        List<String> listString = new ArrayList<>();

        for (int i = 0; i < operationsList.size(); i++) {
            listString.add(operationsList.get(i).getNameOperation());
        }
        for (int i = 0; i < materialsList.size(); i++) {
            listString.add(materialsList.get(i).getName());
        }
        return listString;
    }





}
