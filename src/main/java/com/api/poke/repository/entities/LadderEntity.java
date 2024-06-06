package com.api.poke.repository.entities;

import com.api.poke.model.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Ladder")
public class LadderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(columnDefinition = "VARCHAR(36)")
    private UUID id;

    private Status status;

    /*@OneToMany(mappedBy = "ladder")
    private List<ChallengeEntity> challenges;*/
}
