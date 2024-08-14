import {ref} from 'vue';

class LoginStatus
{
	public username: string;
    public email: string;
    public token: string;
	public is_login: boolean;

    constructor()
    {
        this.username = "";
        this.email = "";
        this.token = "";
        this.is_login = false;
    }
    public logout(){
        this.username = "";
        this.email = "";
        this.token = "";
        this.is_login = false;
    }
    public login(username: string, email: string, token: string){
        this.username = username;
        this.email = email;
        this.token = token;
        this.is_login = true;
    }
}

export const login_status = ref(new LoginStatus());