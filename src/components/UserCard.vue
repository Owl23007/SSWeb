<template>
    <div class="user-card">
      <div class="background" :style="{ backgroundImage: `url(${user.background || defaultBackground})` }"></div>
      <div class="white-overlay"></div>
      <div class="avatar-container">
        <img :src="user.avatar || defaultAvatar" alt="用户头像" class="avatar" />
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
    methods: {
      triggerFileInput() {
        this.$refs.fileInput.click();
      }
    }
  };
  </script>
  
  <style scoped>
  .user-card {
    position: relative;
    width: 300px;
    height: 200px;
    border-radius: 15px;
    overflow: hidden;
    color: black;
    text-align: center;
    padding: 20px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  }
  
  .background {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 50%;
    background-size: cover;
    background-position: center;
    z-index: 1;
  }
  
  .white-overlay {
    position: absolute;
    bottom: 0;
    left: 0;
    width: 100%;
    height: 50%;
    background-color: white;
    z-index: 1;
  }
  
  .avatar-container {
    position: relative;
    margin-bottom: 20px;
    z-index: 2;
  }
  
  .avatar {
    width: 100px;
    height: 100px;
    border-radius: 50%;
    position: relative;
    margin-top:50px;
    margin-left: -200px;
  }
  
  .user-details {
    position: relative;
    z-index: 2;
    
  }
  
  .user-details h2 {
    margin: 10px 0 5px;
  }
  
  .user-details p {
    margin: 0;
    font-style: italic;
  }
  </style>