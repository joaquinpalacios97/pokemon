package com.api.poke.repository;

import com.api.poke.model.Trainer;
import com.api.poke.repository.entities.TrainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TrainerRepository extends JpaRepository<TrainerEntity, UUID > {
}
