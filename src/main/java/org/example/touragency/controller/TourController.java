package org.example.touragency.controller;

import lombok.RequiredArgsConstructor;
import org.example.touragency.dto.request.TourAddDto;
import org.example.touragency.dto.response.TourResponseDto;
import org.example.touragency.dto.response.TourUpdateDto;
import org.example.touragency.service.abstractions.TourService;
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
    public ResponseEntity<?> addNewTour(@PathVariable UUID agencyId, @RequestBody TourAddDto tourAddDto) {
        try{
            tourService.addNewTour(agencyId, tourAddDto);
            return ResponseEntity.ok("Tour has successfully been added");
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{tourId}")
    public ResponseEntity<?> deleteTour(@PathVariable UUID agencyId, @PathVariable UUID tourId) {
        try{
            tourService.deleteTour(agencyId,tourId);
            return ResponseEntity.ok("Tour has successfully been removed");
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{tourId}")
    public ResponseEntity<?> updateTour(@PathVariable UUID agencyId,
                                        @PathVariable UUID tourId,
                                        @RequestBody TourUpdateDto tourUpdateDto) {
        try{
            tourService.updateTour(agencyId,tourId,tourUpdateDto);
            return ResponseEntity.ok("Tour has successfully been updated");
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<List<TourResponseDto>> getAllTours() {
        return new ResponseEntity<>(tourService.getAllTours(), HttpStatus.OK);
    }


}
