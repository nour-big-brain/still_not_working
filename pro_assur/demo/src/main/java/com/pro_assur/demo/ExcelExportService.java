package com.pro_assur.demo;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class ExcelExportService {

    private final FormDataRepository formDataRepository;

    public ExcelExportService(FormDataRepository formDataRepository) {
        this.formDataRepository = formDataRepository;
    }

    /**
     * Exports form data to an Excel file.
     *
     * @return
     * @throws IOException 
     */
    public ByteArrayInputStream exportToExcel() throws IOException {
        List<FormData> formDataList = formDataRepository.findAll();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Form Data");

            // Create header row
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("ID");
            header.createCell(1).setCellValue("Last Name");
            header.createCell(2).setCellValue("First Name");
            header.createCell(3).setCellValue("ID Number");
            header.createCell(4).setCellValue("Email");
            header.createCell(5).setCellValue("Date of Birth");
            header.createCell(6).setCellValue("Phone");
            header.createCell(7).setCellValue("Address");
            header.createCell(8).setCellValue("Region");
            header.createCell(9).setCellValue("Destination");
            header.createCell(10).setCellValue("Start Date");
            header.createCell(11).setCellValue("End Date");
            header.createCell(12).setCellValue("Number of Travelers");

            int rowIdx = 1;
            for (FormData data : formDataList) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(data.getId());
                row.createCell(1).setCellValue(data.getLastName());
                row.createCell(2).setCellValue(data.getFirstName());
                row.createCell(3).setCellValue(data.getIdNumber());
                row.createCell(4).setCellValue(data.getEmail());

                // Date formatting
                String dob = data.getDateOfBirth() != null ? dateFormat.format(data.getDateOfBirth()) : "";
                row.createCell(5).setCellValue(dob);

                row.createCell(6).setCellValue(data.getPhone());
                row.createCell(7).setCellValue(data.getAddress());
                row.createCell(8).setCellValue(data.getRegion());
                row.createCell(9).setCellValue(data.getDestination());

                // Date formatting
                String startDate = data.getStartDate() != null ? dateFormat.format(data.getStartDate()) : "";
                row.createCell(10).setCellValue(startDate);

                String endDate = data.getEndDate() != null ? dateFormat.format(data.getEndDate()) : "";
                row.createCell(11).setCellValue(endDate);

                row.createCell(12).setCellValue(data.getNumberOfTravelers());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}
