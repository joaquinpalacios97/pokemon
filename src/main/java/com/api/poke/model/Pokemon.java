package com.api.poke.model;

import lombok.Data;

@Data
public class Pokemon {
    private Long id;
    private String name;
    private int experience;
    private int evolutionLevel;
    private boolean evolves;
    private byte[] image;

    public void evolve() {

    }
}
