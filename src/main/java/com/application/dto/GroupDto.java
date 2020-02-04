package com.application.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class GroupDto {

    private long groupId;

    @NotEmpty(message = "Location cannot be empty")
    private String location;

    @NotEmpty(message = "GroupName cannot be empty")
    private String groupName;

    @NotNull(message = "Date cannot be null")
    private Timestamp createdDate;

    private double groupAmount;
    private long monthsDuration;
    private long groupSize;
    private GroupUserDto groupUserDto;
    private String groupLock;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getGroupAmount() {
        return groupAmount;
    }

    public void setGroupAmount(double groupAmount) {
        this.groupAmount = groupAmount;
    }

    public long getMonthsDuration() {
        return monthsDuration;
    }

    public void setMonthsDuration(long monthsDuration) {
        this.monthsDuration = monthsDuration;
    }

    public long getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(long groupSize) {
        this.groupSize = groupSize;
    }

    public GroupUserDto getGroupUserDto() {
        return groupUserDto;
    }

    public void setGroupUserDto(GroupUserDto groupUserDto) {
        this.groupUserDto = groupUserDto;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getGroupLock() {
        return groupLock;
    }

    public void setGroupLock(String groupLock) {
        this.groupLock = groupLock;
    }


}


