package com.api.poke.model;

import com.api.poke.repository.entities.LadderEntity;
import com.api.poke.repository.entities.TrainerEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Challenge {

    private UUID id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ladder_id", referencedColumnName = "id")
    private Ladder ladders;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "enemy_trainer_id", referencedColumnName = "id")
    private Trainer enemy;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "challenger_id", referencedColumnName = "id")
    private Trainer challenger;

    private Status status;

    private ChallengeProgress progress;
    public Challenge(Trainer model, List<Trainer> enemies, Status status, ChallengeProgress challengeProgress) {
    }

    public Challenge(Trainer challenger, Trainer enemy, Status status, ChallengeProgress challengeProgress) {
        this.challenger = challenger;
        this.enemy = enemy;
        this.status = status;
        this.progress = challengeProgress;
    }

    public Challenge(Trainer challenger, List<Trainer> enemyModels, ChallengeProgress challengeProgress) {
    }

}
