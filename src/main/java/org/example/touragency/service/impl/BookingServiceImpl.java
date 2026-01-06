package org.example.touragency.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.touragency.dto.response.BookingResponseDto;
import org.example.touragency.model.entity.Booking;
import org.example.touragency.model.entity.Tour;
import org.example.touragency.model.entity.User;
import org.example.touragency.repository.BookingRepository;
import org.example.touragency.repository.TourRepository;
import org.example.touragency.repository.UserRepository;
import org.example.touragency.service.abstractions.BookingService;
import org.example.touragency.service.abstractions.TourService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final TourRepository tourRepository;
    private final TourService tourService;

    @Override
    public BookingResponseDto addBooking(UUID userId, UUID tourId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new RuntimeException("Tour not found"));

        if (!tour.isAvailable()) {
            throw new RuntimeException("Tour not available");
        }

        Booking booking = Booking.builder()
                .user(user)
                .tour(tour)
                .build();

        bookingRepository.save(booking);
        tourService.tourIsBooked(tour);

        return new BookingResponseDto(
                booking.getId(),
                user.getId(),
                tour.getId()
        );
    }


    @Override
    public List<BookingResponseDto> getUsersBookings(UUID userId) {

        List<Booking> bookings = bookingRepository.findAllBookingsByUserId(userId);

        return bookings.stream()
                .map(b -> new BookingResponseDto(
                        b.getId(),
                        b.getUser().getId(),
                        b.getTour().getId()
                ))
                .toList();
    }


    @Override
    public void cancelBooking(UUID userId, UUID tourId) {
        tourService.tourBookingIsCanceled(tourId);
        bookingRepository.deleteByUserIdAndTourId(userId, tourId);
    }

}
