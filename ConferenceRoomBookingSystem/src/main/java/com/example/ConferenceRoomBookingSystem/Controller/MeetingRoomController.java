package com.example.ConferenceRoomBookingSystem.Controller;

import com.example.ConferenceRoomBookingSystem.Entity.MeetingRoom;
import com.example.ConferenceRoomBookingSystem.Service.MeetingRoomService;
import jakarta.persistence.Id;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
        import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@RestController
@RequestMapping("/meeting-rooms")
//    @Api(value = "MeetingRoom Management System", description = "Operations pertaining to meeting rooms in the MeetingRoom Management System")
public class MeetingRoomController {

    //    private static final Logger log = LoggerFactory.getLogger(MeetingRoomController.class);
    @Autowired
    private MeetingRoomService meetingRoomService;

    @PostMapping("/add")
//        @ApiOperation(value = "Add a new meeting room")
    public MeetingRoom addMeetingRoom(@RequestParam String meetingRoomName, @RequestParam String companyId) {
        return meetingRoomService.addMeetingRoom(meetingRoomName, companyId);
    }

    @PostMapping("/book")
//        @ApiOperation(value = "Book a meeting room")
    public MeetingRoom bookMeetingRoom(@RequestParam String companyId, @RequestParam String employeeName, @RequestParam String meetingRoomName) {
        return meetingRoomService.bookMeetingRoom(companyId, employeeName, meetingRoomName);
    }

    @GetMapping("/availability")
//        @ApiOperation(value = "Check meeting room availability")
    public Page<MeetingRoom> checkMeetingRoomAvailability(@RequestParam String companyId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return meetingRoomService.checkMeetingRoomAvailability(companyId, page, size);
    }


    @GetMapping("/pending")
//        @ApiOperation(value = "Check pending requests for meeting room")
    public Page<MeetingRoom> getPendingRequests(@RequestParam String companyId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        return meetingRoomService.getPendingRequests(companyId, page, size);
    }

    @PatchMapping("/approve")
//        @ApiOperation(value = "approve pending requests for meeting room")
    public ResponseEntity<MeetingRoom> approveBookingRequest(@RequestParam Long id) {
        MeetingRoom request = meetingRoomService.approveBookingRequest(id);
        return ResponseEntity.ok(request);
    }

    @PatchMapping("/deny")
//        @ApiOperation(value = "deny pending requests for meeting room")
    public ResponseEntity<MeetingRoom> denyBookingRequest(@RequestParam Long Id) {
        MeetingRoom request = meetingRoomService.denyBookingRequest(Id);
        return ResponseEntity.ok(request);
    }
}