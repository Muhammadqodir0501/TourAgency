package org.example.touragency.service.abstractions;

import org.example.touragency.model.enity.FavouriteTour;
import org.example.touragency.model.enity.Tour;

import java.util.List;
import java.util.UUID;

public interface FavouriteTourService {
    FavouriteTour addFavouriteTour(UUID userId, UUID tourId);

    void deleteFavouriteTour(UUID userId, UUID tourId);

    List<Tour> getUserFavouriteTours(UUID userId);
}
