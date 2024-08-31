<template>

    <input @change="getFileData" type="file" />
    <button @click="uploadFile">上传</button>

</template>

<script>
import { uploadFile_post } from '../assets/script/file_request.js';
import { updateAvatar_patch } from '../assets/script/user_request.js';
import { ref } from 'vue';
import { useStore } from 'vuex';

export default {
    name: 'CardSetting',
    setup() {
        const fileInput = ref(null);
        const store = useStore();

        const getFileData = (event) => {
            const file = event.target.files || event.dataTransfer.files;
            if (file.length > 0) {
                fileInput.value = file[0];
            }
        }
        const uploadFile = async () => {
            const formData = new FormData();
            formData.append('file', fileInput.value);
            const res = await uploadFile_post(store.state.token, formData);
            console.log(res.data);
            if (res.code === 0) {
                await updateAvatar_patch(store.state.token, res.data);
                await store.dispatch('fetchUserData');
                alert("更新成功！");
            }
            else {
                alert("更新失败！");
            }
        }
        return {
            getFileData, uploadFile
        }
    }
}
</script>

<style></style>