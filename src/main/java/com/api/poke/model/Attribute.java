package com.api.poke.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Attribute {

    private UUID id;

    private int hp;

    private int attack;

    private int defense;

    private int sp_attack;

    private int sp_defense;

    private int speed;
}
