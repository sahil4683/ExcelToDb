package com.sp.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sp.entity.GroupCompanyMapping;
import com.sp.entity.GroupMaster;
import com.sp.repository.GroupCompanyMappingRepository;
import com.sp.repository.GroupMasterRepository;

@Service
public class ExcelReader {

	@Autowired
	private GroupMasterRepository groupMasterRepository;
	@Autowired
	GroupCompanyMappingRepository companyMappingRepository;
	
	public List<GroupMaster> readGroupMaster(InputStream inputStream) throws IOException {

		List<GroupMaster> groups = new ArrayList<>();

		try (Workbook workbook = WorkbookFactory.create(inputStream)) {
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();

			// Skip header row
			if (rowIterator.hasNext()) {
				rowIterator.next();
			}

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (row.getCell(0).getStringCellValue() != null) {
					GroupMaster findByGmName = groupMasterRepository.findByGmName(row.getCell(0).getStringCellValue());
					if (findByGmName == null) {
						GroupMaster group = new GroupMaster();
						group.setGmName(row.getCell(0).getStringCellValue().trim());
						group.setIsActive(true);
						groups.add(group);
					}
				}
			}
		}

		return groups;
	}

	public long writeGroupMaster(List<GroupMaster> groups) {
		long counter = 0;
		for(GroupMaster group:groups) {
			System.out.println(group);
			GroupMaster findByGmName = groupMasterRepository.findByGmName(group.getGmName());
			if (findByGmName == null) {
				groupMasterRepository.save(group);
				counter ++;
			}			
		}
		return counter;
	}
	
	
	
	
	
	
	
	
	
	
	public List<GroupCompanyMapping> readGroupMasterCompanyMap(InputStream inputStream) throws IOException {

		List<GroupCompanyMapping> groups = new ArrayList<>();

		try (Workbook workbook = WorkbookFactory.create(inputStream)) {
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();

			// Skip header row
			if (rowIterator.hasNext()) {
				rowIterator.next();
			}

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (row.getCell(0).getStringCellValue() != null) {
					System.out.println(row.getCell(0).getStringCellValue());
					GroupMaster findByGmName = groupMasterRepository.findByGmName(row.getCell(0).getStringCellValue());
					if (findByGmName != null) {
						GroupCompanyMapping group = new GroupCompanyMapping();
						group.setGroupMaster(findByGmName);
						group.setCompanyName(row.getCell(1).getStringCellValue());
						group.setIsin(row.getCell(2).getStringCellValue());
						groups.add(group);
					}
				}
			}
		}

		return groups;
	}
	public long writeGroupMasterCompanyMap(List<GroupCompanyMapping> groups) {
		List<GroupCompanyMapping> result = companyMappingRepository.saveAll(groups);
		return result.size();
	}
}
