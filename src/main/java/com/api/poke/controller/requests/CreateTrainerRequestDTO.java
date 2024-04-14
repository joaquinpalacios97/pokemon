package com.api.poke.controller.requests;


import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor(force = true)
@FieldDefaults(makeFinal = true)
public class CreateTrainerRequestDTO {

    @NotEmpty(message = "Name cannot be empty")
    String name;


}