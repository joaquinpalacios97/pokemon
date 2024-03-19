package com.api.poke.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@Builder
@Data
@Getter
public class Gym {

    UUID id;

    String name;

    String type;

}
