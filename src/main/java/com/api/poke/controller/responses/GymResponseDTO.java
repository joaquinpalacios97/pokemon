package com.api.poke.controller.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@FieldDefaults(makeFinal = true)
public class GymResponseDTO {
    String name;
    String type;
}
