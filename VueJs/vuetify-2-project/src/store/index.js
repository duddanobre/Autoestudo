import Vue from 'vue'
import Vuex from 'vuex'

import auth from './modules/auth'
import user from './modules/user'
import curso from './modules/curso'

Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    auth: auth,
    user: user,
    curso: curso
  }
})
