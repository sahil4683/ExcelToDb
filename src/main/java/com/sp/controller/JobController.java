package com.sp.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sp.config.ExcelReader;
import com.sp.entity.GroupCompanyMapping;
import com.sp.entity.GroupMaster;

@RestController
@RequestMapping("/jobs")
public class JobController {

	@Autowired
	ExcelReader excelReader;

	@GetMapping("/importCustomers")
	public String importCsvToDBJob() throws IOException {

		StringBuilder br = new StringBuilder();
		
		List<GroupMaster> groups = excelReader.readGroupMaster(new FileSystemResource("src/main/resources/gp.xlsx").getInputStream());		
		long groupcount = excelReader.writeGroupMaster(groups);
		
		br.append("Group Count : "+groupcount );
		br.append(" | ");
		
		List<GroupCompanyMapping> companies = excelReader.readGroupMasterCompanyMap(new FileSystemResource("src/main/resources/gp.xlsx").getInputStream());
		long compcount = excelReader.writeGroupMasterCompanyMap(companies);
		
		br.append("Company Count : "+compcount );
		
		return br.toString();

	}

}
