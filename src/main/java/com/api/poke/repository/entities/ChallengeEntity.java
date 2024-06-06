package com.api.poke.repository.entities;

import com.api.poke.model.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;
@Data
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ladder_id", referencedColumnName = "id")
    private LadderEntity ladders;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "enemy_trainer_id", referencedColumnName = "id")
    private TrainerEntity enemy;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "challenger_id", referencedColumnName = "id")
    private TrainerEntity challenger;

    private Status status;

}
