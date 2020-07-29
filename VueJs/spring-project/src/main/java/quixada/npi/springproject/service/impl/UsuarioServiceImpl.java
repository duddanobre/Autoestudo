package quixada.npi.springproject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import quixada.npi.springproject.model.Usuario;
import quixada.npi.springproject.repository.UsuarioRepository;
import quixada.npi.springproject.service.UsuarioService;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Usuario findByEmail(String email) {

        return usuarioRepository.findByEmail(email);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario addUser(Usuario user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return usuarioRepository.save(user);
    }

    @Override
    public Usuario update(Usuario user) {
        return usuarioRepository.save(user);
    }

    @Override
    public void delete(Usuario user) {
        usuarioRepository.delete(user);
    }

    @Override
    public Usuario findById(Integer id) {
        return usuarioRepository.findById(id).get();
    }
}