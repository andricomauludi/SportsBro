package com.example.cobaskripsi;

public class UserModel {
    String nomortelpuser, password, role, username,iduser, emailuser;

    public UserModel(String nomortelpuser, String password, String role, String username, String iduser, String emailuser) {
        this.nomortelpuser = nomortelpuser;
        this.password = password;
        this.role = role;
        this.username = username;
        this.iduser = iduser;
        this.emailuser = emailuser;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
