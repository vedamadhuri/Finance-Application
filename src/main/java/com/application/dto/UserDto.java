package com.application.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class UserDto {

    private long userId;

    @NotEmpty(message = "Username cannot be Empty")
    private String userName;

    @NotNull(message = "GroupName cannot be Null")
    private String groupName;


    private String grouplockStatus;

    private Timestamp createdDate;

    private long mobileNumber;

    @NotNull(message = "status cannot be Null")
    private String status;

    @NotEmpty(message = "EmailId cannot be Empty")
    private String emailId;

    private String editedBy;

    private GroupUserDto groupUserDto;


    public String getEditedBy() {
        return editedBy;
    }

    public void setEditedBy(String editedBy) {
        this.editedBy = editedBy;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public GroupUserDto getGroupUserDto() {
        return groupUserDto;
    }

    public void setGroupUserDto(GroupUserDto groupUserDto) {
        this.groupUserDto = groupUserDto;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getGrouplockStatus() {
        return grouplockStatus;
    }

    public void setGrouplockStatus(String grouplockStatus) {
        this.grouplockStatus = grouplockStatus;
    }
}
