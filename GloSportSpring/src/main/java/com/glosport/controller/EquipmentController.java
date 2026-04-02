package com.glosport.controller;

import com.glosport.dto.EquipmentDTO;
import com.glosport.service.EquipmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @PostMapping("/{playerId}")
    public ResponseEntity<String> addNewEquipment(@PathVariable Long playerId, @Valid @RequestBody EquipmentDTO equipmentDTO) {
        //Implement your code here.
        Long eqId = equipmentService.addNewEquipment(playerId, equipmentDTO);
        return new ResponseEntity<>("New Equipment Added with ID: " + eqId, HttpStatus.CREATED);
    }

    @GetMapping("/{playerId}/{eqId}")
    public ResponseEntity<EquipmentDTO> getEquipmentForPlayer(@PathVariable Long playerId,@PathVariable Long eqId) {
        //Implement your code here.
        return new ResponseEntity<>(equipmentService.getEquipmentForPlayer(playerId, eqId), HttpStatus.OK);
    }

    @DeleteMapping("/{playerId}/{eqId}")
    public ResponseEntity<String> deleteEquipment(@PathVariable Long playerId, @PathVariable Long eqId) {
        //Implement your code here.
        equipmentService.deleteEquipment(playerId, eqId);
        return new ResponseEntity<>("Equipment with ID: "+eqId+"deleted for player ID: "+playerId, HttpStatus.OK);
    }
}
