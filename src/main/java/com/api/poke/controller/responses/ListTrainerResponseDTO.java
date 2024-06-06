package com.api.poke.controller.responses;


import com.api.poke.model.Pokemon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ListTrainerResponseDTO {
        List<TrainerResponseDTO> trainers;

}
