package com.glosport.service;

import com.glosport.dto.EquipmentDTO;
import com.glosport.dto.PlayerDTO;
import com.glosport.entity.Equipment;
import com.glosport.entity.Player;
import com.glosport.repository.EquipmentRepository;
import com.glosport.repository.PlayerRepository;
import com.glosport.utility.EquipmentException;
import com.glosport.utility.PlayerException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private Environment env;

    @Override
    public Long addNewEquipment(Long playerId, EquipmentDTO equipmentDTO) throws PlayerException, EquipmentException {
        //Implement your code here.
        Player player= playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerException(env.getProperty("PlayerService.PLAYER_NOT_FOUND")+playerId));
        Equipment equipment=convertToEntity(equipmentDTO);
        equipment.setPlayer(player);
        Equipment savedEquipment= equipmentRepository.save(equipment);
        return savedEquipment.getEqId();
    }

    @Override
    public EquipmentDTO getEquipmentForPlayer(Long playerId, Long eqId) throws EquipmentException {
        //Implement your code here.
        if(!playerRepository.existsById(playerId)){
            throw new PlayerException(env.getProperty("PlayerService.PLAYER_NOT_FOUND")+playerId);
        }
        Equipment equipment = equipmentRepository.findByEqIdAndPlayerPlayerId(eqId,playerId)
                .orElseThrow(() -> new EquipmentException(env.getProperty("EquipmentService.EQUIPMENT_NOT_FOUND")+eqId));
        return convertToDTO(equipment);
    }

    @Override
    public void deleteEquipment(Long playerId, Long eqId) throws EquipmentException {
        //Implement your code here.
        if(!playerRepository.existsById(playerId)){
            throw new PlayerException(env.getProperty("PlayerService.PLAYER_NOT_FOUND")+playerId);
        }
        Equipment equipment = equipmentRepository.findByEqIdAndPlayerPlayerId(eqId,playerId)
                .orElseThrow(() -> new EquipmentException(env.getProperty("EquipmentService.EQUIPMENT_NOT_FOUND")+eqId));
        equipmentRepository.delete(equipment);
    }

    //Use these methods in your service methods to convert between DTO and Entity as needed.
    //Convert DTO to Entity
    private Equipment convertToEntity(EquipmentDTO equipmentDTO) {
        Equipment equipment = new Equipment();
        equipment.setEqType(equipmentDTO.getEqType());
        equipment.setBrand(equipmentDTO.getBrand());
        equipment.setModel(equipmentDTO.getModel());
        return equipment;
    }

    //Convert Entity to DTO
    public EquipmentDTO convertToDTO(Equipment equipment) {
        EquipmentDTO equipmentDTO = new EquipmentDTO();
        equipmentDTO.setEqId(equipment.getEqId());
        equipmentDTO.setEqType(equipment.getEqType());
        equipmentDTO.setBrand(equipment.getBrand());
        equipmentDTO.setModel(equipment.getModel());

        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setPlayerId(equipment.getPlayer().getPlayerId());
        playerDTO.setPlayerName(equipment.getPlayer().getPlayerName());
        playerDTO.setPlayerEmail(equipment.getPlayer().getPlayerEmail());

        equipmentDTO.setPlayer(playerDTO);
        return equipmentDTO;
    }
}
