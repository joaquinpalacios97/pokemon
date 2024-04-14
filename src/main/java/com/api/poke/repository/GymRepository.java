package com.api.poke.repository;


import com.api.poke.repository.entities.GymEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GymRepository extends JpaRepository<GymEntity, UUID> {
}
