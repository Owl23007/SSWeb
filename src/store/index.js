import { createStore } from 'vuex';

const store = createStore({
  state: {
    user: null,
    token: localStorage.getItem('jwt-token') || '',
    isLoggedIn: !!localStorage.getItem('jwt-token'),
  },
  mutations: {
    setUser(state, user) {
      state.user = user;
    },
    setToken(state, token) {
      state.token = token;
      localStorage.setItem('jwt-token', token);
    },
    logout(state) {
      state.user = null;
      state.token = null;
      state.isLoggedIn = false;
    },
    login(state, token) {
      state.token = token;
      state.isLoggedIn = true;
      localStorage.setItem('jwt-token', token);
    },
  },

  actions: {
    //登出
    async logout({ commit }) {
      localStorage.removeItem('jwt-token');
      commit('logout');
    },
    async fetchUserData({ commit, state }) {
      try {
        const response = await fetch('http://localhost:8080/user/userinfo', {
          method: 'GET',
          headers: {
            'Content-Type': 'application/json',
            'Authorization': `Duel ${state.token}`,
          },
        });

        if (!response.ok) {
          throw new Error('网络响应失败');
        }

        const data = await response.json();
        if (data.code === 0) {
          commit('setUser', data.data);
        } else {
          console.error('获取用户信息失败:', data.message);
        }
      } catch (error) {
        console.error('获取用户信息失败:', error);
      }
    },
  },
});

export default store;