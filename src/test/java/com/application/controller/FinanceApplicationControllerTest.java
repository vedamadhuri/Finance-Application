package com.application.controller;

import com.application.dto.AdminDto;
import com.application.dto.GroupDto;
import com.application.dto.UserDto;
import com.application.exception.UserNotFoundException;
import com.application.service.FinanceApplicationServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;

import static org.mockito.ArgumentMatchers.anyString;

@RunWith(SpringJUnit4ClassRunner.class)
public class FinanceApplicationControllerTest {

    @InjectMocks
    private FinanceApplicationController financeApplicationController;

    @Mock
    private FinanceApplicationServiceImpl financeApplicationServiceImpl;

    @Mock
    private Model model;

    @Mock
    private BindingResult result;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void loginTest() {
        financeApplicationController.login();
    }

    @Test
    public void authenticateUserTest1() {
        AdminDto admindto = new AdminDto();
        admindto.setAdministrator("");
        admindto.setPassword("");
        Mockito.when(financeApplicationServiceImpl.isUserAuthenticated(admindto)).thenReturn(true);
        financeApplicationController.authenticateUser(admindto, model);
    }
    @Test
    public void authenticateUserTest2() {
        AdminDto adminDto = new AdminDto();
        adminDto.setAdministrator("admin");
        adminDto.setPassword("admin");
        Mockito.when(financeApplicationServiceImpl.isUserAuthenticated(adminDto)).thenReturn(true);
        financeApplicationController.authenticateUser(adminDto, model);
    }

    @Test
    public void authenticateUserTest3() {
        AdminDto adminDto = new AdminDto();
        adminDto.setPassword("test");
        adminDto.setAdministrator("test");
        String userName=adminDto.getAdministrator();
        String passWord=adminDto.getPassword();
        //Mockito.when((userName == "") ||(passWord == "")).thenReturn(false);
        Mockito.when(financeApplicationServiceImpl.isUserAuthenticated(adminDto)).thenReturn(false);
        financeApplicationController.authenticateUser(adminDto, model);
    }

    @Test
    public void detailsPageTest() {
        financeApplicationController.detailsPage(model);
    }

    @Test
    public void logoutTest() {
        financeApplicationController.logout();
    }

    @Test
    public void groupPageTest() {
        Mockito.when(financeApplicationServiceImpl.getGroupsDetails(model, "group")).thenReturn("group");
        financeApplicationController.groupPage(model);
    }

    @Test
    public void addGroupTest() {
        financeApplicationController.addGroup();
    }

    @Test
    public void addGroupDetailsTest() {
        GroupDto groupDto = new GroupDto();
        Mockito.when(financeApplicationServiceImpl.addGroup(groupDto, model, "group")).thenReturn("group");
        financeApplicationController.addGroupDetails(groupDto, model);
    }

    @Test
    public void deleteByGroupNameTest() {
        GroupDto groupDto = new GroupDto();
        Mockito.when(financeApplicationServiceImpl.deleteGroupAndUser("sample", model, "group")).thenReturn("test");
        financeApplicationController.deleteByGroupName("sample", groupDto, model);
    }



    @Test
    public void userDetailsTest() {
        Mockito.when(financeApplicationServiceImpl.getUsersDetails(model, "user")).thenReturn("test");
        financeApplicationController.userDetails(model);
    }

    @Test
    public void addUserTest() {
        UserDto userDto = new UserDto();
        Mockito.when(financeApplicationServiceImpl.addUser(userDto, model, result, "test")).thenReturn("test");
        financeApplicationController.addUser(model);
    }

    @Test
    public void addUserDetailsTest() {
        @Valid UserDto userDto = new UserDto();
        Mockito.when(financeApplicationServiceImpl.addUser(userDto, model, result, "user")).thenReturn("user");
        financeApplicationController.addUserDetails(userDto, result, model);
    }

    @Test
    public void deleteUserTest() throws UserNotFoundException {
        UserDto userDto = new UserDto();
        Mockito.when(financeApplicationServiceImpl.deleteUser(1L, model, "test")).thenReturn("test");
        financeApplicationController.deleteUser(1L, userDto, model);
    }

    @Test
    public void updateGroupTest() {
        Mockito.when(financeApplicationServiceImpl.getGroup(1L, model, "sample")).thenReturn("sample");
        financeApplicationController.updateGroup(1L, model);
    }

    @Test
    public void updateUserTest() {
        Mockito.when(financeApplicationServiceImpl.getUser(1L, model, "user")).thenReturn("user");
        financeApplicationController.updateUser(1L, model);
    }

    @Test
    public void updateUserDetailsTest() {
        UserDto userDto = new UserDto();
        Mockito.when(financeApplicationServiceImpl.updateUser(userDto, model, "sample")).thenReturn("sample");
        financeApplicationController.updateUserDetails(userDto, model,"sample");
    }

    @Test
    public void updateGroupDetailsTest() {
        GroupDto groupDto = new GroupDto();
        UserDto userDto = new UserDto();
        Mockito.when(financeApplicationServiceImpl.updateUser(userDto, model, "user")).thenReturn("user");
        financeApplicationController.updateGroupDetails(groupDto, model);
    }


    @Test
    public void displayUsersTest() {
        Mockito.when(financeApplicationServiceImpl.getUsersByGroup("test", model, "user")).thenReturn("user");
        financeApplicationController.displayUsers("test", model);
    }


    @Test
    public void paymentsTest() {
        Mockito.when(financeApplicationServiceImpl.getPaymentDetails(1L, model, "test")).thenReturn("test");
        UserDto userDto=new UserDto();
        financeApplicationController.payments(1L, model);
    }

    @Test
    public void submitAmountDetailsTest() {
        Mockito.when(financeApplicationServiceImpl.calculatePaymentDetails(1L, 10000, 10000,1000,1234,"test", model)).thenReturn("test");
        financeApplicationController.submitAmountDetails(10000, 1L,1000,1000,1234, model);
    }

    @Test
    public void lockGroupTest()
    {
        financeApplicationController.lockGroup(1L,model,"test");
    }

    @Test
    public void showTransactionDetailsTest()
    {
        financeApplicationController.showTransactionDetails(1L,1234,model);
    }

    @Test
    public void paymentDetailsTest()
    {
        financeApplicationController.paymentDetails(1,model);
    }

    @Test
    public void transactionMessageTest(){financeApplicationController.transactionMessage(1L,1L,model,"test");}

}