package com.api.poke.model;

import jakarta.persistence.Entity;
import lombok.*;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Ladder {

    private UUID id;

    private Status status;
}
