package com.api.poke.repository;

import com.api.poke.repository.entities.AttributeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface AttributeRepository extends JpaRepository<AttributeEntity, UUID> {
}
