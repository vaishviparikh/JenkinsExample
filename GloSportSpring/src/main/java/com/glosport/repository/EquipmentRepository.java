package com.glosport.repository;

import com.glosport.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    Optional<Equipment> findByEqIdAndPlayerPlayerId(Long eqId, Long playerId);
}
