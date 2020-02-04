package com.application.repository;

import com.application.model.Group;
import com.application.model.GroupUser;
import com.application.model.Payments;
import com.application.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupUserRepository extends CrudRepository<GroupUser, Long> {

    List<Payments> findByPaymentsList(Payments payments);
    List <GroupUser> findByGroups(Group group);
    GroupUser findByUsers(User user);
}
