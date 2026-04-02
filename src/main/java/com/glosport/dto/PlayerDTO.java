package com.glosport.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Map;

public class PlayerDTO {

    private Long playerId;

    @NotBlank(message="Player name is required")
    private String playerName;

    @NotBlank(message="Email is required")
    @Email(message="Please provide a valid email address")
    private String playerEmail;

    private Map<String, String> equipmentSummary;

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerEmail() {
        return playerEmail;
    }

    public void setPlayerEmail(String playerEmail) {
        this.playerEmail = playerEmail;
    }

    public Map<String, String> getEquipmentSummary() {
        return equipmentSummary;
    }

    public void setEquipmentSummary(Map<String, String> equipmentSummary) {
        this.equipmentSummary = equipmentSummary;
    }

    @Override
    public String toString() {
        return "PlayerDTO{" +
                "playerId=" + playerId +
                ", playerName='" + playerName + '\'' +
                ", playerEmail='" + playerEmail + '\'' +
                ", equipmentSummary=" + equipmentSummary +
                '}';
    }
}
