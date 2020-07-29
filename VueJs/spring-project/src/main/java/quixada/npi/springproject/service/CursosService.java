package quixada.npi.springproject.service;


import org.springframework.stereotype.Service;
import quixada.npi.springproject.model.Curso;
import quixada.npi.springproject.model.Usuario;

import java.util.List;

@Service
public interface CursosService {

    List<Curso> findAll();

    Curso addCurso(Curso curso);

    Curso findById(Integer id);

    Curso update(Curso curso);

    void delete(Curso curso);

    List<Usuario> findByUsers(Integer id);
}
