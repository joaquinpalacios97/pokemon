/*package com.api.poke.controller;

import com.api.poke.model.Persona;
import com.api.poke.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private PersonaService persoServi;

    @RequestMapping("/personas/listar")
    public List<Persona> getPersona(){
        return persoServi.getPersona();
    }

    @PostMapping("/persona/crear")
    public String savePersona (@RequestBody Persona perso){
        persoServi.savePersona(perso);
        return "Persona creada";
    }

    @RequestMapping("/persona/{id}")
    public Persona findPersona(@PathVariable Long id){
        return persoServi.findPersona(id);
    }


    @PostMapping("/persona/login")
    public String login(@RequestParam String usuario, @RequestParam String contrasena) {
        Persona persona = persoServi.findByUsuario(usuario);
        if (persona != null && persona.getContrasena().equals(contrasena)) {
            return "Inicio de sesión exitoso";
        } else {
            return "Nombre de usuario o contraseña incorrectos";
        }
    }

    @DeleteMapping("/persona/eliminar/{id}")
    public String deletePersona(@PathVariable Long id){
        persoServi.deletePersona(id);
        return "se ha eliminado";
    }
}*/