<template>
  <div class="user-card">
    <div class="background" :style="{ backgroundImage: `url(${user.background || defaultBackground})` }"></div>
    <div class="white-overlay"></div>
    <div class="avatar-container" @click="triggerFileInput">
      <img :src="user.userPic || defaultAvatar" alt="用户头像" class="avatar" />
    </div>
    <div class="user-details">
      <h2>{{ user.nickname }}</h2>
      <p>{{ user.signature }}</p>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    user: {
      type: Object,
      required: true
    }
  },
  data() {
    return {
      defaultAvatar: require('@/assets/default-avatar.png'),
      defaultBackground: require('@/assets/background.png')
    };
  },
};
</script>

<style scoped src="@/assets/css/usercard.css"></style>