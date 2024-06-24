package com.conference.spring_boot_conference.Service;

import com.conference.spring_boot_conference.Entity.MeetingRoom;
import com.conference.spring_boot_conference.Entity.MeetingRoomStatus;
//import com.conference.spring_boot_conference.GlobalExceptionHandling.CustomExceptions;
import com.conference.spring_boot_conference.Repository.MeetingRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
    public class MeetingRoomServiceImpl implements MeetingRoomService {

    @Autowired
    private MeetingRoomRepository meetingRoomRepository;

    @Override
    public MeetingRoom addMeetingRoom(String meetingRoomName, String companyId) {
        MeetingRoom room = new MeetingRoom();
        room.setMeetingRoomName(meetingRoomName);
        room.setCompanyId(companyId);
        room.setStatus(MeetingRoomStatus.AVAILABLE);
        return meetingRoomRepository.save(room);
    }

    @Override
    public MeetingRoom bookMeetingRoom(String companyId, String employeeName, String meetingRoomName) {
        Page<MeetingRoom> availableRoomsPage = meetingRoomRepository.findByCompanyIdAndStatus(companyId, MeetingRoomStatus.AVAILABLE, PageRequest.of(0, 10));
        List<MeetingRoom> availableRooms = availableRoomsPage.getContent();
        List<MeetingRoom> rooms = availableRooms.stream().filter(r -> r.getMeetingRoomName().compareTo(meetingRoomName) == 0).collect(Collectors.toList());
//        if (rooms.isEmpty()) {
//            throw new CustomExceptions.ResourceNotFoundException("No available meeting rooms found for company: " + companyId + " with name: " + meetingRoomName);
//        }
        MeetingRoom room = rooms.get(0);
        room.setStatus(MeetingRoomStatus.PENDING);
        room.setBookedBy(employeeName);
        room.setBookingTime(new Timestamp(System.currentTimeMillis()));
        room.setCheckInTime(new Timestamp(System.currentTimeMillis())); // Set the current time as check-in time

        // Assuming the checkout time is 1 hour after the check-in time
        Timestamp checkOutTime = new Timestamp(System.currentTimeMillis() + 3600 * 1000);
        room.setCheckOutTime(checkOutTime);
        return meetingRoomRepository.save(room);

    }

    @Override
    public List<MeetingRoom> checkMeetingRoomAvailability(String companyId, String meetingRoomName) {
        Page<MeetingRoom> availableRoomsPage = meetingRoomRepository.findByCompanyIdAndStatus(companyId, MeetingRoomStatus.AVAILABLE, PageRequest.of(0, 10));
        return availableRoomsPage.getContent();
    }


    @Override
    public List<MeetingRoom> getPendingRequests(String companyId) {
        Page<MeetingRoom> pendingRoomsPage = meetingRoomRepository.findByCompanyIdAndStatus(companyId, MeetingRoomStatus.PENDING, PageRequest.of(0, 10));
        return pendingRoomsPage.getContent();
    }

    @Override
    public MeetingRoom approveBookingRequest(Long Id) {
        MeetingRoom request = null;
        Optional<MeetingRoom> requestOptional = meetingRoomRepository.findById(Id);
        if (requestOptional.isPresent()) {
             request = requestOptional.get();
            request.setStatus(MeetingRoomStatus.APPROVED);
            return meetingRoomRepository.save(request);
        }
        return meetingRoomRepository.save(request);
//        throw new CustomExceptions.ResourceNotFoundException("Booking request not found with ID: " + Id);
    }

    @Override
    public MeetingRoom denyBookingRequest(Long Id) {
        Optional<MeetingRoom> requestOptional = meetingRoomRepository.findById(Id);
        MeetingRoom request = null;
        if (requestOptional.isPresent()) {
             request = requestOptional.get();
            request.setStatus(MeetingRoomStatus.DENIED);
            return meetingRoomRepository.save(request);
        }
        return meetingRoomRepository.save(request);

//        throw new CustomExceptions.ResourceNotFoundException("Booking request not found with ID: " + Id);
    }
}

