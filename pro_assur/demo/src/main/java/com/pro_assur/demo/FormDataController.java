package com.pro_assur.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/api")
public class FormDataController {

    private final FormDataService formDataService;
    private final ExcelExportService excelExportService;

    public FormDataController(FormDataService formDataService, ExcelExportService excelExportService) {
        this.formDataService = formDataService;
        this.excelExportService = excelExportService;
    }

    @PostMapping("/submit")
    @ResponseBody
    public ResponseEntity<Map<String, String>> submitForm(@RequestBody Map<String, String> formData) {
        System.out.println("Received data: " + formData);  
        try {
            FormData data = new FormData();
            data.setLastName(formData.get("lastName"));
            data.setFirstName(formData.get("firstName"));
            data.setIdNumber(formData.get("idNumber"));
            data.setEmail(formData.get("email"));
            data.setDateOfBirth(formData.get("dateOfBirth"));
            data.setPhone(formData.get("phone"));
            data.setAddress(formData.get("address"));
            data.setRegion(formData.get("region"));
            data.setDestination(formData.get("destination"));
            data.setStartDate(formData.get("startDate"));
            data.setEndDate(formData.get("endDate"));
            data.setNumberOfTravelers(Integer.parseInt(formData.get("numberOfTravelers")));
            
            formDataService.saveFormData(data);
            System.out.println("Data saved: " + data); 
            
            Map<String, String> response = new HashMap<>();
            response.put("message", "Form submitted successfully");
            return ResponseEntity.ok(response);
    
        } catch (NumberFormatException e) {
            e.printStackTrace(); 
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Invalid number format for numberOfTravelers");
            return ResponseEntity.badRequest().body(errorResponse);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "An error occurred while processing your request");
            return ResponseEntity.status(500).body(errorResponse);
        }
    }
    

    @RequestMapping("/export")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=form_data.xlsx");

        ByteArrayInputStream stream = excelExportService.exportToExcel();
        org.apache.commons.io.IOUtils.copy(stream, response.getOutputStream());
    }
}
