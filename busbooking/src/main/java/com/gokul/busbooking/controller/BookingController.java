package com.gokul.busbooking.controller;

import com.gokul.busbooking.entity.Booking;
import com.gokul.busbooking.service.BookingService;
import com.gokul.busbooking.entity.User;
import com.gokul.busbooking.repository.UserRepository;
import com.gokul.busbooking.repository.BookingRepository;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;

    public BookingController(BookingService bookingService, UserRepository userRepository, BookingRepository bookingRepository) {
        this.bookingService = bookingService;
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
    }

    @PostMapping("/book")
    public Booking bookTicket(@RequestBody Booking booking, Principal principal) {
        if (principal != null) {
            User user = userRepository.findByEmail(principal.getName()).orElse(null);
            if (user != null) {
                booking.setUserId(user.getId());
            }
        }
        return bookingService.bookTicket(booking);
    }

    @GetMapping("/my-bookings")
    public List<Booking> getMyBookings(Principal principal) {
        if (principal != null) {
            User user = userRepository.findByEmail(principal.getName()).orElse(null);
            if (user != null) {
                return bookingRepository.findByUserId(user.getId());
            }
        }
        return List.of();
    }
}