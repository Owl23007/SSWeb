<template>
  <div>
    <h1>用户中心</h1>
    <div v-if="user">
      <p>用户名: {{ user.username }}</p>
      <p>用户ID: {{ user.id }}</p>
      <!-- 其他用户信息 -->
    </div>
    <div v-else>
      <p>加载中...</p>
    </div>
  </div>
</template>

<script>
import { computed, onMounted } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';

export default {
  name: 'UserCenter',
  setup() {
    const store = useStore();
    const router = useRouter();
    const user = computed(() => store.state.user);
    const isLoggedIn = computed(() => store.state.isLoggedIn);


    onMounted(async () => {
       if (isLoggedIn.value) {
         await store.dispatch('fetchUserData');
       } else {
         router.push('/login');
       }
  },)
  
      return {
        user
      };
    }
};
</script>