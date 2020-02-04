package com.application.repository;

import com.application.model.Payments;
import com.application.model.TransactionHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionHistoryRepository extends CrudRepository<TransactionHistory, Long> {


    List<TransactionHistory> findByPayments(Payments payments);

}
