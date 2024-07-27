package com.api.poke.repository;

import com.api.poke.model.PokemonType;
import com.api.poke.repository.entities.MovementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface MovementRepository extends JpaRepository <MovementEntity, UUID> {
    List<MovementEntity> findAllByType(PokemonType type);
}
