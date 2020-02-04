package com.application.dto;

import javax.validation.constraints.NotEmpty;

public class AdminDto {

    private long adminId;

    @NotEmpty(message = "Password cannot be empty")
    private String password;

    @NotEmpty(message = "Administrator cannot be empty")
    private String administrator;

    public String getAdministrator() {
        return administrator;
    }

    public void setAdministrator(String administrator) {
        this.administrator = administrator;
    }

    public long getAdminId() {
        return adminId;
    }

    public void setAdminId(long adminId) {
        this.adminId = adminId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
