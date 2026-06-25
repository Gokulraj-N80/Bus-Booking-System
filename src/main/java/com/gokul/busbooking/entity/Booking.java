package com.gokul.busbooking.entity;

import jakarta.persistence.*;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String passengerName;
    private String mobileNumber;
    private Long busId;
    private int seatsBooked;

    public Booking() {}

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getPassengerName() { return passengerName; }

    public void setPassengerName(String passengerName) { this.passengerName = passengerName; }

    public String getMobileNumber() { return mobileNumber; }

    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }

    public Long getBusId() { return busId; }

    public void setBusId(Long busId) { this.busId = busId; }

    public int getSeatsBooked() { return seatsBooked; }

    public void setSeatsBooked(int seatsBooked) { this.seatsBooked = seatsBooked; }
}