package com.api.poke.repository;


import com.api.poke.repository.entities.GymEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymRepository extends JpaRepository<GymEntity,Long> {
}
