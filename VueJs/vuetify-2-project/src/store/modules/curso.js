import axios from 'axios'
import auth from "./auth";
import {RepositoryFactory} from "../../repositories/RepositoryFactory";
const cursoRepo = RepositoryFactory.get("curso");

// state
const state = {
    cursos:[],
    curso: {
        id: 0,
        nome: '',
        sigla: '',
        turno: ''
    },
    usuarios:[],
    user:{
        id: 0,
        nome:'',
        email:'',
        curso:''
    }
};

// actions
const actions = {

    async addCurso({state, commit}){
        try {
            const res = await axios.post(
                '/cursos/add', state.curso,
                {headers: {Authorization: auth.state.token}})
            commit('clearCurso', res.data)

        }catch (errs){
            console.log(errs)
        }
    },
    async editarCurso({state, commit}){
        try {
            const res = await axios.put(
                `/cursos/${state.curso.id}`, state.curso,
                {headers: {Authorization: auth.state.token}})
            commit('clearCurso', res.data)
        }catch (errs){
            console.log(errs)
        }
    },

    async listCurso({state, commit}){
        const res = await cursoRepo.getAll()
        commit('setCursos', res.data)
    },

    async listUsers({state, commit}, c){
        commit('setCurso', c)
        try {
            const res = await axios.get(
                `/cursos/users/${state.curso.id}`,
                {headers: {Authorization: auth.state.token}})
            commit('setUsers', res.data)
        }catch (errs){
            console.log(errs)
        }
    },

    async setCurso({state, commit}, curso){
        commit('setCurso', curso);
    },

    async deletarCurso({state, commit}){
        try{
            const res = await axios.delete(`/cursos/${state.curso.id}`,{headers: {Authorization: auth.state.token}})
            commit('clearCurso')
        }catch (errs) {
            console.log(errs)
        }

    }
};

// getters
const getters = {

};

// mutations
const mutations = {
    setCursos (state, curso){
        state.cursos = curso;

    },
    setCurso(state, curso){
            state.curso.id = curso.id,
            state.curso.nome = curso.nome,
            state.curso.sigla = curso.sigla,
            state.curso.turno = curso.turno
    },
    clearCurso(state){
        state.curso.id = 0,
        state.curso.nome = '',
        state.curso.sigla = '',
        state.curso.turno = ''
    },
    setUsers(state, user){
        state.usuarios = user;
    },
    setUser(state, user){
           state.user.id = user.id,
            state.user.nome = user.nome,
            state.user.email = user.email,
            state.user.curso = user.curso
    },
};

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters
}
