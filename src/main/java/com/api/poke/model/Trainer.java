package com.api.poke.model;

import lombok.*;

import java.util.UUID;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Trainer {

    private UUID id;

    String name;

}
