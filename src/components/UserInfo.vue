<template>
    <div class="profile-container">
      <div class="profile-header">
        <img :src="profile.avatar" alt="Avatar" class="avatar">
        <h1>{{ profile.name }}</h1>
        <p>{{ profile.bio }}</p>
      </div>
    </div>
  </template>
  
  <script>
  import { ref } from 'vue';
  
  export default {
    name: 'UserProfile',
    setup() {
      const profile = ref({
        avatar: 'https://via.placeholder.com/150',
        name: 'John Doe',
        bio: 'A passionate developer who loves coding and sharing knowledge.'
      });
  
      return {
        profile
      };
    }
  };
  </script>
  
  <style scoped>
  .profile-container {
    max-width: 600px;
    margin: 0 auto;
    padding: 20px;
    text-align: center;
    border: 1px solid #ddd;
    border-radius: 10px;
    background-color: #f9f9f9;
  }
  
  .profile-header {
    margin-bottom: 20px;
  }
  
  .avatar {
    width: 150px;
    height: 150px;
    border-radius: 50%;
    margin-bottom: 10px;
  }
  
  h1 {
    margin: 10px 0;
    font-size: 24px;
  }
  
  p {
    font-size: 16px;
    color: #666;
  }
  </style>