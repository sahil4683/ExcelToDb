package com.sp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sp.entity.GroupCompanyMapping;

public interface GroupCompanyMappingRepository extends JpaRepository<GroupCompanyMapping, Long> {
}
