import usuarioRepository from "./usuarioRepository";
import cursoRepository from "./cursoRepository";


const repositories = {
    usuario: usuarioRepository,
    curso: cursoRepository
};

export const RepositoryFactory = {
    get: name => repositories[name]
};
