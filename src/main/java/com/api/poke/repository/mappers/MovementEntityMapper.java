package com.api.poke.repository.mappers;

import com.api.poke.model.Movement;
import com.api.poke.repository.entities.MovementEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class MovementEntityMapper {

    public MovementEntity toEntity(Movement movement){
        MovementEntity entity = new MovementEntity();
        entity.setId(movement.getId());
        entity.setName(movement.getName());
        entity.setType(movement.getType());
        entity.setPower(movement.getPower());
        entity.setAccuracy(movement.getAccuracy());
        entity.setPp(movement.getPp());
        return entity;
    }

    public Movement toModel(MovementEntity movementEntity){
        Movement model = new Movement();
        model.setId(movementEntity.getId());
        model.setName(movementEntity.getName());
        model.setType(movementEntity.getType());
        model.setPower(movementEntity.getPower());
        model.setAccuracy(movementEntity.getAccuracy());
        model.setPp(movementEntity.getPp());
        return model;
    }

}
