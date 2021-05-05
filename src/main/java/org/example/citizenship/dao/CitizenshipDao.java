package org.example.citizenship.dao;

import org.example.citizenship.model.Citizenship;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CitizenshipDao extends JpaRepository<Citizenship, Long> {

    Citizenship findCitizenshipByCode(String code);
    boolean existsByCode(String code);

}
