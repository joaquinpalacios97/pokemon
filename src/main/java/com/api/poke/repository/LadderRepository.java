package com.api.poke.repository;

import com.api.poke.repository.entities.LadderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface LadderRepository extends JpaRepository<LadderEntity,UUID> {
}
