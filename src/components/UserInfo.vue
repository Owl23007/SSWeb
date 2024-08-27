<template>
  <div class="container">
    <div class="left_container">
      <a class="title_text">投稿管理</a>
      <div class="left_container_article">
        <ul class="info_menu_list">
          <div class="info_menu_list_li" @click="to_send_article">
            <li><a class="text_button">我要投稿</a></li>
          </div>
          <div class="info_menu_list_li" @click="to_my_draft">
            <li><a class="text_button">我的草稿</a></li>
          </div>
          <div class="info_menu_list_li" @click="to_article_manage">
            <li><a class="text_button">稿件管理</a></li>
          </div>
        </ul>
      </div>
      <a class="title_text">信息管理</a>
      <div class="left_container_info">
        <ul class="info_menu_list">
          <div class="info_menu_list_li" @click="to_my_info">
            <li><a class="text_button">我的信息</a></li>
          </div>
          <div class="info_menu_list_li" @click="to_info_setting">
            <li><a class="text_button">信息设置</a></li>
          </div>
          <div class="info_menu_list_li" @click="logout">
            <li><a class="text_button">退出登陆</a></li>
          </div>
        </ul>
      </div>
    </div>
    <div class="right_container">
      <!-- 投稿页面 -->
      <from v-if="article">
        这是投稿页面
      </from>
      <!-- 草稿页面 -->
      <form v-if="draft">
        这是草稿页面
      </form>
      <!-- 稿件管理页面 -->
      <form v-if="manage">
        这是管理页面
      </form>
      <!-- 用户信息页面 -->
      <form v-if = "info">
        <div v-if="user">
          <p>用户名: {{ user.username }}</p>
          <p>用户ID: {{ user.id }}</p>
          <!-- 其他用户信息 -->
        </div>
        <div v-else>
          <p>加载中...</p>
        </div>
      </form>
      <!-- 信息设置页面 -->
      <form v-if = "setting">
        这是信息设置页面
      </form>
    </div>
  </div>

</template>

<script>
import { ref, computed, onMounted } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';

export default {
  name: 'UserCenter',
  setup() {
    const store = useStore();
    const router = useRouter();
    const user = computed(() => store.state.user);
    const isLoggedIn = computed(() => store.state.isLoggedIn);
    const article = ref(false);
    const draft = ref(false);
    const manage = ref(false);
    const info = ref(true);
    const setting = ref(false);


    onMounted(async () => {
      if (isLoggedIn.value) {
        await store.dispatch('fetchUserData');
      } else {
        router.push('/login');
      }
    },)

    const to_send_article = () => {
      // 将右边的页面转换成发布文章页面
      article.value = true;
      draft.value = false;
      manage.value = false;
      info.value = false;
      setting.value = false;
    };

    const to_my_draft = () => {
      // 将右边的页面转换成我的草稿页面
      draft.value = true;
      article.value = false;
      manage.value = false;
      info.value = false;
      setting.value = false;
    };

    const to_article_manage = () => {
      // 将右边的页面转换成文章管理页面
      manage.value = true;
      article.value = false;
      draft.value = false;
      info.value = false;
      setting.value = false;
    };

    const to_my_info = () => {
      // 将右边的页面转换成我的信息页面
      info.value = true;
      article.value = false;
      draft.value = false;
      manage.value = false;
      setting.value = false;
    };

    const to_info_setting = () => {
      // 将右边的页面转换成信息设置页面
      setting.value = true;
      article.value = false;
      draft.value = false;
      manage.value = false;
      info.value = false;
    };

    const logout = () => {
      // 退出登陆
      store.dispatch('logout');
      router.push('/');
    };

    return {
      user, to_send_article, to_my_draft, to_article_manage, to_my_info, to_info_setting, logout, article, draft, manage, info, setting
    };
  }
};
</script>

<style scoped src="@/assets/css/userinfo.css"></style>