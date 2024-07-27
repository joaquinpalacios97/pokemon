package com.api.poke.repository.entities;

import com.api.poke.model.ChallengeProgress;
import com.api.poke.model.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChallengeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(columnDefinition = "VARCHAR(36)")
    private UUID id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "ladder_id", referencedColumnName = "id")
    private LadderEntity ladders;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "enemy_trainer_id", referencedColumnName = "id")
    private TrainerEntity enemy;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "challenger_id", referencedColumnName = "id")
    private TrainerEntity challenger;

    private ChallengeProgress progress;

    private Status status;

}
