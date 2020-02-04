package com.application.dao;

import com.application.dto.AdminDto;
import com.application.model.*;
import org.springframework.ui.Model;

import java.util.List;

public interface FinanceApplicationDao {

    boolean isUserAuthenticated(AdminDto admin);

    User addUser(User userDao);

    Group addGroup(Group group);

    void deleteUser(long userId);

    List<User> getUsersDetails();

    List<Group> getGroupsDetails();

    List<User> getDetailsByGroupName(String groupname);

    void deleteGroupAndUser(String groupName);

    List<String> getGroupName();

    List<String> getEmailId();

    User updateUser(User user);

    Group updateGroup(Group group);

    TransactionHistory updateTransaction(TransactionHistory transactionHistories);

    User getUser(long userId);

    Group getGroup(long groupId);

    TransactionHistory getTransactionHistory(long transId);

    List<String> getUsersByGroupName(String groupName);

    List<Payments> getPaymentDetails(long id);

    Group groupDetailsByGroupName(String groupName);

    GroupUser addGroupUser(GroupUser groupUser);

    Long getMonthsDuration(String groupName);

    Payments addPaymentDetails(Payments payments);

    long updatePaymentDetails(long paymentsId, double amountPaid, Model model);

    int groupSize(String groupName);

    String getUserNameById(long id);

    String getGroupNameById(long id);

    TransactionHistory saveTransactionDetails(TransactionHistory transactionHistory);

    List<TransactionHistory> getTransactionDetails(long paymentsId);

    List<Payments> getPaymentsList(long id, Model model);

    List<Long> getMobileNumber();
}