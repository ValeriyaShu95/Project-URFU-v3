package org.example.project.controllers;


import com.fasterxml.jackson.core.*;
import org.example.project.entities.Materials;
import org.example.project.entities.Operations;
import org.example.project.entities.Orders;
import org.example.project.entities.Templates;
import org.example.project.repo.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class TemplateController {
    private TemplatesRepo templatesRepo;
    private static OperationsRepo operationsRepo;
    private static MaterialsRepo materialsRepo;
    private final OrdersRepo ordersRepo;

    @Autowired
    public TemplateController(TemplatesRepo templatesRepo, OperationsRepo operationsRepo, MaterialsRepo materialsRepo,
                              OrdersRepo ordersRepo) {
        this.templatesRepo = templatesRepo;
        this.operationsRepo = operationsRepo;
        this.materialsRepo = materialsRepo;
        this.ordersRepo = ordersRepo;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/addTemplate")
    public BigDecimal addTemplate(@RequestBody JSONObject json) {
        //2. посчитать стоимость тех операций
        ArrayList<Object> folderStructure = (ArrayList<Object>) json.get("folders");//1. превратить json в итерируемый тип данных
        String name = (String) json.get("name");
        BigDecimal finalPrice;
        finalPrice =  processFolders(folderStructure);//проходится по тех операциям в цикде for
        System.out.println("Итоговая цена: " + finalPrice);

        try {
            Templates templates = new Templates(name, json); // Создаем объект Templates, передавая в конструктор значение jsong в формате JSONObject
            Orders orders = new Orders( name, json, finalPrice, "test");
            templatesRepo.save(templates);
            ordersRepo.save(orders);
            return finalPrice;
        } catch (Exception e) {
            return finalPrice;
        }

    }




    // Метод для обработки массива папок

    private static BigDecimal processFolders(ArrayList<Object> folders) {
        BigDecimal finalPrice = BigDecimal.valueOf(0.0);
        for (Object folderObj : folders) {
            Map<String, Object> folder = (Map<String, Object>) folderObj;
            String folderName = (String) folder.get("name");
            String folderAmount = (String) folder.get("amount");
            String folderTechnicalWasteCount = (String) folder.get("technicalWasteCount");

            int folderAmountInt = Integer.parseInt(folderAmount);
            BigDecimal folderAmountDecimal = BigDecimal.valueOf(folderAmountInt);

            BigDecimal folderTechnicalWasteCountDecimal;
            if (folderTechnicalWasteCount != null) {
                folderTechnicalWasteCountDecimal = new BigDecimal(folderTechnicalWasteCount);
            } else {
                folderTechnicalWasteCountDecimal = BigDecimal.ZERO;
            }

            BigDecimal finalAmount;
            if (folderTechnicalWasteCountDecimal == BigDecimal.ZERO) {
                finalAmount = folderAmountDecimal;
            } else {
                finalAmount = folderAmountDecimal.add(folderTechnicalWasteCountDecimal);
            }

            BigDecimal operationPrice = operationsRepo.findOperationPrice(folderName.toLowerCase());
            BigDecimal materialsPrice = materialsRepo.findMaterialsPrice(folderName.toLowerCase());
            System.out.println("Тех операция: " + folderName + " цена операции: " + operationPrice +
                    " цена материала: " + materialsPrice);
            // Добавляем к finalPrice только если operationPrice и materialsPrice не null
            if (operationPrice != null) {
                finalPrice = finalPrice.add(operationPrice.multiply(finalAmount));
            }
            if (materialsPrice != null) {
                finalPrice = finalPrice.add(materialsPrice.multiply(finalAmount));
            }
            // Получение вложенных папок, если они есть
            ArrayList<Object> nestedFolders = (ArrayList<Object>) folder.get("folders");
            if (nestedFolders != null && !nestedFolders.isEmpty()) {
                finalPrice = finalPrice.add(processFolders(nestedFolders));
            }
        }
        return finalPrice;
    }


//    private static void processFolders( ArrayList<Object> folders) {
//        for (Object folderObj : folders) {//проходится по каждому объекту типа folders и говорит, что каждый объект типа Object
//            JSONObject folder = (JSONObject) folderObj; // кладем folderObj в folder и приводим к типу JSONObject
//            String folderName = (String) folder.get("name"); //берем имя у folder (это название тех операции) и кладем его в folderName
//            BigDecimal operationPrice = operationsRepo.findOperationPrice(folderName);
//            System.out.println("Тех операция: " + folderName + " цена: " + operationPrice);
//
//            // Получение вложенных папок, если они есть
//            JSONArray nestedFolders = (JSONArray) folder.get("folders"); //вложенные операции
//            if (nestedFolders != null && !nestedFolders.isEmpty()) {
//                processFolders(nestedFolders); // рекурсия рекурсивно проходится по массиву во всю его глубину
//                //    if (nestedFolders != null && !nestedFolders.isEmpty()) { - Это условие проверяет, что переменная nestedFolders не равна null и что она не пуста. То есть, если в текущей папке есть вложенные папки, условие будет истинным, и код внутри блока if будет выполнен.
//                //
//                //    processFolders(nestedFolders); - Это вызов метода processFolders(), который обрабатывает вложенные папки. Метод processFolders() принимает массив JSON объектов, представляющих вложенные папки, и рекурсивно обрабатывает их, вызывая себя же для каждой вложенной папки.
//                //
//                //Таким образом, эти строки кода позволяют рекурсивно обрабатывать структуру папок, проверяя наличие вложенных папок и обрабатывая их при необходимости.
//            }
//        }
//    }


}

