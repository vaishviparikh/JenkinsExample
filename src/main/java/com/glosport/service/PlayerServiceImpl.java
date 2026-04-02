package com.glosport.service;

import com.glosport.dto.PlayerDTO;
import com.glosport.entity.Player;
import com.glosport.utility.PlayerException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import com.glosport.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private Environment env;

    @Override
    public Long registerNewPlayer(PlayerDTO playerDTO) throws PlayerException {
        //Implement your code here.
        if(playerRepository.findByPlayerEmail(playerDTO.getPlayerEmail()).isPresent()) {
            throw new PlayerException(env.getProperty("PlayerService.EMAIL_ALREADY_EXISTS")+playerDTO.getPlayerEmail());
        }
        Player savedPlayer = playerRepository.save(convertToEntity(playerDTO));
        return savedPlayer.getPlayerId();
    }

    @Override
    public PlayerDTO getPlayerById(Long id) throws PlayerException {
        //Implement your code here.
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new PlayerException(env.getProperty("PlayerService.PLAYER_NOT_FOUND")+id));
        return convertToDTO(player);
    }

    //Use these methods in your service methods to convert between DTO and Entity as needed.
    //Convert DTO to Entity
    private Player convertToEntity(PlayerDTO playerDTO) {
        Player player = new Player();
        player.setPlayerName(playerDTO.getPlayerName());
        player.setPlayerEmail(playerDTO.getPlayerEmail());
        player.setEquipments(null);
        return player;
    }

    //Convert Entity to DTO
    private PlayerDTO convertToDTO(Player player) {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setPlayerId(player.getPlayerId());
        playerDTO.setPlayerName(player.getPlayerName());
        playerDTO.setPlayerEmail(player.getPlayerEmail());

        Map<String, String> equipmentSummary = new HashMap<>();

        player.getEquipments().stream()
                .forEach(equipment -> equipmentSummary.put(equipment.getEqType(), equipment.getBrand() + " " + equipment.getModel()));

        playerDTO.setEquipmentSummary(equipmentSummary);

        return playerDTO;
    }
}
