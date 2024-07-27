package com.api.poke.controller;

import com.api.poke.controller.presenters.ChallengePresenter;
import com.api.poke.controller.requests.CreateChallengeRequestDTO;
import com.api.poke.controller.requests.ReportBattleResultRequestDTO;
import com.api.poke.controller.responses.ChallengeResponseDTO;
import com.api.poke.model.Challenge;
import com.api.poke.model.Trainer;
import com.api.poke.service.BattleService;
import com.api.poke.service.ChallengeService;
import com.api.poke.service.TrainerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/challenges")
@CrossOrigin(origins = "http://localhost:4200")
public class ChallengeControler {


    private final ChallengeService challengeService;
    private final ChallengePresenter challengePresenter;
    private final TrainerService trainerService;
    private final BattleService battleService;
    @PostMapping("/create")
    public ResponseEntity<ChallengeResponseDTO> create(@Valid @RequestBody CreateChallengeRequestDTO requestDTO) {
        System.out.println("Received Challenger ID: " + requestDTO.getChallengerId());
        UUID challengerId = requestDTO.getChallengerId();
        ChallengeResponseDTO responseDTO = challengePresenter.toResponse(challengeService.createChallenge(challengerId));
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
    @PostMapping("/report-result")
    public ResponseEntity<Void> reportResult(@RequestBody ReportBattleResultRequestDTO requestDTO) {
        challengeService.reportBattleResult(requestDTO.getChallengerId(), requestDTO.getEnemyId(), requestDTO.isWon());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/battle/{challengerId}/{enemyId}/turn")
    public ResponseEntity<BattleService.BattleResult> battleTurn(
            @PathVariable UUID challengerId,
            @PathVariable UUID enemyId,
            @RequestBody UUID selectedMoveId) {

        Trainer challenger = trainerService.findById(challengerId);
        if (challenger == null) {
            throw new IllegalArgumentException("Challenger not found with id: " + challengerId);
        }
        Trainer enemy = trainerService.findById(enemyId);
        if (enemy == null) {
            throw new IllegalArgumentException("Enemy not found with id: " + enemyId);
        }
        BattleService.BattleResult result = battleService.battleTurn(challenger, enemy, selectedMoveId);
        return ResponseEntity.ok(result);
    }
}