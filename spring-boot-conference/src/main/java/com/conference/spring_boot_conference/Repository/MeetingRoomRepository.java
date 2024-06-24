package com.conference.spring_boot_conference.Repository;

import com.conference.spring_boot_conference.Entity.MeetingRoom;
import com.conference.spring_boot_conference.Entity.MeetingRoomStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

@Repository
public interface MeetingRoomRepository extends JpaRepository<MeetingRoom, Long> {
    Page<MeetingRoom> findByCompanyIdAndStatus(String companyId, MeetingRoomStatus status, Pageable pageable);

}
