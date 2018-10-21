package com.example.user.smartmenu6.dto;

public class ToToken {
    String token;
    String role; //1 서빙 2 주방 3 관리자 4 소비자
    String key;


    public String getKey(){

        return key;
    }

    public void setKey(String key) {

        this.key=key;
    }



    public ToToken() {

    }

    public String getToken() {

        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
