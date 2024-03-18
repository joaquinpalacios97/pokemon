/*package com.api.poke.controller;

import com.api.poke.model.ImagenPersona;
import com.api.poke.service.ImagenPersonaService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class ImagenPersoController {

    ImagenPersonaService imaPersoService;

    @GetMapping("/personaje/listar")
    public List<ImagenPersona> getAll() {
        List<ImagenPersona> newImaPerso = imaPersoService.getImagenPersona();
        for (ImagenPersona imaPer : newImaPerso) {
            imaPer.setImagenBase64(convertirImagenABase64(imaPer.getImagen()));
        }
        return newImaPerso;
    }

    private String convertirImagenABase64(byte[] imagen) {
        return Base64.getEncoder().encodeToString(imagen);
    }

    @RequestMapping("personaje/{id}")
    public ResponseEntity<ImagenPersona> findPersonaje(@PathVariable Long id) {
        try {
            ImagenPersona imaPerso = imaPersoService.searchImagenPerso(id);
            if (id != null) {
                return new ResponseEntity<>(imaPerso, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/personaje/crear")
    public ResponseEntity<ImagenPersona> saveImagenPersona(@RequestParam("imagen") MultipartFile imagen,
                                                           @RequestParam("nombre") String nombre) {
        try {
            ImagenPersona imaPerso = new ImagenPersona();
            imaPerso.setImagen(imagen.getBytes());
            imaPerso.setNombre(nombre);

            ImagenPersona savedImagenPerso = imaPersoService.saveImagenPersona(imaPerso);

            return new ResponseEntity<>(savedImagenPerso, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/personaje/editar/{id}")
    public ResponseEntity<ImagenPersona> editCharacter(@PathVariable Long id, @RequestBody ImagenPersona persojaneactualizado) {
        try {
            ImagenPersona imaPerso = imaPersoService.searchImagenPerso(id);

            if (imaPerso != null) {

                imaPerso.setNombre(persojaneactualizado.getNombre());
                imaPerso.setImagen(persojaneactualizado.getImagen());

                ImagenPersona personajeGuardado = imaPersoService.saveImagenPersona(imaPerso);

                return new ResponseEntity<>(imaPerso, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

        @DeleteMapping("/personaje/eliminar/{id}")
        public ResponseEntity<Map<String, String>> deleteImaPerso (@PathVariable Long id){
            imaPersoService.deleteImage(id);
            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "Ha sido eliminado");
            return ResponseEntity.ok().body(response);
        }

}*/