package com.sp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tbl_group_master")
public class GroupMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gm_id")
	private Long gmId;

	@Column(name = "gm_name")
	private String gmName;

	@Column(name = "is_active")
	private Boolean isActive;

}
