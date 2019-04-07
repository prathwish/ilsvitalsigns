package com.example.ilsvitalsign_be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ilsvitalsign_be.entity.CodeValueEntity;



@Repository
public interface CodeValueRepository extends JpaRepository<CodeValueEntity, Integer>{

	CodeValueEntity findByDisplay(String displayName);

	CodeValueEntity findByCode(String codeName);
	
}
