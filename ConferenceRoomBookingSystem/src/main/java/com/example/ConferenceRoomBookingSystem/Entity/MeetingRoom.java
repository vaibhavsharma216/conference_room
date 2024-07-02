package com.example.ConferenceRoomBookingSystem.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "MeetingRoom")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MeetingRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "meetingRoomName")
    private String meetingRoomName;

    @Column(name = "companyId")
    private String companyId;

    @Enumerated(EnumType.STRING)
    private MeetingRoomStatus status;

    @Column(name = "bookedBy")
    private String bookedBy;

    @Column(name = "bookingTime")
    private Timestamp bookingTime;

    public String getBookedBy() {
        return bookedBy;
    }

    public void setBookedBy(String bookedBy) {
        this.bookedBy = bookedBy;
    }

    public Timestamp getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(Timestamp bookingTime) {
        this.bookingTime = bookingTime;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMeetingRoomName() {
        return meetingRoomName;
    }

    public void setMeetingRoomName(String meetingRoomName) {
        this.meetingRoomName = meetingRoomName;
    }

    public MeetingRoomStatus getStatus() {
        return status;
    }

    public void setStatus(MeetingRoomStatus status) {
        this.status = status;
    }

    private Timestamp checkInTime;
    private Timestamp checkOutTime;


    public Timestamp getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Timestamp checkInTime) {
        this.checkInTime = checkInTime;
    }

    public Timestamp getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(Timestamp checkOutTime) {
        this.checkOutTime = checkOutTime;
    }
}