package quixada.npi.springproject.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quixada.npi.springproject.model.Curso;

import java.util.List;
import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer>{

    @Override
    List<Curso> findAll();

    @Override
    Optional<Curso> findById(Integer integer);
}
