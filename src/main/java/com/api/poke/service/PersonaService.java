/*package com.api.poke.service;

import com.api.poke.model.Persona;
import com.api.poke.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PersonaService implements IPersonaService{
    @Autowired
    PersonaRepository persoRepo;
    @Override
    public List<Persona> getPersona() {
        return persoRepo.findAll();
    }

    @Override
    public void deletePersona(Long id) {
    persoRepo.deleteById(id);
    }

    @Override
    public void savePersona(Persona perso) {
    persoRepo.save(perso);
    }

    @Override
    public Persona findPersona(Long id) {
        return persoRepo.findById(id).orElse(null);
    }

    @Override
    public Persona findByUsuario(String usuario) {
        return persoRepo.findByUsuario(usuario);
    }
}
*/