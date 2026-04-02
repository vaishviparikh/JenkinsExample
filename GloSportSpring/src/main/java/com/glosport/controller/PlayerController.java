package com.glosport.controller;

import com.glosport.dto.PlayerDTO;
import com.glosport.service.PlayerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @PostMapping
    public ResponseEntity<String> registerNewPlayer(@Valid @RequestBody PlayerDTO player) {
        //Implement your code here.
        Long playerId = playerService.registerNewPlayer(player);
        return new ResponseEntity<>("New Player Registered with ID: " + playerId, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerDTO> getPlayer(@PathVariable Long id) {
        //Implement your code here.
        return new ResponseEntity<>(playerService.getPlayerById(id), HttpStatus.OK);
    }
}
