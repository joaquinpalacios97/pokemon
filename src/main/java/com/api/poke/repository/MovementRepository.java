package com.api.poke.repository;

import com.api.poke.model.PokemonType;
import com.api.poke.repository.entities.MovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MovementRepository extends JpaRepository <MovementEntity, UUID> {
    List<MovementEntity> findAllByType(PokemonType type);
}
