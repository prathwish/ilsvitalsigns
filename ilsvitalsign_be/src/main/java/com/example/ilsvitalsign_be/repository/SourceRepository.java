package com.example.ilsvitalsign_be.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ilsvitalsign_be.entity.SourceEntity;

public interface SourceRepository extends JpaRepository<SourceEntity, Integer> {

	SourceEntity findBySourceName(String comment);
}
