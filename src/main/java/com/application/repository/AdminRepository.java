package com.application.repository;

import com.application.model.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository<Admin, Long> {

    Admin getAdminByAdministratorAndPassword(String administrator, String password);

}
