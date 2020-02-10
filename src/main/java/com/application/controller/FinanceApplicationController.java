package com.application.controller;

import com.application.dto.AdminDto;
import com.application.dto.GroupDto;
import com.application.dto.UserDto;
import com.application.exception.UserNotFoundException;
import com.application.messageconstants.FinanceApplicationConstants;
import com.application.service.FinanceApplicationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class FinanceApplicationController implements FinanceApplicationConstants {
    private static final Logger logger = LoggerFactory.getLogger(FinanceApplicationController.class);

    @Autowired
    private FinanceApplicationServiceImpl financeApplicationServiceImpl;

    /**
     * @return Application enters into login page when it reaches this default url
     */
    @GetMapping(value = "/")
    public String login() {
        logger.info("Into Login ControllerMethod");
        logger.debug("Into Login ControllerMethod");
        return VIEWNAME_LOGIN;
    }





    /**
     * @param admindto
     * @param model
     * @return loginPage
     * <p>
     * This method is used to " Check the login details and proceed further "
     */
    @PostMapping(value = "/authenticate-user")
    public String authenticateUser( AdminDto admindto, Model model) {
        logger.info("Entered Into authenticateUser ControllerMethod");
        logger.debug("Entered Into authenticateUser ControllerMethod");
        if ((admindto.getAdministrator() == "") || (admindto.getPassword() == "")) {
            model.addAttribute(MESSAGE_NAME, LOGINERROR_MESSAGE);
            logger.info("End of authenticateUser ControllerMethod");
            logger.debug("End of authenticateUser ControllerMethod");
            return VIEWNAME_LOGIN;

        }
        if (financeApplicationServiceImpl.isUserAuthenticated(admindto)) {
            logger.info("End of authenticateUser ControllerMethod");
            logger.debug("End of authenticateUser ControllerMethod");
            return financeApplicationServiceImpl.getGroupsDetails(model, VIEWNAME_GROUP);
        } else {
            model.addAttribute(MESSAGE_NAME, LOGININVALID_MESSAGE);
            logger.info("End of authenticateUser ControllerMethod");
            logger.debug("End of authenticateUser ControllerMethod");
            return VIEWNAME_LOGIN;
        }
    }

    /**
     * @return HomePage
     * <p>
     * This method is to enter  into home page if the login details are correct
     */
/*    @GetMapping(value = "/home")
    public String loginMenu(Model model) {
        logger.info("Into loginMenu ControllerMethod");
        logger.debug("Into loginMenu ControllerMethod");
        return financeApplicationServiceImpl.getGroupsDetails(model, VIEWNAME_GROUP);
    }*/

    /**
     * @param model
     * @return When the url is followed by details page,the application enters into details page
     * which gives the details of groups and the users in that group
     */

    @GetMapping(value = "/detailsPage")
    public String detailsPage(Model model) {
        logger.info("Into detailsPage ControllerMethod");
        logger.debug("Into detailsPage ControllerMethod");
        return financeApplicationServiceImpl.getUsersDetails(model, VIEWNAME_DETAILS);
    }

    /**
     * @return This method logout from the application re-directs to the login html page
     */
    @GetMapping(value = "/logoutAdmin")
    public String logout() {
        logger.info("Into logout ControllerMethod");
        logger.debug("Into logout ControllerMethod");
        return VIEWNAME_LOGIN;
    }

    /**
     * @param model
     * @return This method will redirect to the group html page
     */
    @GetMapping(value = "/groupPage")
    public String groupPage(Model model) {
        logger.info("Into groupPage ControllerMethod");
        logger.debug("Into groupPage ControllerMethod");
        return financeApplicationServiceImpl.getGroupsDetails(model, VIEWNAME_GROUP);
    }

    /**
     * @return this method will re-direct to the "addgroup" html page
     */
    @GetMapping(value = "/addGroup")
    public String addGroup() {
        logger.info("Into addGroup ControllerMethod");
        logger.debug("Into addGroup ControllerMethod");
        return VIEWNAME_ADDGROUP;
    }

    /**
     * @param groupId
     * @param model
     * @param viewName
     * @return when we have lockGroup/grpId as url the appliction enters this method locks the group and
     * re-direct to the same group html page
     */
    @GetMapping(value = "/lockGroup/{groupId}")
    public String lockGroup(@PathVariable("groupId") long groupId, Model model, String viewName) {
        logger.info("Into lockGroup ControllerMethod");
        logger.debug("Into lockGroup ControllerMethod");
        return financeApplicationServiceImpl.lockGroup(groupId, model, VIEWNAME_GROUP);
    }

    /**
     * @param transactionId
     * @param paymentId
     * @param model
     * @param viewName
     * @return This interact with service layer to
     * check the payment conditions based on the id passed
     */
    @GetMapping(value = "/transactionMessage/{transactionId}/{paymentId}")
    public String transactionMessage(@PathVariable("transactionId") long transactionId, @PathVariable("paymentId") long paymentId, Model model, String viewName) {
        logger.info("Into transactionMessage ControllerMethod");
        logger.debug("Into transactionMessage ControllerMethod");
        return financeApplicationServiceImpl.transactionMessage(transactionId, paymentId, model, VIEWNAME_TRANSACTIONDETAILS);
    }

    /**
     *
     * @param groupDto
     * @param model
     * @return This method will re-direct to the group html page submitting the group form details into the database
     */
    @PostMapping(value = "/submitGroupDetails")
    public String addGroupDetails(@ModelAttribute GroupDto groupDto, Model model) {
        logger.info("Into addGroupDetails ControllerMethod");
        logger.debug("Into addGroupDetails ControllerMethod");
        return financeApplicationServiceImpl.addGroup(groupDto, model, VIEWNAME_GROUP);

    }

    /**
     * @param groupName
     * @param groupDto
     * @param model
     * @return This method is used when a group has to be deleted and it re-directs to the group html page
     */
    @GetMapping(value = "/deletegroupName/{groupName}")
    public String deleteByGroupName(@PathVariable("groupName") String groupName, GroupDto groupDto, Model model) {
        logger.info("Into deleteByGroupName ControllerMethod");
        logger.debug("Into deleteByGroupName ControllerMethod");
        return financeApplicationServiceImpl.deleteGroupAndUser(groupName, model, VIEWNAME_GROUP);
    }

    /**
     * @param model
     * @return This url will re-direct to user html page which gives the details of the user
     */
    @GetMapping(value = "/userPage")
    public String userDetails(Model model) {
        logger.info("Into userDetails ControllerMethod");
        logger.debug("Into userDetails ControllerMethod");
        return financeApplicationServiceImpl.getUsersDetails(model, VIEWNAME_USER);
    }

    /**
     * @param model
     * @return This url will re-direct to the adduser html page which gives us the form to add a user
     */
    @GetMapping(value = "/addUser")
    public String addUser(Model model) {
        logger.info("Into addUser ControllerMethod");
        logger.debug("Into addUser ControllerMethod");
        return financeApplicationServiceImpl.getGroupsDetails(model, VIEWNAME_ADDUSER);
    }

    /**
     * @param userDto
     * @param result
     * @param model
     * @return this url will submit the details of user from the form and re-directs to the user html page
     */
    @PostMapping(value = "/submitUserDetails")
    public String addUserDetails(@Valid @ModelAttribute("users") UserDto userDto, BindingResult result, Model model) {
        logger.info("Into addUserDetails ControllerMethod");
        logger.debug("Into addUserDetails ControllerMethod");
        return financeApplicationServiceImpl.addUser(userDto, model, result, VIEWNAME_USER);
    }

    /**
     * @param userId
     * @param user
     * @param model
     * @return
     * @throws UserNotFoundException this will delete the respective user details through userid
     */
    @GetMapping(value = "/deleteUserByUserId/{userId}")
    public String deleteUser(@PathVariable long userId, UserDto user, Model model) throws UserNotFoundException {
        logger.info("Into deleteUser ControllerMethod");
        logger.debug("Into deleteUser ControllerMethod");
        return financeApplicationServiceImpl.deleteUser(userId, model, VIEWNAME_USER);
    }

    /**
     * @param groupId
     * @param model
     * @return this will re-direct to the addnewgroup html page where we can edit the details of the group
     */
    @GetMapping(value = "/editGroupDetails/{groupId}")
    public String updateGroup(@PathVariable long groupId, Model model) {
        logger.info("Into updateGroup ControllerMethod");
        logger.debug("Into updateGroup ControllerMethod");
        return financeApplicationServiceImpl.getGroup(groupId, model, VIEWNAME_ADDNEWGROUP);

    }

    /**
     * @param userId
     * @param model
     * @return this will re-direct to the addnewuser html page where we can edit the details of the user
     */
    @GetMapping(value = "/editDetails/{userId}")
    public String updateUser(@PathVariable long userId, Model model) {
        logger.info("Into updateUser ControllerMethod");
        logger.debug("Into updateUser ControllerMethod");
        return financeApplicationServiceImpl.getUser(userId, model, VIEWNAME_ADDNEWUSER);
    }

    /**
     * @param userDto
     * @param model
     * @return this will update the edited details of the user
     */
    @PostMapping(value = "/updateUserDetails")
    public String updateUserDetails(UserDto userDto, Model model, String viewName) {
        logger.info("Into updateUserDetails ControllerMethod");
        logger.debug("Into updateUserDetails ControllerMethod");
        return financeApplicationServiceImpl.updateUser(userDto, model, viewName);
    }

    /**
     * @param groupDto
     * @param model
     * @return this will update the edited details of the group and return group html page
     */
    @PostMapping(value = "/updateGroupDetails")
    public String updateGroupDetails(GroupDto groupDto, Model model) {
        logger.info("Into updateGroupDetails ControllerMethod");
        logger.debug("Into updateGroupDetails ControllerMethod");
        return financeApplicationServiceImpl.updateGroup(groupDto, model, VIEWNAME_GROUP);
    }

    /**
     * @param groupName
     * @param model
     * @return To display the users of a group
     */
    @GetMapping(value = "/displayUsers/{groupName}")
    public String displayUsers(@PathVariable("groupName") String groupName, Model model) {
        logger.info("Into displayUsers ControllerMethod");
        logger.debug("Into displayUsers ControllerMethod");
        return financeApplicationServiceImpl.getUsersByGroup(groupName, model, VIEWNAME_DISPLAYUSERS);
    }

    /**
     * @param userId
     * @param model
     * @return After the payment of the user is enabled, this method helps in re-directing to paymentdetails html page
     */
    @GetMapping(value = "/paymentDetails/{userId}")
    public String payments(@PathVariable("userId") long userId, Model model) {
        logger.info("Into payments ControllerMethod");
        logger.debug("Into payments ControllerMethod");
        return financeApplicationServiceImpl.getPaymentDetails(userId, model, VIEWNAME_PAYMENTDETAILS);
    }

    /**
     * @param amountPaid
     * @param paymentsId
     * @param amount
     * @param months
     * @param balanceAmount
     * @param model
     * @return this will save the amountpaid by the user in the database through a dialogue box and returns back to the same page(paymentdetails)
     */
    @GetMapping(value = "/submitAmountDetails/{amount}/{paymentsId}/{months}/{amountPaid}/{balanceAmount}")
    public String submitAmountDetails(@PathVariable("amount") double amount, @PathVariable("paymentsId") long paymentsId, @PathVariable("months") long months, @PathVariable("amountPaid") double amountPaid,
                                      @PathVariable("balanceAmount") double balanceAmount, Model model) {
        logger.info("Into submitAmountDetails ControllerMethod");
        logger.debug("Into submitAmountDetails ControllerMethod");
        return financeApplicationServiceImpl.calculatePaymentDetails(paymentsId, months, amountPaid, balanceAmount, amount, VIEWNAME_PAYMENTDETAILS, model);
    }

    /**
     * @param paymentsId
     * @param amountPaid
     * @param model
     * @return transactionhistory in payments details  page will bring to control to this method and will display  transactiondetails html page
     */
    @GetMapping(value = "/transactionHistory/{paymentsId}/{amountPaid}")
    public String showTransactionDetails(@PathVariable("paymentsId") long paymentsId, @PathVariable("amountPaid") double amountPaid, Model model) {
        logger.info("Into showTransactionDetails ControllerMethod");
        logger.debug("Into showTransactionDetails ControllerMethod");
        return financeApplicationServiceImpl.transactionDetails(paymentsId, amountPaid, model, VIEWNAME_TRANSACTIONDETAILS);
    }

    /**
     * @param transactionId
     * @param model
     * @return this will give the  paymentdetails html page
     */
    @GetMapping(value = "/transactionDetails/{transactionId}")
    public String paymentDetails(@PathVariable("transactionId") long transactionId, Model model) {
        logger.info("Into paymentDetails ControllerMethod");
        logger.debug("Into paymentDetails ControllerMethod");
        return financeApplicationServiceImpl.getPaymentDetails(transactionId, model, VIEWNAME_PAYMENTDETAILS);

    }

    /**
     * @param paymentsId
     * @param model
     * @return this will give the  paymentdetails html page
     */
   /* @GetMapping(value = "/paymentsList/{paymentsId}")
    public String paymentList(@PathVariable long paymentsId, Model model) {
        logger.info("Into paymentList ControllerMethod");
        logger.debug("Into paymentList ControllerMethod");
        return financeApplicationServiceImpl.getPaymentsList(paymentsId, model, VIEWNAME_PAYMENTDETAILS);
    }*/
}


