package com.pro_assur.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "FORMDATA")
public class FormData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lastName;
    private String firstName;
    private String idNumber;
    private String email;
    private String dateOfBirth;
    private String phone;
    private String address;
    private String region;
    private String destination;
    private String startDate;
    private String endDate;
    private Integer numberOfTravelers;

    // Constructors
    public FormData() {}

    public FormData(String lastName, String firstName, String idNumber, String email, String dateOfBirth, 
                    String phone, String address, String region, String destination, String startDate, 
                    String endDate, Integer numberOfTravelers) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.idNumber = idNumber;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.address = address;
        this.region = region;
        this.destination = destination;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfTravelers = numberOfTravelers;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getIdNumber() { return idNumber; }
    public void setIdNumber(String idNumber) { this.idNumber = idNumber; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }
    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }
    public Integer getNumberOfTravelers() { return numberOfTravelers; }
    public void setNumberOfTravelers(Integer numberOfTravelers) { this.numberOfTravelers = numberOfTravelers; }
}
