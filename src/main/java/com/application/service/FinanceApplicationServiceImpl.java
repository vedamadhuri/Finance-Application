package com.application.service;

import com.application.dao.FinanceApplicationDaoImpl;
import com.application.dto.AdminDto;
import com.application.dto.GroupDto;
import com.application.dto.UserDto;
import com.application.messageconstants.FinanceApplicationConstants;
import com.application.model.*;
import com.application.repository.GroupUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FinanceApplicationServiceImpl implements FinanceApplicationService, FinanceApplicationConstants {
    private static final Logger logger = LoggerFactory.getLogger(FinanceApplicationServiceImpl.class);
    /**
     * object for class FinanceApplicationDaoImpl
     */
    @Autowired
    private FinanceApplicationDaoImpl applicationDaoImpl;

    /**
     * object for interface GroupUserRepository
     */
    @Autowired
    private GroupUserRepository groupUserRepository;

    public boolean isUserAuthenticated(AdminDto adminDto) {
        logger.info("Entered Into IsUserAuthenticated service method");
        return applicationDaoImpl.isUserAuthenticated(adminDto);
    }

    /**
     * @param userDto
     * @param model
     * @param result
     * @param viewName
     * @return This connects with the dao in creating a new user
     * checks if the emailId,mobile number  entered is unique or not , other wise will throw an error
     * checks the group size whether a user can be added to the group or not
     * returns the html page that has to be re-directed to and get the details of the groups from dao
     */
    public String addUser(UserDto userDto, Model model, BindingResult result, String viewName) {
        logger.info("Entered Into addUser service method ");
        if (result.hasErrors()) {
            model.addAttribute(MODEL_GROUPS, applicationDaoImpl.getGroupsDetails());
            viewName = VIEWNAME_ADDUSER;
            logger.info("End of addUser service method");
            return viewName;
        }
        String EmailId = userDto.getEmailId();
        List<String> lists = applicationDaoImpl.getEmailId();
        for (String emailid : lists) {
            if (emailid.equals(EmailId)) {
                model.addAttribute(MODEL_GROUPS, applicationDaoImpl.getGroupsDetails());
                model.addAttribute(MESSAGE_EMAILID_NAME, ADDUSER_EMAILID_MESSAGE);
                viewName = VIEWNAME_ADDUSER;
                logger.info("End of addUser service method");
                return viewName;
            }
        }
        Long mobileNumber = userDto.getMobileNumber();
        List<Long> list = applicationDaoImpl.getMobileNumber();
        for (long mobilenumber : list) {
            if (mobilenumber == mobileNumber) {
                List<Group> groups = applicationDaoImpl.getGroupsDetails();
                model.addAttribute(MODEL_GROUPS, groups);
                model.addAttribute(MESSAGE_NAME, ADDUSER_MOBLIENO_MESSSAGE);
                viewName = VIEWNAME_ADDUSER;
                logger.info("End of addUser service method");
                return viewName;
            }
        }
        String groupname = userDto.getGroupName();
        if ((applicationDaoImpl.getUsersByGroupName(groupname).size() + 1) > applicationDaoImpl.groupSize(groupname)) {
            model.addAttribute(MESSAGE_GROUP_NAME, ADDUSER_GROUPLIMIT_MESSAGE);
            model.addAttribute(MODEL_GROUPS, applicationDaoImpl.getGroupsDetails());
            viewName = VIEWNAME_ADDUSER;
            logger.info("End of addUser service method");
            return viewName;
        }
        Group group = applicationDaoImpl.groupDetailsByGroupName(userDto.getGroupName());
        User user = new User();
        GroupUser groupUser = new GroupUser();
        List<Payments> paymentsList = new ArrayList<>();
        BeanUtils.copyProperties(userDto, user);
        groupUser.setGroups(group);
        groupUser.setUsers(user);
        List<GroupUser> groupUsers = new ArrayList<>();
        groupUsers.add(groupUser);
        user.setGroupUserArrayList(groupUsers);
        groupUser.setPaymentsList(paymentsList);
        user.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        applicationDaoImpl.addUser(user);
        logger.info("End of addUser service method");
        return getUsersDetails(model, viewName);
    }

    /**
     * @param groupDto
     * @param model
     * @param viewName
     * @return When adding a new group checka if any group exists with same name returns the html page
     * connect with the dao layer to create new group in the database
     */
    public String addGroup(GroupDto groupDto, Model model, String viewName) {
        logger.info("Entered Into addGroup service method");
        List<String> list = applicationDaoImpl.getGroupName();
        String groupNames = groupDto.getGroupName();
        for (String groupname : list) {
            if (groupname.equalsIgnoreCase(groupNames)) {
                model.addAttribute(MESSAGE_NAME, ADDUSER_DUPLICATE_GROUPNAME_MESSAGE);
                viewName = VIEWNAME_ADDGROUP;
                logger.info("End of addGroup service method");
                return viewName;
            }
        }
        Group groupnew = new Group();
        BeanUtils.copyProperties(groupDto, groupnew);
        groupnew.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        applicationDaoImpl.addGroup(groupnew);
        logger.info("End of addGroup service method");
        return getGroupsDetails(model, viewName);
    }


    /**
     * @param userId
     * @param model
     * @param viewName
     * @return connects with the dao layer to delete the user from database
     */
    public String deleteUser(long userId, Model model, String viewName) {
        logger.info("Entered Into deleteUser service method");
        applicationDaoImpl.deleteUser(userId);
        logger.info("End of deleteUser service method");
        return getUsersDetails(model, viewName);
    }


    /**
     * @param groupName
     * @param model
     * @param viewName
     * @return this connects the controller and dao layer to delete the groups adn user details
     */
    public String deleteGroupAndUser(String groupName, Model model, String viewName) {
        logger.info("Entered Into deleteGroupAndUser service method");
        applicationDaoImpl.deleteGroupAndUser(groupName);
        logger.info("End of deleteGroupAndUser service method");
        return getGroupsDetails(model, viewName);
    }


    /**
     * @param model
     * @param viewName
     * @return gets the user details from the dao , sends to the controller and return the html page
     */
    public String getUsersDetails(Model model, String viewName) {
        logger.info("Entered Into getUsersDetails service method");
        model.addAttribute(MODEL_USERS, applicationDaoImpl.getUsersDetails());
        logger.info("End of getUsersDetails method");
        return viewName;
    }

    /**
     * @param model
     * @param viewName
     * @return sends the users and groupdetails using model.addAttribute to the controller and return the viewname
     */
    public String getGroupsDetails(Model model, String viewName) {
        logger.info("Entered Into getGroupsDetails service method");
        model.addAttribute(MODEL_USERS, new User());
        model.addAttribute(MODEL_GROUPS, applicationDaoImpl.getGroupsDetails());
        logger.info("End of getGroupsDetails service method");
        return viewName;
    }

    /**
     * @param userDto
     * @param model
     * @param viewName
     * @return copies the user properties from dto object to model object and send the info to dao  layer
     * returns the html page to which it will be re-directed
     */
    public String updateUser(UserDto userDto, Model model, String viewName) {
        logger.info("Entered Into updateUser service method");
        User user = new User();
        String groupName = userDto.getGroupName();
        BeanUtils.copyProperties(userDto, user);
        if ((applicationDaoImpl.getUsersByGroupName(groupName).size() + 1) > applicationDaoImpl.groupSize(groupName)) {

            model.addAttribute(MESSAGE_GROUP_NAME, UPDATE_USER_GROUPSIZE_MESSAGE);
            model.addAttribute(MODEL_GROUPS, applicationDaoImpl.getGroupsDetails());
            model.addAttribute(MODEL_USERS, applicationDaoImpl.getUser(user.getUserId()));
            viewName = VIEWNAME_ADDNEWUSER;
            logger.info("End of updateUser service method");
            return viewName;
        } else {
            Group group = applicationDaoImpl.groupDetailsByGroupName(groupName);
            User updateUser = applicationDaoImpl.updateUser(user);
            Group updateGroup = applicationDaoImpl.updateGroup(group);
            GroupUser groupUser = groupUserRepository.findByUsers(updateUser);
            groupUser.setUsers(updateUser);
            groupUser.setGroups(updateGroup);
            groupUserRepository.save(groupUser);
            logger.info("End of updateUser service method");
            return getUsersDetails(model, VIEWNAME_USER);
        }
    }

    /**
     * @param groupDto
     * @param model
     * @param viewName
     * @return copies the group properties from dto object to model object and send the info to dao  layer
     * returns the html page to which it will be re-directed
     */
    public String updateGroup(GroupDto groupDto, Model model, String viewName) {
        logger.info("Entered Into updateGroup service method");
        Group group = new Group();
        BeanUtils.copyProperties(groupDto, group);
        System.out.println((applicationDaoImpl.getUsersByGroupName(group.getGroupName()).size() + 1) + "**********");
        System.out.println(applicationDaoImpl.groupSize(group.getGroupName()));
        applicationDaoImpl.updateGroup(group);
        logger.info("End of updateGroup service method");
        return getGroupsDetails(model, viewName);
    }

    /**
     * @param userId
     * @param model
     * @param viewName
     * @return This get the user and group details from the database and returns them to the html page
     */
    public String getUser(long userId, Model model, String viewName) {
        logger.info("Entered Into getUser service method");
        model.addAttribute(MODEL_GROUPS, applicationDaoImpl.getGroupsDetails());
        model.addAttribute(MODEL_USERS, applicationDaoImpl.getUser(userId));
        logger.info("End of getUser service method");
        return viewName;
    }

    /**
     * @param groupId
     * @param model
     * @param viewName
     * @return This get the group details from the database and returns them to the html page
     */
    public String getGroup(long groupId, Model model, String viewName) {
        logger.info("Entered Into getGroup service method");
        model.addAttribute(VIEWNAME_GROUPEDIT, applicationDaoImpl.getGroup(groupId));
        logger.info("End of getGroup service method");
        return viewName;
    }

    /**
     * @param userId
     * @param model
     * @param viewName
     * @return This get the details from the user,payments group respective database tables and returns them to the html page
     */
    public String getPaymentDetails(long userId, Model model, String viewName) {
        logger.info("Entered Into getPaymentDetails service method");
        model.addAttribute(MODEL_USERNAME, applicationDaoImpl.getUserNameById(userId));
        model.addAttribute(MODEL_GROUPNAME, applicationDaoImpl.getGroupNameById(userId));
        model.addAttribute(VIEWNAME_PAYMENTS, applicationDaoImpl.getPaymentDetails(userId));
        logger.info("End of getPaymentDetails service method");
        return viewName;
    }

    /**
     * @param groupName
     * @param model
     * @param viewName
     * @return to get the list of users of a group
     */
    public String getUsersByGroup(String groupName, Model model, String viewName) {
        logger.info("Entered Into getUsersByGroup service method");
        model.addAttribute(MODEL_GROUPS, applicationDaoImpl.getUsersByGroupName(groupName));
        logger.info("End of getUsersByGroup service method");
        return viewName;
    }

    /**
     * @param groupId
     * @param model
     * @param viewName
     * @return It is used to lock the users in the group by checking the number of users in the group and the size of the group
     * after that action opertions of payments are done and updated
     */
    public String lockGroup(long groupId, Model model, String viewName) {
        logger.info("Entered Into lockGroup service method");
        Group group = applicationDaoImpl.getGroup(groupId);
        String groupname = group.getGroupName();
        List<User> usersObjList = applicationDaoImpl.getDetailsByGroupName(groupname);
        List<String> userList = applicationDaoImpl.getUsersByGroupName(groupname);
        int userCount = userList.size();
        model.addAttribute(MODEL_USERCOUNT, userCount);
        model.addAttribute(MODEL_GROUPCOUNT, group.getGroupSize());
        String groupStatus = (userCount == group.getGroupSize()) ? GROUPLOCKED_STATUS : GROUPUNLOCKED_STATUS;
        group.setGroupLock(groupStatus);
        List<GroupUser> groupUserList = groupUserRepository.findByGroups(group);
        int userId = usersObjList.size();
        for (GroupUser groupUserLists : groupUserList) {
            int groupUserId = (int) groupUserLists.getGroupUserId();
            Long longGroupuserId = new Long(groupUserId);
            Long Id = new Long(userId);
            GroupUser groupUser = new GroupUser();
            for (Long id = longGroupuserId; id <= longGroupuserId; id++) {
                Optional<GroupUser> groupUserOptional = groupUserRepository.findById(id);
                groupUser = groupUserOptional.get();
                Long monthsDuration = applicationDaoImpl.getMonthsDuration(groupname);
                for (Long months = 0L; months < monthsDuration; months++) {
                    Payments payments = new Payments();
                    payments.setGroupUsers(groupUser);
                    int amount = (int) group.getGroupAmount();
                    int size = (int) group.getGroupSize();
                    int groupMonthsDuration = (int) group.getMonthsDuration();
                    int userYearlyAmount = (int) (amount / size);
                    int userMonthlyAmount = (int) (userYearlyAmount / groupMonthsDuration);
                    payments.setAmount(userMonthlyAmount);
                    payments.setBalanceAmount(userMonthlyAmount);
                    applicationDaoImpl.addPaymentDetails(payments);
                }
            }
        }
        for (User users : usersObjList) {
            users.setGrouplockStatus(groupStatus);
        }
        viewName = VIEWNAME_GROUP;
        applicationDaoImpl.updateGroup(group);
        for (User users : usersObjList) {
            applicationDaoImpl.updateUser(users);
        }
        model.addAttribute(MODEL_GROUPS, applicationDaoImpl.getGroupsDetails());
        model.addAttribute(MODEL_USERS, applicationDaoImpl.getUsersDetails());
        logger.info("End of lockGroup service method");
        return viewName;
    }

    /**
     * @param paymentId
     * @param model
     * @param viewName
     * @return For giving the required messages in the transaction
     * checks the amount paid with the total amount to be paid
     * returns the maount paid details to the transactiondetails html page
     */
    public String transactionMessage(long transactionId, long paymentId, Model model, String viewName) {
        logger.info("Entered Into transactionMessage service method");
        TransactionHistory transactionHistory = applicationDaoImpl.getTransactionHistory(transactionId);
        System.out.println(transactionHistory.getBalanceAmount());
        if (transactionHistory.getBalanceAmount() == 0) {
            transactionHistory.setMessage("You Have Paid The Complete Amount");
        } else {
            transactionHistory.setMessage("You Have Paid " + transactionHistory.getAmountPaid() + " The Remaining Amount Needed To Be Paid Is " + transactionHistory.getBalanceAmount());
        }
        applicationDaoImpl.updateTransaction(transactionHistory);
        model.addAttribute(MODEL_TRANSACTIONS, applicationDaoImpl.getTransactionDetails(paymentId));
        viewName = VIEWNAME_TRANSACTIONDETAILS;
        model.addAttribute("groups", applicationDaoImpl.getGroupsDetails());
        model.addAttribute("users", applicationDaoImpl.getUsersDetails());
        logger.info("End of transactionMessage service method");
        return viewName;
    }

    /**
     * @param paymentsId
     * @param months
     * @param amountPaid
     * @param balanceAmount
     * @param amount
     * @param viewName
     * @param model
     * @return This method interacts with the dao to get userName,groupName,payments from respective db tables
     * Calculates the aount paid, to be paid, balance and saves the data in the database
     */
    public String calculatePaymentDetails(long paymentsId, long months, double amountPaid, double balanceAmount, double amount, String viewName, Model model) {
        logger.info("Entered Into calculatePaymentDetails service method ");
        TransactionHistory transactionHistory = new TransactionHistory();
        Payments payments = new Payments();
        payments.setPaymentsId(paymentsId);
        List<TransactionHistory> transactionHistoryList = new ArrayList<>();
        transactionHistory.setAmount(amount);
        transactionHistory.setAmountPaid(amountPaid);
        transactionHistory.setTransactionDate(new Timestamp((System.currentTimeMillis())));
        transactionHistory.setMonths(months);
        transactionHistory.setBalanceAmount(balanceAmount - amountPaid);
        transactionHistory.setPayments(payments);
        transactionHistoryList.add(transactionHistory);
        payments.setTransactionHistoryList(transactionHistoryList);
        applicationDaoImpl.saveTransactionDetails(transactionHistory);
        long id = applicationDaoImpl.updatePaymentDetails(paymentsId, amountPaid, model);
        model.addAttribute(MODEL_USERNAME, applicationDaoImpl.getUserNameById(id));
        model.addAttribute(MODEL_GROUPNAME, applicationDaoImpl.getGroupNameById(id));
        model.addAttribute(MODEL_PAYMENTS, applicationDaoImpl.getPaymentDetails(id));
        logger.info("End of calculatePaymentDetails service method");
        return viewName;
    }

    /**
     * @param paymentsId
     * @param amountPaid
     * @param model
     * @param viewName
     * @return gets the transaction history details from the database interacting with db
     */
    public String transactionDetails(long paymentsId, double amountPaid, Model model, String viewName) {
        logger.info("Entered Into transactionDetails service method");
        model.addAttribute(MODEL_TRANSACTIONS, applicationDaoImpl.getTransactionDetails(paymentsId));
        logger.info("End of transactionDetails method");
        return viewName;
    }

    /**
     * @param id
     * @param model
     * @param viewName
     * @return interacts with dao to get the amount paid,total  amount and balance from database
     */
    public String getPaymentsList(long id, Model model, String viewName) {
        logger.info("Entered Into getPaymentsList service method");
        model.addAttribute(MODEL_PAYMENTS, applicationDaoImpl.getPaymentsList(id, model));
        logger.info("End of getPaymentsList service method");
        return viewName;
    }
}