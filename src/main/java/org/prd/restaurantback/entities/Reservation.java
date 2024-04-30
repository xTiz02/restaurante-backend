package org.prd.restaurantback.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.prd.restaurantback.dtos.ReservationDto;
import org.prd.restaurantback.enums.ReservationStatus;

import java.util.Date;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String tableType;
    private String description;
    private Date dateTime;
    private ReservationStatus reservationStatus;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)//si se borra el usuario se borra la reserva
    @JsonIgnore
    private User user;

    public Reservation() {
    }

    public Reservation(Long id, String tableType, String description, Date date, ReservationStatus reservationStatus, User user) {
        this.id = id;
        this.tableType = tableType;
        this.description = description;
        this.dateTime = date;
        this.reservationStatus = reservationStatus;
        this.user = user;
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

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ReservationDto getReservationDto(){
        return new ReservationDto(this.id, this.tableType, this.description, this.user.getEmail());
    }
}
