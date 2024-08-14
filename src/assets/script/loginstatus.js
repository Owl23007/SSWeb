import {ref} from 'vue';

class LoginStatus
{
	username = ref("");
    email= ref("") ;
    token= ref("") ;
	is_login= ref(false) ;
    constructor(){
        this.username = "";
        this.email = "";
        this.token = "";
        this.is_login = false;
    }
     async logout(){
        this.username = "";
        this.email = "";
        this.token = "";
        this.is_login = false;
    }
    async login(username, email, token){
        this.username = username;
        this.email = email;
        this.token = token;
        this.is_login = true;
    }
}

export const login_status = ref(new LoginStatus());