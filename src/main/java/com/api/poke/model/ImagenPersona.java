/*package com.api.poke.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Table(name = "imagen_persona")
@Entity
public class ImagenPersona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_character;
    private String nombre;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] imagen;
    private String imagenBase64;

    public ImagenPersona() {
    }

    public ImagenPersona(Long id_character, String nombre, byte[] imagen, String imagenBase64) {
        this.id_character = id_character;
        this.nombre = nombre;
        this.imagen = imagen;
        this.imagenBase64 = imagenBase64;
    }
}
*/