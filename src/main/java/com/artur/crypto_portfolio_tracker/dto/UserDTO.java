package com.artur.crypto_portfolio_tracker.dto;

import jakarta.persistence.Column;

public class UserDTO {

    private int id;

    private String userName;

    private int enabled;

    public UserDTO(){}

    public UserDTO(int id, String userName, int enabled) {
        this.id = id;
        this.userName = userName;
        this.enabled = enabled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}

