package com.sp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sp.entity.GroupMaster;

public interface GroupMasterRepository extends JpaRepository<GroupMaster, Long> {
	GroupMaster findByGmName(String gmName);
}