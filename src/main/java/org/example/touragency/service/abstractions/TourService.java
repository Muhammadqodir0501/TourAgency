package org.example.touragency.service.abstractions;

import org.example.touragency.dto.request.TourAddDto;
import org.example.touragency.dto.response.TourResponseDto;
import org.example.touragency.dto.response.TourUpdateDto;
import org.example.touragency.model.enity.Tour;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TourService {
    Tour addNewTour(UUID agencyId, TourAddDto tourAddDto);

    void deleteTour(UUID agencyId, UUID tourId);

    Tour updateTour(UUID agencyId, UUID tourId, TourUpdateDto tourUpdateDto);

    List<TourResponseDto> getAllTours();
}
