package com.application.dao;

import com.application.dto.AdminDto;
import com.application.messageconstants.FinanceApplicationConstants;
import com.application.model.*;
import com.application.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class FinanceApplicationDaoImpl implements FinanceApplicationDao, FinanceApplicationConstants {
    private static final Logger logger = LoggerFactory.getLogger(FinanceApplicationDaoImpl.class);
    /**
     * objects for all repositories( Tables in database)
     */
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PaymentsRepository paymentsRepository;

    @Autowired
    private GroupUserRepository groupUserRepository;

    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;

    public boolean isUserAuthenticated(AdminDto adminDto) {
        logger.info("This method used to authenticate the admin");
        Admin adminVerification = adminRepository.getAdminByAdministratorAndPassword(adminDto.getAdministrator(), adminDto.getPassword());
        return adminVerification != null;
    }

    /**
     * @param userDao
     * @return Stores the user details filled in the form in the database
     * Stores the user details filled in the form in the database
     */
    public User addUser(User userDao) {
        logger.info("This method adds user to the group");
        return userRepository.save(userDao);
    }

    /**
     * @param group
     * @return Stores the group details filled in the form in the database
     */
    public Group addGroup(Group group) {
        logger.debug("This method creates a new group");
        return groupRepository.save(group);
    }

   /* public Admin addAdmin(Admin administrator) {
        return adminRepository.save(administrator);
    }*/

    /**
     * @param userId deletes the user of that id from the database
     */
    public void deleteUser(long userId) {
        logger.debug("This method deletes the User from the group");
        userRepository.deleteById(userId);
    }

   /* public void deleteAdmin(long adminId) {
        adminRepository.deleteById(adminId);
    }*/

    /**
     * @return retrieves the list of users details from database and send to the controller
     */
    public List<User> getUsersDetails() {
        logger.info("This method fetches all the User details");
        return userRepository.findAll();
    }

    /**
     * retrieves the list of groups details from database and send to the controller
     *
     * @return
     */
    public List<Group> getGroupsDetails() {
        logger.info("This method fetches all the group details");
        return (List<Group>) groupRepository.findAll();
    }

    /**
     * @return retrieves the list of users of a particular group from database and send to the controller
     */
    public List<User> getDetailsByGroupName(String groupname) {
        logger.info("This method gets the details of a group by its name");
        return userRepository.findAllUsersByGroupName(groupname);
    }

    /**
     * @param groupName This method deletes the group and the users of that group from database
     */
    public void deleteGroupAndUser(String groupName) {
        logger.debug("method starts here");
        groupRepository.deleteByGroupName(groupName);
        userRepository.deleteByGroupName(groupName);
        logger.info("method ends here");
    }

    /**
     * @return Selects the list of groups names from the group tables in the databse
     */
    public List<String> getGroupName() {
        logger.info("This method gets the Users by Group name");
        return groupRepository.displayGroupsName();
    }

    /**
     * @return Selects the list of emailId from the group tables in the databse
     */
    public List<String> getEmailId() {
        return userRepository.displayEmailId();
    }

    /**
     * @param user
     * @return This method updates the edited details of the user in the database
     */
    public User updateUser(User user) {
        logger.info("Update User method starts here");
        User users;
        Optional<User> optUser = userRepository.findById(user.getUserId());
        try {
            users = optUser.get();
        } catch (NoSuchElementException e) {
            throw new RuntimeException(UPDATE_USER_EXCEPTION_MESSAGE);
        }
        users.setGroupName(user.getGroupName());
        users.setEditedBy(user.getEditedBy());
        users.setGroupUserArrayList(user.getGroupUserArrayList());
        logger.info("Update User method ends here");
        return userRepository.save(users);
    }

    /**
     * @param group
     * @return This method updates the edited details of the group in the database
     */
    public Group updateGroup(Group group) {
        logger.debug("Update Group method starts here");
        Group groups;
        Optional<Group> optGroup = groupRepository.findById(group.getGroupId());
        try {
            groups = optGroup.get();
        } catch (NoSuchElementException e) {
            throw new RuntimeException(UPDATE_GROUP_EXCEPTION_MESSAGE);
        }
        groups.setGroupName(group.getGroupName());
        groups.setGroupAmount(group.getGroupAmount());
        groups.setGroupSize(group.getGroupSize());
        groups.setMonthsDuration(group.getMonthsDuration());
        groups.setGroupUserList(group.getGroupUserList());
        logger.debug("Update Group method ends here");
        return groupRepository.save(groups);
    }

  /*public GroupUser updateGroupUser(GroupUser groupUser,Group group)
    {
        GroupUser groupUserList= groupUserRepository.findByGroups(group);
        long groupUserId=  groupUserList.getGroupUserId();
        Optional<GroupUser> optionalGroupUser=groupUserRepository.findById(groupUserId);
        groupUser=optionalGroupUser.get();
        groupUser.setGroupUserId(groupUser.getGroupUserId());
        groupUser.setGroups(groupUser.getGroups());
        groupUser.setUsers(groupUser.getUsers());
        groupUser.setPaymentsList(groupUser.getPaymentsList());
        return groupUserRepository.save(groupUser);
    }

*/

    /**
     * @param transactionHistories
     * @return All the transaction details like message,amountpaid,balance,total amount  etc of a suer will saved in the database table
     */
    public TransactionHistory updateTransaction(TransactionHistory transactionHistories) {
        logger.info("UpdateTransaction method starts here");
        TransactionHistory transactionHistory = new TransactionHistory();
        Optional<TransactionHistory> optionalTransactionHistory = transactionHistoryRepository.findById(transactionHistories.getTransactionId());
        transactionHistory = optionalTransactionHistory.get();
        transactionHistory.setMessage(transactionHistory.getMessage());
        transactionHistory.setBalanceAmount(transactionHistory.getBalanceAmount());
        transactionHistory.setAmount(transactionHistory.getAmount());
        transactionHistory.setAmountPaid(transactionHistory.getAmountPaid());
        transactionHistory.setMonths(transactionHistory.getMonths());
        transactionHistory.setPayments(transactionHistory.getPayments());
        transactionHistory.setTransactionDate(transactionHistory.getTransactionDate());
        transactionHistory.setTransactionId(transactionHistory.getTransactionId());
        logger.info("UpdateTransaction method ends here");
        return transactionHistoryRepository.save(transactionHistory);
    }

    /**
     * @param userId
     * @return This method gets all the user details from the database and send to the controller
     */
    public User getUser(long userId) {
        logger.debug("method starts here");
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent())
            return optionalUser.get();
        else
            logger.debug("method ends here");
        return null;
    }

    /**
     * @param groupname
     * @return Gets all the users of a particular group from database
     */
  /*  public User getUserByGroupName(String groupname) {
        return userRepository.findByGroupName(groupname);
    }*/

    /**
     * @param groupId
     * @return using the groupId the group details are taken from the database
     */
    public Group getGroup(long groupId) {
        Optional<Group> optionalGroup = groupRepository.findById(groupId);
        if (optionalGroup.isPresent())
            return optionalGroup.get();
        else
            return null;
    }
 /*   public GroupUser getGroupUser(long groupId)
    {
        Group group=getGroup(groupId);
        Optional<GroupUser> optionalGroupUser=groupUserRepository.findById(group.getGroupId());
        if(optionalGroupUser.isPresent())
        {
            return optionalGroupUser.get();
        }
        else
            return null;
    }*/

    /**
     * @param transId
     * @return This returns all the transaction history details of a particular user og a group
     */
    public TransactionHistory getTransactionHistory(long transId) {
        logger.debug("method starts here");
        Optional<TransactionHistory> optionalTransactionHistory = transactionHistoryRepository.findById(transId);
        if (optionalTransactionHistory.isPresent())
            return optionalTransactionHistory.get();
        else
            logger.debug("method ends here");
        return null;
    }

    /**
     * @param groupName
     * @return Will get the list of users of a particular group
     */
    public List<String> getUsersByGroupName(String groupName) {
        return userRepository.selectUserByGroup(groupName);
    }

    /**
     * @param id
     * @return This considers the id from the joint tables and store the payments details in the payments table in the database
     */
    public List<Payments> getPaymentDetails(long id) {
        logger.debug("method starts here");
        GroupUser groupUser = new GroupUser();
        groupUser.setGroupUserId(id);
        List<Payments> paymentsList = paymentsRepository.findByGroupUsers(groupUser);
        logger.debug("method ends here");
        return paymentsList;
    }

    /**
     * returns the details of a particular group
     *
     * @param groupName
     * @return
     */
    public Group groupDetailsByGroupName(String groupName) {
        return groupRepository.findByGroupName(groupName);
    }

    /**
     * @param groupUser
     * @return stores the joint table GroupUser details in the database
     */
    public GroupUser addGroupUser(GroupUser groupUser) {
        return groupUserRepository.save(groupUser);
    }

    /**
     * @param groupName
     * @return This returns the no. of months of a particular group
     */
    public Long getMonthsDuration(String groupName) {
        return groupRepository.getMonthsDurationByGroupName(groupName);
    }

    /**
     * @param payments
     * @return Stores the payments details  in the payemnts table of the database
     */
    public Payments addPaymentDetails(Payments payments) {
        return paymentsRepository.save(payments);
    }

    /**
     * @param paymentsId
     * @param amountPaid
     * @param model
     * @return Payment of the amount
     * Checks whether the amount paying is greater than required and returns a message
     * updates the amount paid, balance coloumns according to the amount paid and stores the data in the database
     */
    public long updatePaymentDetails(long paymentsId, double amountPaid, Model model) {
        logger.debug("method starts here");
        Payments payment = new Payments();
        Optional<Payments> payments = paymentsRepository.findById(paymentsId);
        payment = payments.get();
        double amount = payment.getAmount();
        double previousAmountPaid = payment.getAmountPaid();
        double totAmntPaid = amountPaid + previousAmountPaid;
        if (totAmntPaid > amount) {
            model.addAttribute(MESSAGE_NAME, UPDATE_PAYMENT_DETAILS_MESSAGE);
            GroupUser groupUser = payment.getGroupUsers();
            long id = groupUser.getGroupUserId();
            paymentsRepository.save(payment);
            logger.debug("method ends here");
            return id;
        }
        payment.setAmountPaid(amountPaid + previousAmountPaid);
        double balanceAmount = amount - (amountPaid + previousAmountPaid);
        payment.setBalanceAmount(balanceAmount);
        GroupUser groupUser = payment.getGroupUsers();
        long id = groupUser.getGroupUserId();
        paymentsRepository.save(payment);
        logger.debug("method ends here");
        return id;
    }

    /**
     * @param groupName
     * @return returns the size of a group from group table to the controller
     */
    public int groupSize(String groupName) {
        return groupRepository.groupsize(groupName);
    }

    /**
     * @param id
     * @return returns the username by taking the id passed from user table in the database
     */
    public String getUserNameById(long id) {
        return userRepository.getUserNameById(id);
    }

    /**
     * @param id
     * @return returns the groupname by taking the id passed from group table in the database
     */
    public String getGroupNameById(long id) {
        return userRepository.getGroupNameById(id);
    }

    /**
     * @param transactionHistory
     * @return Stores the transaction details in the transactionHistory table in the database
     */
    public TransactionHistory saveTransactionDetails(TransactionHistory transactionHistory) {
        return transactionHistoryRepository.save(transactionHistory);
    }

    /**
     * @param paymentsId
     * @return returns the list of transaction history from transactionHistory table considering the payment id of the user
     */
    public List<TransactionHistory> getTransactionDetails(long paymentsId) {
        logger.debug("method starts here");
        Payments payments = new Payments();
        payments.setPaymentsId(paymentsId);
        logger.debug("method ends here");
        return transactionHistoryRepository.findByPayments(payments);
    }

    /**
     * @param id
     * @param model
     * @return We use this method To return back from history page to payments and display all the details
     */
    public List<Payments> getPaymentsList(long id, Model model) {
        logger.debug("method starts here");
        Payments payments = new Payments();
        Optional<Payments> optionalPayments = paymentsRepository.findById(id);
        payments = optionalPayments.get();
        String userName = getUserNameById(payments.getGroupUsers().getGroupUserId());
        String groupName = getGroupNameById(payments.getGroupUsers().getGroupUserId());
        model.addAttribute(MODEL_USERNAME, userName);
        model.addAttribute(MODEL_GROUPNAME, groupName);
        logger.debug("method ends here");
        return getPaymentDetails(payments.getGroupUsers().getGroupUserId());
    }

    /**
     * returns the mobilenumber of the user from user table considering the id
     */
    public List<Long> getMobileNumber() {
        return userRepository.getMobileNumberDetails();
    }
}
