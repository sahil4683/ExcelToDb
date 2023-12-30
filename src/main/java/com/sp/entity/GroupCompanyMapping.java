package com.sp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tbl_group_company_mapping")
@Data
public class GroupCompanyMapping {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "gcm_id")
	private Long gcmId;

	@Column(name = "gcm_company_name")
	private String companyName;

	@Column(name = "gcm_isin")
	private String isin;

	@ManyToOne
	@JoinColumn(name = "gcm_gm_id")
	private GroupMaster groupMaster;

}
