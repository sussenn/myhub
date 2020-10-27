package com.itcodes.myhub.idcardshow.dao;

import com.itcodes.myhub.idcardshow.pojo.entity.Useres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UseresDao extends JpaRepository<Useres,String>, JpaSpecificationExecutor<Useres> {
}
