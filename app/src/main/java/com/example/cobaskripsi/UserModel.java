package com.example.cobaskripsi;

public class UserModel {
    String nomortelpuser, role, username,iduser, emailuser, useridauth;

    public String getUseridauth() {
        return useridauth;
    }

    public void setUseridauth(String useridauth) {
        this.useridauth = useridauth;
    }

    public UserModel(String nomortelpuser, String role, String username, String iduser, String emailuser, String useridauth) {
        this.nomortelpuser = nomortelpuser;
        this.role = role;
        this.username = username;
        this.iduser = iduser;
        this.emailuser = emailuser;
        this.useridauth = useridauth;
    }


    public String getEmailuser() {
        return emailuser;
    }

    public void setEmailuser(String emailuser) {
        this.emailuser = emailuser;
    }

    UserModel(){

    }

    public String getNomortelpuser() {
        return nomortelpuser;
    }

    public void setNomortelpuser(String nomortelpuser) {
        this.nomortelpuser = nomortelpuser;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

}
