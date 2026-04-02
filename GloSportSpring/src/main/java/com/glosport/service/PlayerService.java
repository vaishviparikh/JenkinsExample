package com.glosport.service;

import com.glosport.dto.PlayerDTO;
import com.glosport.utility.PlayerException;

public interface PlayerService {

    Long registerNewPlayer(PlayerDTO player) throws PlayerException;
    PlayerDTO getPlayerById(Long id) throws PlayerException;
}
