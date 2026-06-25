package com.gokul.busbooking.service;

import com.gokul.busbooking.entity.Booking;
import com.gokul.busbooking.entity.Bus;
import com.gokul.busbooking.repository.BookingRepository;
import com.gokul.busbooking.repository.BusRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BusRepository busRepository;

    public BookingService(
            BookingRepository bookingRepository,
            BusRepository busRepository) {

        this.bookingRepository = bookingRepository;
        this.busRepository = busRepository;
    }

    public Booking bookTicket(
            Booking booking) {

        Bus bus =
                busRepository.findById(
                                booking.getBusId())
                        .orElseThrow();

        if(bus.getSeats()
                < booking.getSeatsBooked())
        {
            throw new RuntimeException(
                    "Not enough seats");
        }

        bus.setSeats(
                bus.getSeats()
                        - booking.getSeatsBooked());

        busRepository.save(bus);

        return bookingRepository.save(booking);
    }
}