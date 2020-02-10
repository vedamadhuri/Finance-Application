package com.application.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "GroupUsers")
public class GroupUser implements Serializable {


    @OneToMany(mappedBy = "groupUsers", cascade = CascadeType.ALL)
    List<Payments> paymentsList = new ArrayList<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "groupUserId")
    private long groupUserId;
    @ManyToOne
    @JoinColumn(name = "groupId")
    private Group groups;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User users;

    public long getGroupUserId() {
        return groupUserId;
    }

    public void setGroupUserId(long groupUserId) {
        this.groupUserId = groupUserId;
    }

    public Group getGroups() {
        return groups;
    }

    public void setGroups(Group groups) {
        this.groups = groups;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public List<Payments> getPaymentsList() {
        return paymentsList;
    }

    public void setPaymentsList(List<Payments> paymentsList) {
        this.paymentsList = paymentsList;
    }

}

