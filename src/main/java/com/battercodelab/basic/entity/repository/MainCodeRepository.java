package com.battercodelab.basic.entity.repository;

import com.battercodelab.basic.entity.MainCode;
import com.battercodelab.basic.entity.repository.custom.CustomMainCodeRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MainCodeRepository extends JpaRepository<MainCode, String>, CustomMainCodeRepository {
    Optional<MainCode> findByCode(String code);
}
