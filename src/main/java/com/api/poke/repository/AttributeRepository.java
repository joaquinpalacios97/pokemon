package com.api.poke.repository;

import com.api.poke.repository.entities.AttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttributeRepository extends JpaRepository<AttributeEntity, UUID> {
}
