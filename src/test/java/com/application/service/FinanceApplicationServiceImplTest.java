package com.application.service;

import com.application.dao.FinanceApplicationDaoImpl;
import com.application.dto.AdminDto;
import com.application.dto.GroupDto;
import com.application.dto.GroupUserDto;
import com.application.dto.UserDto;
import com.application.model.*;
import com.application.repository.GroupUserRepository;
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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class FinanceApplicationServiceImplTest {

    @InjectMocks
    private FinanceApplicationServiceImpl financeApplicationService;

    @Mock
    private FinanceApplicationDaoImpl financeApplicationDao;

    @Mock
    private Model model;
    @Mock
    private GroupUserRepository groupUserRepository;

    @Mock
    private BindingResult result;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void isUserAuthenticatedTest() {
        AdminDto adminDto = new AdminDto();
        Mockito.when(financeApplicationDao.isUserAuthenticated(adminDto)).thenReturn(true);
        financeApplicationService.isUserAuthenticated(adminDto);
    }


    @Test
    public void addUserTest1() {
        UserDto userDto = new UserDto();
        Group group = new Group();
        User user = new User();
        GroupUser groupUser = new GroupUser();
        Payments payments = new Payments();
        Mockito.when(financeApplicationDao.groupDetailsByGroupName("sample")).thenReturn(group);
        Mockito.when(financeApplicationDao.addUser(user)).thenReturn(user);
        Mockito.when(financeApplicationDao.addGroupUser(groupUser)).thenReturn(groupUser);
        Mockito.when(financeApplicationDao.addPaymentDetails(payments)).thenReturn(payments);
        financeApplicationService.addUser(userDto, model, result, "user");
    }

    @Test
    public void addUserTest2() {
        UserDto userDto = new UserDto();
        userDto.setEmailId("test@gmail.com");
        List<String> output = new ArrayList<>();
        output.add("test@gmail.com");
        Mockito.when(financeApplicationDao.getEmailId()).thenReturn(output);
        financeApplicationService.addUser(userDto, model, result, "user");
    }


    @Test
    public void addUserTest3() {
        UserDto userDto = new UserDto();
        Group group = new Group();
        group.setMonthsDuration(10L);
        User user = new User();
        GroupUser groupUser = new GroupUser();
        userDto.setEmailId("sample@gmail.com");
        List<String> output = new ArrayList<>();
        output.add("test@gmail.com");
        Mockito.when(financeApplicationDao.getEmailId()).thenReturn(output);
        Mockito.when(financeApplicationDao.groupDetailsByGroupName("sample")).thenReturn(group);
        Mockito.when(financeApplicationDao.addUser(user)).thenReturn(user);
        Mockito.when(financeApplicationDao.addGroupUser(groupUser)).thenReturn(groupUser);
        // Mockito.when(financeApplicationDao.getMonthsDuration("test")).thenReturn(10L);
        financeApplicationService.addUser(userDto, model, result, "user");
    }

    @Test
    public void addUserTest4() {
        UserDto userDto = new UserDto();
        Mockito.when(result.hasErrors()).thenReturn(true);
        financeApplicationService.addUser(userDto, model, result, "test");
    }

    @Test
    public void addUserTest5() {
        UserDto userDto = new UserDto();
        userDto.setGroupName("sample");
        Mockito.when(result.hasErrors()).thenReturn(false);
        List<String> list = new ArrayList<>();
        list.add("test");
        list.add("sample");
        int size = list.size();
        Mockito.when(financeApplicationDao.getUsersByGroupName("sample")).thenReturn(list);
        Mockito.when(financeApplicationDao.groupSize("sample")).thenReturn(10);
        financeApplicationService.addUser(userDto, model, result, "test");
    }

    @Test
    public void addUserTest6(){
        UserDto userDto=new UserDto();
        userDto.setMobileNumber(1L);
        List<Long> list=new ArrayList<>();
        list.add(1L);
        Mockito.when(financeApplicationDao.getMobileNumber()).thenReturn(list);
        financeApplicationService.addUser(userDto, model, result, "test");
    }
    @Test
    public void addUserTest7(){
        UserDto userDto=new UserDto();
        userDto.setMobileNumber(1L);
        List<Long> list=new ArrayList<>();
        list.add(10L);
        Mockito.when(financeApplicationDao.getMobileNumber()).thenReturn(list);
        financeApplicationService.addUser(userDto, model, result, "test");
    }
    @Test
    public void addGroupTest() {
        GroupDto groupDto = new GroupDto();
        groupDto.setGroupName("test");
        List<String> list = new ArrayList<>();
        list.add("test");
        Mockito.when(financeApplicationDao.getGroupName()).thenReturn(list);
        financeApplicationService.addGroup(groupDto, model, "test");
    }

    @Test
    public void addGroupTest1() {
        GroupDto groupDto = new GroupDto();
        groupDto.setGroupName("test");
        List<String> list = new ArrayList<>();
        list.add("sample");
        Mockito.when(financeApplicationDao.getGroupName()).thenReturn(list);
        financeApplicationService.addGroup(groupDto, model, "test");
    }


    @Test
    public void deleteUserTest() {
        User user = new User();
        user.setUserId(1L);
        List<User> list = new ArrayList<>();
        list.add(user);
        Mockito.doNothing().when(financeApplicationDao).deleteUser(1L);
        Mockito.when(financeApplicationDao.getUsersDetails()).thenReturn(list);
        financeApplicationService.deleteUser(1L, model, "sample");
    }


    @Test
    public void deleteGroupAndUserTest() {
        Group group = new Group();
        group.setGroupName("test");
        List<Group> groupList = new ArrayList<>();
        groupList.add(group);
        Mockito.doNothing().when(financeApplicationDao).deleteGroupAndUser("test");
        Mockito.when(financeApplicationDao.getGroupsDetails()).thenReturn(groupList);
        financeApplicationService.deleteGroupAndUser("test", model, "sample");
    }

    @Test
    public void getUsersDetailsTest() {
        User user = new User();
        user.setGroupName("test");
        List<User> usersList = new ArrayList<>();
        usersList.add(user);
        Mockito.when(financeApplicationDao.getUsersDetails()).thenReturn(usersList);
        Mockito.when(model.addAttribute("users", financeApplicationDao.getUsersDetails())).thenReturn(model);
        financeApplicationService.getUsersDetails(model, "sample");
    }

    @Test
    public void getGroupsDetailsTest() {
        Mockito.when(model.addAttribute("users", new User())).thenReturn(model);
        Mockito.when(model.addAttribute("groups", financeApplicationDao.getGroupsDetails())).thenReturn(model);
        financeApplicationService.getGroupsDetails(model, "sample");
    }

    @Test
    public void updateUserTest1() {
        User user = new User();
        user.setGroupName("sample");
        UserDto userDto = new UserDto();
        userDto.setGroupName("sample");
        userDto.setEmailId("test@gmail.com");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        userDto.setCreatedDate(timestamp);
        userDto.setEditedBy("admin");
        userDto.setMobileNumber(8989898989L);
        userDto.setStatus("active");
        List<User> userList = new ArrayList<>();
        userList.add(user);
        Mockito.when(financeApplicationDao.updateUser(user)).thenReturn(user);
        Mockito.when(financeApplicationDao.getUsersDetails()).thenReturn(userList);
        financeApplicationService.updateUser(userDto, model, "sample");
    }

    @Test
    public void updateUserTest2(){
        User user = new User();
        user.setGroupName("sample");
        UserDto userDto = new UserDto();
        userDto.setGroupName("sample");
        userDto.setEmailId("test@gmail.com");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        userDto.setCreatedDate(timestamp);
        userDto.setEditedBy("admin");
        userDto.setMobileNumber(8989898989L);
        userDto.setStatus("active");
        List<User> userList = new ArrayList<>();
        userList.add(user);
        Group group=new Group();
        GroupUser groupUser=new GroupUser();
        List<String> test = new ArrayList<>();
        test.add("test");
        Mockito.when(financeApplicationDao.getUsersByGroupName(anyString())).thenReturn(test);
        Mockito.when(financeApplicationDao.groupSize(anyString())).thenReturn(100);
        Mockito.when(financeApplicationDao.groupDetailsByGroupName(anyString())).thenReturn(group);
        Mockito.when(financeApplicationDao.updateUser(any())).thenReturn(user);
        Mockito.when(financeApplicationDao.updateGroup(any())).thenReturn(group);
        Mockito.when(groupUserRepository.findByUsers(any())).thenReturn(groupUser);
        groupUser.setUsers(user);
        groupUser.setGroups(group);
        Mockito.when(groupUserRepository.save(groupUser)).thenReturn(groupUser);
        financeApplicationService.updateUser(userDto,model,"test");
    }
    @Test
    public void lockgroupTestIf(){
        Group group=new Group();
        User user=new User();
        Payments payments=new Payments();
        GroupUser groupUser=new GroupUser();
        Mockito.when(financeApplicationDao.getGroup(1L)).thenReturn(group);
        group.setGroupName("test");
        List<User> userObjList=new ArrayList<>();
        userObjList.add(user);
        Mockito.when(financeApplicationDao.getDetailsByGroupName("test")).thenReturn(userObjList);
        List<String> userList=new ArrayList<>();
        userList.add("sample1");
        Mockito.when(financeApplicationDao.getUsersByGroupName("test")).thenReturn(userList);
        groupUser.setGroups(group);
        groupUser.setUsers(user);
        groupUser.setGroupUserId(1L);
        List<Payments> paymentsList=new ArrayList<>();
        groupUser.setPaymentsList(paymentsList);
        List<GroupUser> groupUserList=new ArrayList<>();
        groupUserList.add(groupUser);
        Mockito.when(groupUserRepository.findByGroups(group)).thenReturn(groupUserList);
        Optional<GroupUser> optionalGroupUser=Optional.of(groupUser);
        payments.setAmount(1d);
        payments.setBalanceAmount(1d);
        Mockito.when(financeApplicationDao.addPaymentDetails(payments)).thenReturn(payments);
        Mockito.when(groupUserRepository.findById(1L)).thenReturn(optionalGroupUser);
        Mockito.when(financeApplicationDao.getMonthsDuration("test")).thenReturn(1L);
        group.setGroupAmount(1d);
        group.setGroupSize(1L);
        group.setMonthsDuration(1L);
        user.setGrouplockStatus("sample");
        Mockito.when(financeApplicationDao.updateGroup(group)).thenReturn(group);
        Mockito.when(financeApplicationDao.updateUser(user)).thenReturn(user);
        financeApplicationService.lockGroup(1L,model,"viewName");
    }
    @Test
    public void lockgroupTestElse(){
        Group group=new Group();
        User user=new User();
        Payments payments=new Payments();
        GroupUser groupUser=new GroupUser();
        Mockito.when(financeApplicationDao.getGroup(1L)).thenReturn(group);
        group.setGroupName("test");
        List<User> userObjList=new ArrayList<>();
        userObjList.add(user);
        Mockito.when(financeApplicationDao.getDetailsByGroupName("test")).thenReturn(userObjList);
        List<String> userList=new ArrayList<>();
        userList.add("sample1");
        Mockito.when(financeApplicationDao.getUsersByGroupName("test")).thenReturn(userList);
        groupUser.setGroups(group);
        groupUser.setUsers(user);
        groupUser.setGroupUserId(1L);
        List<Payments> paymentsList=new ArrayList<>();
        groupUser.setPaymentsList(paymentsList);
        List<GroupUser> groupUserList=new ArrayList<>();
        groupUserList.add(groupUser);
        Mockito.when(groupUserRepository.findByGroups(group)).thenReturn(groupUserList);
        Optional<GroupUser> optionalGroupUser=Optional.of(groupUser);
        payments.setAmount(1d);
        payments.setBalanceAmount(1d);
        Mockito.when(financeApplicationDao.addPaymentDetails(payments)).thenReturn(payments);
        Mockito.when(groupUserRepository.findById(1L)).thenReturn(optionalGroupUser);
        Mockito.when(financeApplicationDao.getMonthsDuration("test")).thenReturn(1L);
        group.setGroupAmount(1d);
        group.setGroupSize(2L);
        group.setMonthsDuration(1L);
        user.setGrouplockStatus("sample");
        Mockito.when(financeApplicationDao.updateGroup(group)).thenReturn(group);
        Mockito.when(financeApplicationDao.updateUser(user)).thenReturn(user);
        financeApplicationService.lockGroup(1L,model,"viewName");
    }
    @Test
    public void updateGroupTest() {
        Group group = new Group();
        UserDto userDto = new UserDto();
        userDto.setStatus("active");
        group.setGroupName("sample");
        GroupDto groupDto = new GroupDto();
        groupDto.setGroupName("test");
        Timestamp date = new Timestamp(System.currentTimeMillis());
        groupDto.setCreatedDate(date);
        groupDto.setGroupAmount(10000);
        groupDto.setGroupId(1);
        groupDto.setGroupSize(2);
        GroupUserDto groupUserDto = new GroupUserDto();
        groupUserDto.setGroupId(1);
        groupDto.setGroupUserDto(groupUserDto);
        groupUserDto.setGroupDto(groupDto);
        groupUserDto.setId(1L);
        groupUserDto.setUserId(1L);
        groupUserDto.setUserDto(userDto);
        List<Group> groupList = new ArrayList<>();
        groupList.add(group);
        Mockito.when(financeApplicationDao.updateGroup(group)).thenReturn(group);
        Mockito.when(financeApplicationDao.getGroupsDetails()).thenReturn(groupList);
        financeApplicationService.updateGroup(groupDto, model, "sample");
    }


    @Test
    public void getUserTest() {
        Mockito.when(model.addAttribute("groups", financeApplicationDao.getGroupsDetails())).thenReturn(model);
        Mockito.when(model.addAttribute("users", financeApplicationDao.getUser(1L))).thenReturn(model);
        financeApplicationService.getUser(1L, model, "sample");
    }

    @Test
    public void getGroupTest() {
        Mockito.when(model.addAttribute("groupedit", financeApplicationDao.getGroup(1L))).thenReturn(model);
        financeApplicationService.getGroup(1L, model, "sample");
    }

    @Test
    public void getUsersByGroupTest() {
        Mockito.when(model.addAttribute("groups", financeApplicationDao.getUsersByGroupName("sample"))).thenReturn(model);
        financeApplicationService.getUsersByGroup("sample", model, "test");
    }

    @Test
    public void getPaymentDetailsTest() {
        Mockito.when(model.addAttribute("groups", financeApplicationDao.getPaymentDetails(1L))).thenReturn(model);
        financeApplicationService.getPaymentDetails(1L, model, "sample");
    }

    @Test
    public void calculatePaymentDetailsTest() {
        Mockito.when(financeApplicationDao.updatePaymentDetails(1L, 10000, model)).thenReturn(1L);
        Mockito.when(model.addAttribute("payments", financeApplicationDao.getPaymentDetails(1L))).thenReturn(model);
        financeApplicationService.calculatePaymentDetails(1L, 1000, 10000, 1000, 1000, "sample", model);
    }

//    @Test
//    public void lockGroupTest1()
//    {
//        Group group=new Group();
//        group.setGroupName("test");
//        List<String> list=new ArrayList<>();
//        list.add("test");
//        Mockito.when(financeApplicationDao.getGroup(1L)).thenReturn(group);
//        Mockito.when(financeApplicationDao.getUsersByGroupName("test")).thenReturn(list);
//        Mockito.when(financeApplicationDao.updateGroup(group)).thenReturn(group);
//        Mockito.when(model.addAttribute("groups", financeApplicationDao.getGroupsDetails())).thenReturn(model);
//        financeApplicationService.lockGroup(1L,model,"test");
//    }
//
   /*    @Test
    public void lockGroupTest2()
    {
        Group group=new Group();
        group.setGroupName("test");
        List<String> list=new ArrayList<>();
        list.add("test");
        Mockito.when(financeApplicationDao.getGroup(1L)).thenReturn(group);
        Mockito.when(financeApplicationDao.getUsersByGroupName("test")).thenReturn(list);
        Mockito.when(financeApplicationDao.updateGroup(group)).thenReturn(group);
        Mockito.when(model.addAttribute("groups", financeApplicationDao.getGroupsDetails())).thenReturn(model);
        financeApplicationService.lockGroup(1L,model,"test");
    }*/

   @Test
   public void transactionMessageIfTest(){
       TransactionHistory transactionHistory=new TransactionHistory();
       Mockito.when(financeApplicationDao.getTransactionHistory(anyLong())).thenReturn(transactionHistory);
       transactionHistory.setBalanceAmount(0L);
       financeApplicationService.transactionMessage(1L,1L,model,"test");
   }
    @Test
    public void transactionMessageElseTest(){
        TransactionHistory transactionHistory=new TransactionHistory();
        Mockito.when(financeApplicationDao.getTransactionHistory(anyLong())).thenReturn(transactionHistory);
        transactionHistory.setBalanceAmount(1L);
        financeApplicationService.transactionMessage(1L,1L,model,"test");
    }
    @Test
    public void transactionDetailsTest() {
        Mockito.when(model.addAttribute("transactions", financeApplicationDao.getTransactionDetails(1L))).thenReturn(model);
        financeApplicationService.transactionDetails(1L, 1234.0, model, "test");
    }

    @Test
    public void getPaymentsListTest() {
        Mockito.when(model.addAttribute("payments", financeApplicationDao.getPaymentsList(1L, model))).thenReturn(model);
        financeApplicationService.getPaymentDetails(1L, model, "test");
        financeApplicationService.getPaymentsList(1L, model, "test");
    }
}