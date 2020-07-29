package quixada.npi.springproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quixada.npi.springproject.model.Curso;
import quixada.npi.springproject.model.Usuario;
import quixada.npi.springproject.repository.CursoRepository;
import quixada.npi.springproject.repository.UsuarioRepository;
import quixada.npi.springproject.service.CursosService;

import java.util.List;

@Service
public class CursoServImpl implements CursosService {
    @Autowired
    private CursoRepository cursoRepo;

    @Autowired
    private UsuarioRepository userRepo;

    @Override
    public List<Curso> findAll() {
        return cursoRepo.findAll();
    }

    @Override
    public Curso addCurso(Curso curso) {
        return cursoRepo.save(curso);
    }

    @Override
    public Curso findById(Integer id) {
        return cursoRepo.findById(id).get();
    }

    @Override
    public Curso update(Curso curso) {
        return cursoRepo.save(curso);
    }

    @Override
    public void delete(Curso curso) {
        cursoRepo.delete(curso);
    }

    @Override
    public List<Usuario> findByUsers(Integer id) {
        return userRepo.findAllById(id);
    }
}
