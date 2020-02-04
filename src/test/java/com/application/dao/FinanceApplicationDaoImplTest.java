package com.application.dao;

import com.application.dto.AdminDto;
import com.application.model.*;
import com.application.repository.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.Model;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class FinanceApplicationDaoImplTest {

    @InjectMocks
    private FinanceApplicationDaoImpl financeApplicationDao;

    @Mock
    private UserRepository userRepository;

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private PaymentsRepository paymentsRepository;

    @Mock
    private GroupUserRepository groupUserRepository;

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private TransactionHistoryRepository transactionHistoryRepository;

    @Mock
    private Model model;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void isUserAuthenticatedTest() {
        AdminDto adminDto = new AdminDto();
        financeApplicationDao.isUserAuthenticated(adminDto);
    }

    @Test
    public void isUserAuthenticatedTest1() {
        AdminDto adminDto = new AdminDto();
        adminDto.setAdminId(1);
        adminDto.setPassword("test");
        adminDto.setAdministrator("test");
        Admin admin = new Admin();
        Mockito.when(adminRepository.getAdminByAdministratorAndPassword("test", "test")).thenReturn(admin);
        financeApplicationDao.isUserAuthenticated(adminDto);
    }

    @Test
    public void addUserTest() {
        User user = new User();
        user.setGroupName("sample");
        Mockito.when(userRepository.save(user)).thenReturn(user);
        financeApplicationDao.addUser(user);
    }

    @Test
    public void addGroupTest() {
        Group group = new Group();
        group.setGroupName("test");
        Mockito.when(groupRepository.save(group)).thenReturn(group);
        financeApplicationDao.addGroup(group);
    }


    @Test
    public void deleteUserTest() {
        Mockito.doNothing().when(userRepository).deleteById(1L);
        financeApplicationDao.deleteUser(1L);
    }


    @Test
    public void getUsersDetailsTest() {
        User user = new User();
        user.setGroupName("Test");
        List<User> userList = new ArrayList<>();
        userList.add(user);
        Mockito.when(userRepository.findAll()).thenReturn(userList);
        financeApplicationDao.getUsersDetails();
    }

    @Test
    public void getGroupsDetailsTest() {
        financeApplicationDao.getGroupsDetails();
    }

    @Test
    public void getDetailsByGroupNameTest() {
        financeApplicationDao.getDetailsByGroupName("sample");
    }

    @Test
    public void deleteGroupAndUser() {
        financeApplicationDao.deleteGroupAndUser("test");
    }

    @Test
    public void getGroupNameTest() {
        financeApplicationDao.getGroupName();
    }

    @Test
    public void getEmailIdTest() {
        financeApplicationDao.getEmailId();
    }


    @Test
    public void updateUserTest1() {
        User user = new User();
        user.setUserId(1);
        Optional<User> optionalUser = Optional.of(user);
        Mockito.when(userRepository.findById(1L)).thenReturn(optionalUser);
        financeApplicationDao.updateUser(user);
    }

    @Test(expected = RuntimeException.class)
    public void updateUserTest2() {
        User user = new User();
        user.setGroupName("test");
        financeApplicationDao.updateUser(user);
    }

    @Test
    public void getUserTest1() {
        User user = new User();
        user.setUserId(1);
        user.setGroupName("test");
        Optional<User> optionalUser = Optional.of(user);
        Mockito.when(userRepository.findById(1L)).thenReturn(optionalUser);
        financeApplicationDao.getUser(1L);
    }

    @Test
    public void getUserTest2() {
        User user = new User();
        user.setUserId(1);
        user.setGroupName("test");
        Optional<User> optionalUser = Optional.of(user);
        financeApplicationDao.getUser(1L);
    }

    @Test
    public void getGroupIfTest() {
        Group group = new Group();
//        group.setGroupId(1L);
        Optional<Group> optionalGroup = Optional.of(group);
        Mockito.when(groupRepository.findById(1L)).thenReturn(optionalGroup);
        financeApplicationDao.getGroup(1L);
    }

    @Test
    public void getTransactionHistoryTestIf() {
        TransactionHistory transactionHistory = new TransactionHistory();
        Optional<TransactionHistory> optionalTransactionHistory = Optional.of(transactionHistory);
        Mockito.when(transactionHistoryRepository.findById(anyLong())).thenReturn(optionalTransactionHistory);
        financeApplicationDao.getTransactionHistory(1L);
    }
    @Test
    public void getTransactionHistoryTestElse() {
        TransactionHistory transactionHistory = new TransactionHistory();
        Optional<TransactionHistory> optionalTransactionHistory = Optional.of(transactionHistory);
     /*   Mockito.when(transactionHistoryRepository.findById(anyLong())).thenReturn(optionalTransactionHistory);*/
        financeApplicationDao.getTransactionHistory(1L);
    }
   /* @Test
    public void getTransactionHistoryTest(){
        TransactionHistory transactionHistory=new TransactionHistory();
        Optional<TransactionHistory> OptionalTransactionHistory=Optional.of(transactionHistory);
        Mockito.when(transactionHistoryRepository.findById(1L)).thenReturn(OptionalTransactionHistory);
        financeApplicationDao.getTransactionHistory(1L);
    }*/
   /* @Test
    public void getTransactionHistoryTestNull(){
        TransactionHistory transactionHistory=new TransactionHistory();
        Optional<TransactionHistory> OptionalTransactionHistory=Optional.of(transactionHistory);
        Mockito.when(transactionHistoryRepository.findById(null)).thenReturn(OptionalTransactionHistory);
        financeApplicationDao.getTransactionHistory(0L);
    }*/

    @Test
    public void getGroupElseTest() {
        financeApplicationDao.getGroup(1L);
    }

    @Test
    public void getUsersByGroupNameTest() {
        financeApplicationDao.getUsersByGroupName("test");
    }

    @Test
    public void getPaymentDetailsTest() {
        financeApplicationDao.getPaymentDetails(1L);
    }

    @Test
    public void groupDetailsByGroupNameTest() {
        financeApplicationDao.groupDetailsByGroupName("test");
    }

    @Test
    public void addGroupUserTest() {
        GroupUser groupUser = new GroupUser();
        financeApplicationDao.addGroupUser(groupUser);
    }

    @Test
    public void getMonthsDurationTest() {
        financeApplicationDao.getMonthsDuration("test");
    }

    @Test
    public void addPaymentDetailsTest() {
        GroupUser groupUsers = new GroupUser();
        groupUsers.setGroupUserId(1);
        Payments payments = new Payments();
        payments.setBalanceAmount(1000);
        payments.setAmountPaid(10000);
        payments.setAmount(1234);
        payments.setMonths(1);
        payments.setPaymentsId(1);
        payments.setGroupUsers(groupUsers);
        financeApplicationDao.addPaymentDetails(payments);
    }

    @Test
    public void groupSizeTest() {
        financeApplicationDao.groupSize("test");
    }

    @Test
    public void updateGroupTest() {
        Group group = new Group();
        group.setGroupName("test");
        group.setGroupId(1);
        group.setGroupSize(2);
        group.setMonthsDuration(2);
        Optional<Group> optionalGroup = Optional.of(group);
        Mockito.when(groupRepository.findById(1L)).thenReturn(optionalGroup);
        financeApplicationDao.updateGroup(group);
    }

    @Test
    public void updateTransactionTest() {
        TransactionHistory transactionHistory = new TransactionHistory();
        Optional<TransactionHistory> optionalTransactionHistory = Optional.of(transactionHistory);
        Mockito.when(transactionHistoryRepository.findById(1L)).thenReturn(optionalTransactionHistory);
        transactionHistory.setMessage("Test");
        transactionHistory.setBalanceAmount(1d);
        transactionHistory.setAmount(1d);
        transactionHistory.setAmountPaid(1d);
        transactionHistory.setMonths(1L);
        transactionHistory.setPayments(new Payments());
        transactionHistory.setTransactionDate(new Timestamp(Instant.now().getEpochSecond()));
        transactionHistory.setTransactionId(1L);
        financeApplicationDao.updateTransaction(transactionHistory);
    }

    @Test(expected = RuntimeException.class)
    public void updateGroupExceptionTest() {
        Group group = new Group();
        group.setGroupName("test");
        financeApplicationDao.updateGroup(group);
    }

    @Test
    public void getUserNameByIdTest() {
        financeApplicationDao.getUserNameById(1L);
    }

    @Test
    public void getGroupNameByIdTest() {
        financeApplicationDao.getGroupNameById(1L);
    }

    @Test
    public void saveTransactionDetailsTest() {
        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setBalanceAmount(1234);
        Mockito.when(transactionHistoryRepository.save(transactionHistory)).thenReturn(transactionHistory);
        financeApplicationDao.saveTransactionDetails(transactionHistory);
    }

    @Test
    public void getTransactionDetailsTest() {
        financeApplicationDao.getTransactionDetails(1L);
    }

    @Test
    public void getMoblieNumberTest() {
        financeApplicationDao.getMobileNumber();
    }

    @Test
    public void updatePaymentDetailsIfTest() {
        Payments payments = new Payments();
        payments.setPaymentsId(1L);
        GroupUser groupUser = new GroupUser();
        groupUser.setGroupUserId(1);
        payments.setGroupUsers(groupUser);
        Optional<Payments> optionalPayments = Optional.of(payments);
        Mockito.when(paymentsRepository.findById(1L)).thenReturn(optionalPayments);
        Mockito.when(paymentsRepository.save(payments)).thenReturn(payments);
        //Mockito.when(groupUser.getGroupUserId()).thenReturn(1L);
        financeApplicationDao.updatePaymentDetails(1, 1234, model);
    }

    @Test
    public void updatePaymentDetailsElseTest() {
        Payments payments = new Payments();
        payments.setAmount(2000);
        payments.setPaymentsId(1L);
        GroupUser groupUser = new GroupUser();
        groupUser.setGroupUserId(1);
        payments.setGroupUsers(groupUser);
        Optional<Payments> optionalPayments = Optional.of(payments);
        Mockito.when(paymentsRepository.findById(1L)).thenReturn(optionalPayments);
        Mockito.when(paymentsRepository.save(payments)).thenReturn(payments);
        financeApplicationDao.updatePaymentDetails(1, 1234, model);
    }
    @Test
    public void getPaymentsListTest(){
        Payments payments=new Payments();
        GroupUser groupUser=new GroupUser();
        Optional<Payments> optionalPayments=Optional.of(payments);
        List<Payments> getPaymentsList=new ArrayList<>();
        payments.setBalanceAmount(1d);
        payments.setAmount(1d);
        payments.setBalanceAmount(1d);
        payments.setGroupUsers(groupUser);
        payments.setPaymentsId(1L);
        payments.setAmountPaid(1d);
        getPaymentsList.add(payments);
        Mockito.when(paymentsRepository.findById(1L)).thenReturn(optionalPayments);
//        Mockito.when(userRepository.getUserNameById(1L)).thenReturn("test");
//        Mockito.when(userRepository.getGroupNameById(1L)).thenReturn("test");
        financeApplicationDao.getPaymentsList(1L,model);
    }

}
