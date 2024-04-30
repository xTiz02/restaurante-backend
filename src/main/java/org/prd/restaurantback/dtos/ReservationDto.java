package org.prd.restaurantback.dtos;

import org.prd.restaurantback.enums.ReservationStatus;

import java.util.Date;

public class ReservationDto {

    private Long id;
    private String tableType;
    private String description;


    private String customerUsername;

    public ReservationDto() {
    }

    public ReservationDto(Long id, String tableType, String description, String customerUsername) {
        this.id = id;
        this.tableType = tableType;
        this.description = description;


        this.customerUsername = customerUsername;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    @Override
    public String toString() {
        return "ReservationDto{" +
                "id=" + id +
                ", tableType='" + tableType + '\'' +
                ", description='" + description + '\'' +


                ", customerUsername='" + customerUsername + '\'' +
                '}';
    }
}

