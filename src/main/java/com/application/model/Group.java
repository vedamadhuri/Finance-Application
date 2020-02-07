package com.application.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groupsNames")
public class Group implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "groupId")
    private long groupId;

    @NotEmpty(message = "GroupName cannot be empty")
    @Column(name = "groupName")
    private String groupName;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "location")
    private String location;

    @Column(name = "groupAmount")
    private double groupAmount;

    @Column(name = "monthsDuration")
    private Long monthsDuration;

    @Column(name = "groupSize")
    private long groupSize;

    @Column(name = "grouplock")
    private String groupLock;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "groups", cascade = CascadeType.ALL)
    private List<GroupUser> groupUserList = new ArrayList<>();

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

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public List<GroupUser> getGroupUserList() {
        return groupUserList;
    }

    public void setGroupUserList(List<GroupUser> groupUserList) {
        this.groupUserList = groupUserList;
    }

    public double getGroupAmount() {
        return groupAmount;
    }

    public void setGroupAmount(double groupAmount) {
        this.groupAmount = groupAmount;
    }

    public long getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(long groupSize) {
        this.groupSize = groupSize;
    }

    public long getMonthsDuration() {
        return monthsDuration;
    }

    public void setMonthsDuration(long monthsDuration) {
        this.monthsDuration = monthsDuration;
    }

    public String getGroupLock() {
        return groupLock;
    }

    public void setGroupLock(String groupLock) {
        this.groupLock = groupLock;
    }


}
