package com.api.poke.repository.mappers;

import com.api.poke.model.Attribute;
import com.api.poke.repository.entities.AttributeEntity;
import org.springframework.stereotype.Component;

@Component
public class AttributeEntityMapper {

    public AttributeEntity toEntity(Attribute attribute) {
        return AttributeEntity.builder()
                .hp(attribute.getHp())
                .attack(attribute.getAttack())
                .defense(attribute.getDefense())
                .sp_attack(attribute.getSp_attack())
                .sp_defense(attribute.getSp_defense())
                .speed(attribute.getSpeed())
                .build();
    }

    public Attribute toModel(AttributeEntity attributeEntity) {
        return Attribute.builder()
                .hp(attributeEntity.getHp())
                .attack(attributeEntity.getAttack())
                .defense(attributeEntity.getDefense())
                .sp_attack(attributeEntity.getSp_attack())
                .sp_defense(attributeEntity.getSp_defense())
                .speed(attributeEntity.getSpeed())
                .build();
    }
}