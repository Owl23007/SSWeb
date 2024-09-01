import { createStore } from 'vuex';

const store = createStore({
  state: {
    user: localStorage.getItem('user') || null,
    token: localStorage.getItem('jwt-token') || '',
    isLoggedIn: localStorage.getItem('jwt-token') !== null && localStorage.getItem('user') !== null,
  },
  mutations: {
    setUser(state, user) {
      state.user = user;
      localStorage.setItem('user', state.user);
    },
    setToken(state, token) {
      state.token = token;
      localStorage.setItem('jwt-token', token);
    },
    logout(state) {
      state.token = '';
      state.isLoggedIn = false;
      localStorage.removeItem('user');
      localStorage.removeItem('jwt-token');
    },
    login(state, token) {
      state.token = token;
      state.isLoggedIn = true;
      localStorage.setItem('jwt-token', token);
      localStorage.setItem('user', state.user);
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

        const res = await response.json();
        if (res.code === 0) {
          commit('setUser', res.data);

        } else {
          console.error('获取用户信息失败:', res.message);
        }
      } catch (error) {
        console.error('获取用户信息失败:', error);
      }
    },
  },
});

export default store;