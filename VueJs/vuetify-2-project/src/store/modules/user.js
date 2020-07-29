import axios from 'axios'
import auth from "./auth";
import {RepositoryFactory} from "../../repositories/RepositoryFactory";
const usuarioRepo = RepositoryFactory.get("usuario");


// state
const state = {
    usuarios:[],
    user: {
        id: 0,
        curso: '',
        nome: '',
        email: '',
        password: '',
        habilitado: false
    }
};

// actions
const actions = {
    async addUser({state, commit}){
        try {
            const res = await axios.post(
                '/usuarios/add', state.user,
                {headers: {Authorization: auth.state.token}})
            commit('clearUser')
        }catch (errs){
            console.log(errs)
        }
    },

    async listUser({state, commit}){
        const res = await usuarioRepo.getAll()
        commit('setUsers', res.data)
    },
    async editarUser({state, commit}){
        try {
            const res = await axios.put(
                `/usuarios/${state.user.id}`, state.user,
                {headers: {Authorization: auth.state.token}})
            commit('clearUser', res.data)
        }catch (errs){
            console.log(errs)
        }
    },

    async setUser({state, commit}, user){
        commit('setUser', user);

    },
    async deletarUser({state, commit}){
        try{
            const res = await axios.delete(`/usuarios/${state.user.id}`,{headers: {Authorization: auth.state.token}})
            commit('clearUser')
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
    setUsers (state, usuarios){
        state.usuarios = usuarios
    },
    setUser(state, user){
        state.user.id = user.id,
        state.user.curso = user.curso,
        state.user.nome = user.nome,
        state.user.email = user.email,
        state.user.password = user.password,
        state.user.habilitado = user.habilitado
    },
    clearUser(state){
        state.user.id = '',
        state.user.nome = '',
        state.user.email = '',
        state.user.password = '',
        state.user.habilitado = '',
        state.user.curso = ''
    }
};

export default {
    namespaced: true,
    state,
    mutations,
    actions,
    getters
}
