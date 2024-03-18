/*package com.api.poke.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Pokepersona {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_persona")
    private Persona perso;
    @ManyToOne
    @JoinColumn(name ="id_pokemon")
    private Pokemon poke;
    private int nivel;
    private int experiencia;

    public Pokepersona() {
    }
    public Pokepersona(Long id, Persona perso, Pokemon poke, int nivel, int experiencia) {
        this.id = id;
        this.perso = perso;
        this.poke = poke;
        this.nivel = nivel;
        this.experiencia = experiencia;
    }
}*/
