package org.example.touragency.service.abstractions;

import org.example.touragency.model.enity.Booking;

import java.util.List;
import java.util.UUID;

public interface BookingService {

    Booking addBooking(UUID userId, UUID tourId);

    List<Booking> getUsersBookings(UUID userId);

    void cancelBooking(UUID userId, UUID tourId);
}
