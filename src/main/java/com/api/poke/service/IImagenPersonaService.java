package com.api.poke.service;

import com.api.poke.model.ImagenPersona;

import java.util.List;

public interface IImagenPersonaService {
    List<ImagenPersona> getImagenPersona ();
    void deleteImage(Long id);
    ImagenPersona saveImagenPersona(ImagenPersona imagenPerso);
    ImagenPersona searchImagenPerso (Long id);

}
