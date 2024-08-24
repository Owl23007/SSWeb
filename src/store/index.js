import { ref } from 'vue';

const isLoggedIn = ref(!!localStorage.getItem('token'));

const login = (token) => {
  localStorage.setItem('token', token);
  isLoggedIn.value = true;
};

const logout = () => {
  localStorage.removeItem('token');
  isLoggedIn.value = false;
};

export default {
  isLoggedIn,
  login,
  logout
};