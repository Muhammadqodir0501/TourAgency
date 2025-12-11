package org.example.touragency.controller;

import lombok.RequiredArgsConstructor;
import org.example.touragency.model.enity.Tour;
import org.example.touragency.service.abstractions.FavouriteTourService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users/{userId}/favourites")
@RequiredArgsConstructor
public class FavouriteTourController {

    private final FavouriteTourService favouriteTourService;

    @PostMapping("/{tourId}")
    public ResponseEntity<?> addFavTour(@PathVariable UUID userId, @PathVariable UUID tourId) {
        try{
            favouriteTourService.addFavouriteTour(userId,tourId);
            return ResponseEntity.ok("Tour has been added to the Favourite list");
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{tourId}")
    public ResponseEntity<?> deleteFavouriteTour(@PathVariable UUID userId, @PathVariable UUID tourId) {
        try{
            favouriteTourService.deleteFavouriteTour(userId,tourId);
            return ResponseEntity.ok("Tour has been deleted from the Favourite list");
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<List<Tour>> getUserFavouriteTour(@PathVariable UUID userId){
       return new ResponseEntity<>(favouriteTourService.getUserFavouriteTours(userId), HttpStatus.OK);
    }
}
