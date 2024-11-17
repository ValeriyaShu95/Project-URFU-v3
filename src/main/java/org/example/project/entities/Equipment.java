package org.example.project.entities;
import jakarta.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "Equipment")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameEquipment;
    private ArrayList<Long> operationId = new ArrayList<Long>();

    public Equipment() {
    }

    public Equipment(String nameEquipment, ArrayList<Long> operationId) {
        this.nameEquipment = nameEquipment;
        this.operationId = operationId;
    }
}



