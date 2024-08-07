package com.conference.spring_boot_conference.Service;

import com.conference.spring_boot_conference.Entity.MeetingRoom;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MeetingRoomService {
    MeetingRoom addMeetingRoom(String meetingRoomName, String companyId);
    MeetingRoom bookMeetingRoom(String companyId, String employeeName, String meetingRoomName);
    Page<MeetingRoom> checkMeetingRoomAvailability(String companyId, int page, int size);
    Page<MeetingRoom> getPendingRequests(String companyId, int page, int size);
    MeetingRoom approveBookingRequest(Long requestId);
    MeetingRoom denyBookingRequest(Long requestId);
}