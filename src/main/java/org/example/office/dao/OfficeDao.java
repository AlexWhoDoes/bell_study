package org.example.office.dao;

import com.sun.istack.Nullable;
import org.example.office.model.Office;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface OfficeDao extends JpaRepository<Office, Long>, JpaSpecificationExecutor<Office> {

    boolean existsOfficeById(Long id);

    @Override
    List<Office> findAll (@Nullable Specification<Office> specification);

}
