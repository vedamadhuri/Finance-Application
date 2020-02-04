package com.application.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usersDetails")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private long userId;

    @Column(name = "mobileNumber", unique = true)
    private long mobileNumber;

    @NotEmpty(message = "Status cannot be empty")
    @Column(name = "status")
    private String status;

    @NotEmpty(message = "UserName cannot be empty")
    @Column(name = "username")
    private String userName;

    @Column(name = "created_date")
    private Timestamp createdDate;


    @NotEmpty(message = "GroupName cannot be empty")
    @Column(name = "groupName")
    private String groupName;

    @Column(name = "editedBy")
    private String editedBy;

    @Email
    @NotEmpty(message = "EmailId cannot be empty")
    @Column(name = "emailId", unique = true)
    private String emailId;

    @Column(name = "grouplock_Status")
    private String grouplockStatus;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "users", cascade = CascadeType.ALL)
    private List<GroupUser> groupUserArrayList = new ArrayList<>();


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getEditedBy() {
        return editedBy;
    }

    public void setEditedBy(String editedBy) {
        this.editedBy = editedBy;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getGrouplockStatus() {
        return grouplockStatus;
    }

    public void setGrouplockStatus(String grouplockStatus) {
        this.grouplockStatus = grouplockStatus;
    }


    public List<GroupUser> getGroupUserArrayList() {
        return groupUserArrayList;
    }

    public void setGroupUserArrayList(List<GroupUser> groupUserArrayList) {
        this.groupUserArrayList = groupUserArrayList;
    }


}/*

package com.application.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usersDetails")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userId")
    private long userId;

    @Column(name = "mobileNumber",unique = true)
    private long mobileNumber;

    @NotEmpty(message = "Status cannot be empty")
    @Column(name="status")
    private String status;

    @NotEmpty(message = "UserName cannot be empty")
    @Column(name = "username")
    private String userName;

    @Column(name = "created_date")
    private Timestamp createdDate;

    public String getGrouplockStatus() {
        return grouplockStatus;
    }

    public void setGrouplockStatus(String grouplockStatus) {
        this.grouplockStatus = grouplockStatus;
    }

    @Column(name="grouplock_Status")
    private String grouplockStatus;


    @NotEmpty(message = "GroupName cannot be empty")
    @Column(name = "groupName")
    private String groupName;

    @Column(name = "editedBy")
    private String editedBy;

    @Email
    @NotEmpty(message = "EmailId cannot be empty")
    @Column(name="emailId",unique=true)
    private String emailId;

    @Column(name="grouplock_Status")
    private String grouplockStatus;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "users",cascade = CascadeType.ALL)
    private List<GroupUser> groupUserArrayList=new ArrayList<>();


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getEditedBy() {
        return editedBy;
    }

    public void setEditedBy(String editedBy) {
        this.editedBy = editedBy;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    public String getGrouplockStatus() {
        return grouplockStatus;
    }

    public void setGrouplockStatus(String grouplockStatus) {
        this.grouplockStatus = grouplockStatus;
    }


    public List<GroupUser> getGroupUserArrayList() {
        return groupUserArrayList;
    }

    public void setGroupUserArrayList(List<GroupUser> groupUserArrayList) {
        this.groupUserArrayList = groupUserArrayList;
    }




}

*/
