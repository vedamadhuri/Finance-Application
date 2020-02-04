package com.application.service;

import com.application.dto.AdminDto;
import com.application.dto.GroupDto;
import com.application.dto.UserDto;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

public interface FinanceApplicationService {

    boolean isUserAuthenticated(AdminDto adminDto);

    String addUser(UserDto userDto, Model model, BindingResult result, String viewName);

    String addGroup(GroupDto groupDto, Model model, String viewName);

    String deleteUser(long userId, Model model, String viewName);

    String deleteGroupAndUser(String groupName, Model model, String viewName);

    String getUsersDetails(Model model, String viewName);

    String getGroupsDetails(Model model, String viewName);

    String updateUser(UserDto userDto, Model model, String viewName);

    String updateGroup(GroupDto groupDto, Model model, String viewName);

    String getUser(long userId, Model model, String viewName);

    String getGroup(long groupId, Model model, String viewName);

    String getPaymentDetails(long userId, Model model, String viewName);

    String getUsersByGroup(String groupName, Model model, String viewName);

    String lockGroup(long groupId, Model model, String viewName);

    String transactionMessage(long transId, long paymentId, Model model, String viewName);

    String calculatePaymentDetails(long paymentsId, long months, double amountPaid, double balanceAmount, double amount, String viewName, Model model);

    String transactionDetails(long paymentsId, double amountPaid, Model model, String viewName);

    String getPaymentsList(long id, Model model, String viewName);
}
