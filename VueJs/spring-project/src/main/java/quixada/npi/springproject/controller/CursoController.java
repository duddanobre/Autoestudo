package quixada.npi.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import quixada.npi.springproject.model.Curso;
import quixada.npi.springproject.model.Usuario;
import quixada.npi.springproject.service.CursosService;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursosService cursosService;

    @GetMapping("")
    public ResponseEntity<List<Curso>> findAll(){

        return ResponseEntity.ok(cursosService.findAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Curso> find(@PathVariable Integer id) {
        // Busca curso pelo id e retornar curso...
        return ResponseEntity.ok(cursosService.findById(id));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<List<Usuario>> findByUsers(@PathVariable Integer id) {
        // Busca curso pelo id e retornar curso...
        return ResponseEntity.ok(cursosService.findByUsers(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Curso> create(@RequestBody Curso curso) {
        return ResponseEntity.ok(cursosService.addCurso(curso));
    }
    @PutMapping("{id}")
    public ResponseEntity<Curso> update(@RequestBody Curso curso) {
        return ResponseEntity.ok(cursosService.update(curso));
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable Integer id){
        Curso c = cursosService.findById(id);
        cursosService.delete(c);
        ResponseEntity.ok().build();
    }
}
