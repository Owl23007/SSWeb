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
            <div class="not_title">
                <div class="avatar_setting">
                    <a>头像设置</a>
                    <div class="avatar">
                        <img :src="avatarPreview" alt="avatar" v-if="avatarPreview" />
                        <div v-else>
                            <input @change="getAvatarFileData" type="file" />
                            <svg width="150" height="150" viewBox="0 0 100 100" xmlns="http://www.w3.org/2000/svg">
                                <rect x="1" y="1" width="80" height="80" stroke="gray" stroke-width="1"
                                    stroke-dasharray="2,2" fill="none" />
                                <path d="M40 60 L40 13 M 10 30 L40 10 L70 30" stroke="gray" stroke-width="3"
                                    fill="none" />
                            </svg>
                        </div>


                        <button @click="uploadAvatarFile">上传头像</button>
                    </div>
                </div>
                <div class="background_setting">
                    <a>背景设置</a>
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

export default {
    name: 'CardSetting',
    setup() {
        // 获取文件
        const avatarFileInput = ref(null);
        const backgroundFileInput = ref(null);
        const image = ref(null);
        // 获取store
        const store = useStore();
        // 当前的状态
        const isAvatarLoad = ref(false);
        const isCardSetMode = ref(true);
        // 预览图片
        const avatarPreview = ref(null);
        const backgroundPreview = ref(null);

        // 获取头像文件
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

        // 获取背景文件
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

        // 上传头像文件
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

        // 上传背景文件
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

        // 关闭页面
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

<style scoped src='@/assets/css/cardsetting.css'></style>