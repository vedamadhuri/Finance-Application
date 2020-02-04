package com.application.repository;

import com.application.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {


    long countByGroupName(String groupname);

    List<User> findAllUsersByGroupName(String groupname);

    User findByGroupName(String groupname);

    @Modifying
    @Query("select emailId from User")
    void displayUserEmailId(@Param("userId") long userId);

    @Modifying
    @Query("delete User where groupName=:groupName")
    void deleteByGroupName(@Param("groupName") String groupName);

    @Modifying
    @Query("delete User where userid=:userid")
    void deleteByUserid(@Param("userid") long userid);

    @Modifying
    @Query("select mobileNumber from User where groupName=:groupName")
    List<User> getUserDetails(@Param("groupName") String groupName);


    @Modifying
    @Query("select emailId from User")
    List<String> displayEmailId();


    @Modifying
    @Query("select mobileNumber from User")
    List<Long> getMobileNumberDetails();


    @Modifying
    @Query("select userName from User where groupName=:groupName")
    List<String> selectUserByGroup(@Param("groupName") String groupName);

    @Query("select userName from User where userId=:userId")
    String getUserNameById(@Param("userId") Long userId);

    @Query("select groupName from User where userId=:userId")
    String getGroupNameById(@Param("userId") Long userId);


    /*@Modifying
    @Query("select count(userName) from User where groupName=:groupName")
    public int count(@Param("groupName")String groupName);*/


}


