package quixada.npi.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import quixada.npi.springproject.model.Usuario;

import java.util.List;
import java.util.Optional;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

  //  @Query("SELECT new Usuario(u.id, u.nome, u.email, u.habilitado, u.curso) FROM Usuario u")
    Usuario findByEmail(String email);

    List<Usuario> findAll();

    @Override
    Optional<Usuario> findById(Integer integer);

    List<Usuario> findAllById(Integer id);
}
