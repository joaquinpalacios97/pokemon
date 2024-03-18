/*package com.api.poke.controller;

import com.api.poke.model.Pokepersona;
import com.api.poke.service.PokepersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PokepersonaController {
    @Autowired
    private PokepersonaService pokepersoServi;

    @RequestMapping("/pokepersona/listar")
    public List<Pokepersona> getPokepersona(){
        return pokepersoServi.getPokeperso();
    }

    @PostMapping("/pokepersona/crear")
    public String savePokepersona(Pokepersona pokeperso){
        pokepersoServi.savePokeperso(pokeperso);
        return "pokeperso creado";
    }

    @RequestMapping("/pokepersona/{id}")
    public Pokepersona findPokepersona(@PathVariable Long id){
       return pokepersoServi.findPokeperso(id);
    }

    @DeleteMapping("/pokepersona/eliminar/{id}")
    public String deletePokepersona(@PathVariable Long id){
        pokepersoServi.deletePokeperso(id);
        return "se ha eliminado";
    }
}
*/