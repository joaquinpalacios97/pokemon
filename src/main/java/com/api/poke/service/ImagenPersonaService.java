package com.api.poke.service;

import com.api.poke.model.ImagenPersona;
import com.api.poke.repository.ImagenPersoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ImagenPersonaService implements IImagenPersonaService{

    @Autowired
    ImagenPersoRepository imagenPersoRepo;

    @Override
    public List<ImagenPersona> getImagenPersona() {
        return imagenPersoRepo.findAll();
    }

    @Override
    public void deleteImage(Long id) {
    imagenPersoRepo.deleteById(id);
    }

    @Override
    public ImagenPersona saveImagenPersona(ImagenPersona imagenPerso) {
        return imagenPersoRepo.save(imagenPerso);
    }

    public ImagenPersona updateImagenPerso (Long id, ImagenPersona imagenPerso){
        ImagenPersona existingImagenPerso = imagenPersoRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Imagen no encontrada"));

        existingImagenPerso.setNombre(imagenPerso.getNombre());
        existingImagenPerso.setImagen(imagenPerso.getImagen());

        return imagenPersoRepo.save(existingImagenPerso);
    }

    @Override
    public ImagenPersona searchImagenPerso(Long id) {
        return imagenPersoRepo.findById(id).orElse(null);
    }
}