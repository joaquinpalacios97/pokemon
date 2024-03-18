/*package com.api.poke.service;

import com.api.poke.model.Pokepersona;
import com.api.poke.repository.PokepersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PokepersonaService implements  IPokepersonaService{
    @Autowired
    PokepersonaRepository pokepersoRepo;
    @Override
    public List<Pokepersona> getPokeperso() {
        return pokepersoRepo.findAll();
    }

    @Override
    public void savePokeperso(Pokepersona pokeper) {
    pokepersoRepo.save(pokeper);
    }

    @Override
    public void deletePokeperso(Long id) {
    pokepersoRepo.deleteById(id);
    }

    @Override
    public Pokepersona findPokeperso(Long id) {
        return pokepersoRepo.findById(id).orElse(null);
    }
}
*/