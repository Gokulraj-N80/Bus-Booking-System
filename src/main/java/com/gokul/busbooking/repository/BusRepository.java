package com.gokul.busbooking.repository;

import com.gokul.busbooking.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface BusRepository extends JpaRepository<Bus, Long> {

    List<Bus> findBySourceAndDestination(String source, String destination);

}