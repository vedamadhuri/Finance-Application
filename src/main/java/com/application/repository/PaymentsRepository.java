package com.application.repository;

import com.application.model.GroupUser;
import com.application.model.Payments;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentsRepository extends CrudRepository<Payments,Long> {

    List<Payments> findByGroupUsers(GroupUser groupUser);

    Payments findBytransactionHistoryList(Payments payments);

    @Query("select amount from Payments where mobileNumber=:mobileNumber")
    List<Long> getAmountDetails(@Param("mobileNumber") long mobileNumber);


}