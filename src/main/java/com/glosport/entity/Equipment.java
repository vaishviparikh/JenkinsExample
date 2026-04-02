package com.glosport.entity;

import jakarta.persistence.*;

@Entity
@Table(name="equipments")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="eq_id")
    private Long eqId;

    @Column(name="eq_type", nullable = false, length = 100)
    private String eqType;

    @Column(name="brand", nullable = false, length = 100)
    private String brand;

    @Column(name="model", length = 100)
    private String model;

    @ManyToOne
    @JoinColumn(name="player_id")
    private Player player;

    public Long getEqId() {
        return eqId;
    }

    public void setEqId(Long eqId) {
        this.eqId = eqId;
    }

    public String getEqType() {
        return eqType;
    }

    public void setEqType(String eqType) {
        this.eqType = eqType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
