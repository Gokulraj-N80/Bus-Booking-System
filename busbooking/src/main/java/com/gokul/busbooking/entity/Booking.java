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
    private Long userId;
    private String busName;
    private String source;
    private String destination;
    private String departureTime;
    private String departureDate;
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

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getBusName() { return busName; }
    public void setBusName(String busName) { this.busName = busName; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public String getDepartureTime() { return departureTime; }
    public void setDepartureTime(String departureTime) { this.departureTime = departureTime; }

    public String getDepartureDate() { return departureDate; }
    public void setDepartureDate(String departureDate) { this.departureDate = departureDate; }
}