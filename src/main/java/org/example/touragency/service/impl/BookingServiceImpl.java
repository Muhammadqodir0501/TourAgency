package org.example.touragency.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.touragency.model.enity.Booking;
import org.example.touragency.model.enity.Tour;
import org.example.touragency.model.enity.User;
import org.example.touragency.repository.BookingRepository;
import org.example.touragency.repository.TourRepository;
import org.example.touragency.repository.UserRepository;
import org.example.touragency.service.abstractions.BookingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final TourRepository tourRepository;
    private final TourServiceImpl tourServiceImpl;

    @Override
    public Booking addBooking(UUID userId, UUID tourId) {

        Tour existTour = tourRepository.getTourById(tourId);
        if(existTour == null) {
            throw new RuntimeException("Tour not found");
        }

        User existUser = userRepository.getUserById(userId);
        if(existUser == null) {
            throw new RuntimeException("User not found");
        }

        if(!existTour.isAvailable()) {
            throw new RuntimeException("Tour not available");
        }
        Booking newBooking = Booking.builder()
                .userId(existUser.getId())
                .tourId(existTour.getId())
                .build();

        bookingRepository.addBooking(newBooking);
        tourServiceImpl.tourIsBooked(existTour);

        return newBooking;
    }

    @Override
    public List<Booking> getUsersBookings(UUID userId) {
        return bookingRepository.getUsersBookings(userId);
    }

    @Override
    public void cancelBooking(UUID userId, UUID tourId) {
        tourServiceImpl.tourBookingIsCanceled(tourId);
        bookingRepository.removeBooking(userId, tourId);
    }
}
