import store from '../store'
import LoginPage from '../views/Login'
import Home from '../views/Home'
import Curso from "../views/Curso";
import Usuario from "../views/Usuario";

export default [


    /* Geral */
    {
        path: '/',
        name: 'Home',
        component: Home
    },
    {
        path: '/login',
        meta: {
            public: true,
        },
        props: true,
        name: 'Login',
        component: LoginPage
    },
    {
        path: '/usuario',
        name: 'Usuario',
        component: Usuario
    },
    {
        path: '/curso',
        name: 'Curso',
        component: Curso
    }

];
