package org.example.touragency.controller;

import lombok.RequiredArgsConstructor;
import org.example.touragency.dto.request.TourAddDto;
import org.example.touragency.dto.response.TourResponseDto;
import org.example.touragency.dto.response.TourUpdateDto;
import org.example.touragency.model.enity.Tour;
import org.example.touragency.service.abstractions.TourService;
import org.example.touragency.service.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/agencies/{agencyId}/tours")
@RequiredArgsConstructor
public class TourController {

    final private TourService tourService;

    @PostMapping()
    public ResponseEntity<ApiResponse<?>> addNewTour(@PathVariable UUID agencyId,
                                                     @RequestBody TourAddDto tourAddDto) {
            Tour createdTour = tourService.addNewTour(agencyId, tourAddDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(createdTour));

    }

    @DeleteMapping("/{tourId}")
    public ResponseEntity<ApiResponse<?>> deleteTour(@PathVariable UUID agencyId, @PathVariable UUID tourId) {
            tourService.deleteTour(agencyId,tourId);
            return ResponseEntity.ok(new ApiResponse<>("Tour has successfully been removed"));
    }

    @PutMapping("/{tourId}")
    public ResponseEntity<ApiResponse<?>> updateTour(@PathVariable UUID agencyId,
                                        @PathVariable UUID tourId,
                                        @RequestBody TourUpdateDto tourUpdateDto) {
            Tour updatedTour = tourService.updateTour(agencyId,tourId,tourUpdateDto);
            return ResponseEntity.ok(new ApiResponse<>(updatedTour));
    }

    @GetMapping()
    public ResponseEntity<ApiResponse<List<TourResponseDto>>> getAllTours() {
        List<TourResponseDto> tours = tourService.getAllTours();
        return ResponseEntity.ok(new ApiResponse<>(tours));
    }

    @GetMapping("/{agencyId}")
    public ResponseEntity<ApiResponse<List<TourResponseDto>>> getAllToursByAgency(@PathVariable UUID agencyId) {
        List<TourResponseDto> tours = tourService.getAllToursByAgencyId(agencyId);
        return ResponseEntity.ok(new ApiResponse<>(tours));
    }


}
