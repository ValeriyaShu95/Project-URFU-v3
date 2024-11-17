package org.example.project.controllers;

import org.example.project.entities.Materials;
import org.example.project.entities.Operations;
import org.example.project.repo.EquipmentRepo;
import org.example.project.repo.MaterialsRepo;
import org.example.project.repo.OperationsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MaterialsController {
    private EquipmentRepo equipmentRepo;
    private OperationsRepo operationsRepo;
    private MaterialsRepo materialsRepo;


    @Autowired
    public MaterialsController(EquipmentRepo equipmentRepo, OperationsRepo operationsRep, MaterialsRepo materialsRepo) {
        this.equipmentRepo = equipmentRepo;
        this.operationsRepo = operationsRepo;
        this.materialsRepo = materialsRepo;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/getAllMaterials")
    public List<String> materialsGet(Model model) {
        List<Materials> materialsList = (List<Materials>) materialsRepo.findAll();
        List<String> materialListString = new ArrayList<>();

        for (int i = 0; i < materialsList.size(); i++) {
            materialListString.add(materialsList.get(i).getName()); //???????????????
        }
        return materialListString;
    }


    @CrossOrigin(origins = "*")
    @PostMapping("/addMaterials")
    public ResponseEntity<String> addMaterials(@RequestParam String name, @RequestParam BigDecimal price) {
        try {
            Materials materials = new Materials(name, price);
            materialsRepo.save(materials);
            return ResponseEntity.ok("Данные успешно добавлены!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ошибка при добавлении данных в базу.");
        }
    }
}
