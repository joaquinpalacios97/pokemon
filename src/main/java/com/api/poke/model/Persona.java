package com.api.poke.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_persona;
    private String nombre;
    private String usuario;
    private String contrasena;
    @OneToOne
    @JoinColumn(name = "id_persona")
    private ImagenPersona imagenPersona;

    public Persona() {
    }

    public Persona(Long id_persona, String nombre, String usuario, String contrasena, ImagenPersona imagenPersona) {
        this.id_persona = id_persona;
        this.nombre = nombre;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.imagenPersona = imagenPersona;
    }
}
