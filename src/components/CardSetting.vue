<template>
    <div class="background" v-if="isCardSetMode">
        <div class="card_setting_container">
            <div class="title">
                <h1>个人卡片设置</h1>
                <div class="close-button" @click="closepage">
                    <svg width="20" height="20" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
                        <line x1="1" y1="1" x2="20" y2="20" stroke="gray" stroke-width="2" />
                        <line x1="1" y1="20" x2="20" y2="1" stroke="gray" stroke-width="2" />
                    </svg>
                </div>
            </div>
            <UserCard />
            <div class="not_title">
                <div class="avatar_setting">
                    <h1>头像设置</h1>
                    <div class="avatar">
                        <img :src="avatarPreview" alt="avatar" v-if="avatarPreview" />
                        <div v-else>
                            <svg width="20" height="20" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
                                <rect x="1" y="1" width="18" height="18" stroke="gray" stroke-width="1" stroke-dasharray="2,2" fill="none" />
                                <path d="M10 5 L10 13 M7 8 L10 5 L13 8" stroke="gray" stroke-width="1" fill="none" />
                            </svg>
                        </div>
                        <input @change="getAvatarFileData" type="file" />
                        <vue-cropper ref="cropper" :src="image" :aspect-ratio="1" :auto-crop="true" :view-mode="1"
                            :background="false" :guides="true" :crop-box-resizable="true" :crop-box-movable="true"
                            :drag-mode="'crop'" />
                        <button @click="uploadAvatarFile">上传头像</button>
                    </div>
                </div>
                <div class="background_setting">
                    <h1>背景设置</h1>
                    <div class="background_preview">
                        <img :src="backgroundPreview" alt="background" v-if="backgroundPreview" />
                    </div>
                    <input @change="getBackgroundFileData" type="file" />
                    <button @click="uploadBackgroundFile">上传</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { uploadFile_post } from '../assets/script/file_request.js';
import { updateAvatar_patch, updateBackground_patch } from '../assets/script/user_request.js';
import { ref } from 'vue';
import { useStore } from 'vuex';
import VueCropper from 'vue-cropper';

export default {
    name: 'CardSetting',
    components: {
        VueCropper
    },
    setup() {
        const avatarFileInput = ref(null);
        const backgroundFileInput = ref(null);
        const image = ref(null);
        const store = useStore();
        const isAvatarLoad = ref(false);
        const isCardSetMode = ref(true);
        const avatarPreview = ref(null);
        const backgroundPreview = ref(null);

        const getAvatarFileData = (event) => {
            const file = event.target.files || event.dataTransfer.files;
            if (file.length > 0) {
                avatarFileInput.value = file[0];
                const reader = new FileReader();
                reader.onload = (e) => {
                    avatarPreview.value = e.target.result;
                };
                reader.readAsDataURL(file[0]);
                isAvatarLoad.value = true;
            }
        };

        const getBackgroundFileData = (event) => {
            const file = event.target.files || event.dataTransfer.files;
            if (file.length > 0) {
                backgroundFileInput.value = file[0];
                const reader = new FileReader();
                reader.onload = (e) => {
                    backgroundPreview.value = e.target.result;
                };
                reader.readAsDataURL(file[0]);
            }
        };

        const uploadAvatarFile = async () => {
            const formData = new FormData();
            formData.append('file', avatarFileInput.value);
            const res = await uploadFile_post(store.state.token, formData);
            console.log(res.data);
            if (res.code === 0) {
                await updateAvatar_patch(store.state.token, res.data);
                await store.dispatch('fetchUserData');
                alert("更新成功！");
            } else {
                alert("更新失败！");
            }
        };

        const uploadBackgroundFile = async () => {
            const formData = new FormData();
            formData.append('file', backgroundFileInput.value);
            const res = await uploadFile_post(store.state.token, formData);
            console.log(res.data);
            if (res.code === 0) {
                await updateBackground_patch(store.state.token, res.data);
                await store.dispatch('fetchUserData');
                alert("更新成功！");
            } else {
                alert("更新失败！");
            }
        };

        const closepage = async () => {
            await store.dispatch('setcartsettingmode', false);
        };

        return {
            closepage,
            getAvatarFileData,
            getBackgroundFileData,
            uploadAvatarFile,
            uploadBackgroundFile,
            image,
            isAvatarLoad,
            isCardSetMode,
            avatarPreview,
            backgroundPreview
        };
    }
};
</script>

<style scoped>
.background {
    position: absolute;
    top: 0;
    left: 0;
    background-color: rgba(0, 0, 0, 0.5);
    height: 100%;
    width: 100%;
    z-index: 5;
    display: flex;
    justify-content: center;
    align-items: center;
}

.title {
    margin-top: 20px;
    display: flex;
    justify-content: space-between;
}

.close-button {
    margin-right: 20px;
}

.not_title {
    height: 80%;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    align-items: center;
}

.card_setting_container {
    height: 60%;
    width: 50%;
    border-radius: 10px;
    background-color: white;
    display: flex;
    flex-direction: column;
    margin-top: 50px;
    box-shadow: 0 4px 4px rgba(0, 0, 0, 0.1);
}

.card_setting_container h1 {
    margin-left: 20px;
}

.avatar_setting,
.background_setting {
    display: flex;
    flex-direction: column;
    align-items: center;
}

.avatar img,
.background_preview img {
    max-width: 100%;
    max-height: 200px;
    border-radius: 10px;
    margin-bottom: 10px;
}
</style>