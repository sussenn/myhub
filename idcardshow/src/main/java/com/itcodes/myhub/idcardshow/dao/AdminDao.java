package com.itcodes.myhub.idcardshow.dao;

import com.itcodes.myhub.idcardshow.pojo.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminDao extends JpaRepository<Admin,String> {
    Admin findByUsername(String username);
}
