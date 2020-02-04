package com.application.repository;

import com.application.model.Group;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface GroupRepository extends CrudRepository<Group, Long> {

    Group findByGroupName(String groupName);

    long countByGroupName(String groupname);

    @Transactional
    @Modifying
    @Query("delete Group where groupName=:groupName")
    void deleteByGroupName(@Param("groupName") String groupName);

    @Query("select groupName from Group")
    List<String> displayGroupsName();

    @Query("select monthsDuration from Group where groupName=:groupName")
    Long getMonthsDurationByGroupName(@Param("groupName") String groupName);

    @Query("select groupSize from Group where groupName=:groupName")
    Integer groupsize(@Param("groupName") String groupName);

}
