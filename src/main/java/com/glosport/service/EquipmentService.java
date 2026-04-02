package com.glosport.service;

import com.glosport.dto.EquipmentDTO;
import com.glosport.utility.EquipmentException;
import com.glosport.utility.PlayerException;

public interface EquipmentService {

    Long addNewEquipment(Long playerId, EquipmentDTO equipmentDTO) throws PlayerException, EquipmentException;
    EquipmentDTO getEquipmentForPlayer(Long playerId, Long eqId) throws EquipmentException;
    void deleteEquipment(Long playerId, Long eqId) throws EquipmentException;
}
