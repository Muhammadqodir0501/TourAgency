package org.example.touragency.repository;

import org.example.touragency.model.enity.Booking;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class BookingRepository {

    private final Map<UUID, Booking> bookings = new ConcurrentHashMap<>();

    public Booking addBooking(Booking booking) {
        bookings.put(booking.getId(), booking);
        return booking;
    }

    public Booking getBooking(UUID userId, UUID tourId) {
        return bookings.values().stream()
                .filter(booking -> booking.getUserId().equals(userId)
                && booking.getTourId().equals(tourId))
                .findFirst().orElse(null);
    }

    public void removeBooking(UUID userId, UUID tourId) {
        bookings.values().removeIf(b -> b.getUserId().equals(userId)
                && b.getTourId().equals(tourId));
    }

    public List<Booking> getUsersBookings(UUID userId) {
        return bookings.values().stream()
                .filter(booking -> booking.getUserId().equals(userId))
                .toList();
    }

}
