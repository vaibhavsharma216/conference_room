package com.conference.spring_boot_conference.Service;

import com.conference.spring_boot_conference.Entity.MeetingRoom;

import java.util.List;

public interface MeetingRoomService {
    MeetingRoom addMeetingRoom(String meetingRoomName, String companyId);
    MeetingRoom bookMeetingRoom(String companyId, String employeeName, String meetingRoomName);
    List<MeetingRoom> checkMeetingRoomAvailability(String companyId, String meetingRoomName);
    List<MeetingRoom> getPendingRequests(String companyId);
    MeetingRoom approveBookingRequest(Long requestId);
    MeetingRoom denyBookingRequest(Long requestId);
}