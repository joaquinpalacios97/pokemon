package com.api.poke.repository;

import com.api.poke.model.Trainer;
import com.api.poke.repository.entities.TrainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface TrainerRepository extends JpaRepository<TrainerEntity, UUID > {
}
