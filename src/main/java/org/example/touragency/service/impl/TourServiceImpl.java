package org.example.touragency.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.touragency.dto.request.TourAddDto;
import org.example.touragency.dto.response.TourResponseDto;
import org.example.touragency.dto.response.TourUpdateDto;
import org.example.touragency.model.Role;
import org.example.touragency.model.enity.Tour;
import org.example.touragency.model.enity.User;
import org.example.touragency.repository.FavTourRepository;
import org.example.touragency.repository.TourRepository;
import org.example.touragency.repository.UserRepository;
import org.example.touragency.service.abstractions.TourService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TourServiceImpl implements TourService {

    private final TourRepository tourRepository;
    private final UserRepository userRepository;
    private final FavTourRepository favTourRepository;


    @Override
    public Tour addNewTour(UUID agencyId, TourAddDto tourAddDto) {

        Integer nights = calculatingNights(tourAddDto.getStartDate(), tourAddDto.getReturnDate());
        User agency = userRepository.getUserById(agencyId);

        if(agency != null && agency.getRole().equals(Role.AGENCY)) {
            Tour newTour = Tour.builder()
                    .title(tourAddDto.getTitle())
                    .agencyId(agencyId)
                    /*.city(tourAddDto.getCity(city)) */
                    .hotel(tourAddDto.getHotel())
                    .description(tourAddDto.getDescription())
                    .startDate(tourAddDto.getStartDate())
                    .returnDate(tourAddDto.getReturnDate())
                    .nights(nights)
                    .price(tourAddDto.getPrice())
                    .seatsTotal(tourAddDto.getSeatsTotal())
                    .seatsAvailable(tourAddDto.getSeatsTotal())
                    .build();
            tourRepository.addTour(newTour);
            return newTour;
        }
        return null;

    }

    @Override
    public void deleteTour(UUID agencyId, UUID tourId) {

        User agency = userRepository.getUserById(agencyId);

        if(agency.getRole().equals(Role.AGENCY) && tourId != null) {
            favTourRepository.deleteAllFavToursIfTourDeleted(tourId);
            tourRepository.deleteTour(tourId);
        }
    }

    @Override
    public Tour updateTour(UUID agencyId, UUID tourId, TourUpdateDto tourUpdateDto) {
        Tour existingTour = tourRepository.getTourById(tourId);

        if(existingTour != null && existingTour.getAgencyId().equals(agencyId)) {
            Integer nights = calculatingNights(tourUpdateDto.getStartDate(), tourUpdateDto.getReturnDate());

            existingTour.setTitle(tourUpdateDto.getTitle());
            existingTour.setDescription(tourUpdateDto.getDescription());
            existingTour.setCity(tourUpdateDto.getCity());
            existingTour.setPrice(tourUpdateDto.getPrice());
            existingTour.setSeatsTotal(tourUpdateDto.getSeatsTotal());
            existingTour.setSeatsAvailable(tourUpdateDto.getSeatsTotal());
            existingTour.setStartDate(tourUpdateDto.getStartDate());
            existingTour.setReturnDate(tourUpdateDto.getReturnDate());
            existingTour.setNights(nights);
            existingTour.setHotel(tourUpdateDto.getHotel());
            return  existingTour;
        }
        return null;
    }

    @Override
    public List<TourResponseDto> getAllTours() {
        return tourRepository.getAllTours().stream()
                .map(this::toResponseDto)
                .toList();
    }

    private TourResponseDto toResponseDto(Tour tour) {
        User agency = userRepository.getUserById(tour.getAgencyId());
        return TourResponseDto.builder()
                .id(tour.getId())
                .agencyName(agency.getFullName())
                .title(tour.getTitle())
                .description(tour.getDescription())
                .nights(calculatingNights(tour.getStartDate(), tour.getReturnDate()))
                .startDate(tour.getStartDate())
                .returnDate(tour.getReturnDate())
                .price(tour.getPrice())
                .hotel(tour.getHotel())
                .city(tour.getCity())
                .seatsTotal(tour.getSeatsTotal())
                .seatsAvailable(tour.getSeatsAvailable())
                .views(tour.getViews())
                .rating(tour.getRating())
                .discountPercent(tour.getDiscountPercent())
                .build();
    }

    private Integer calculatingNights(LocalDate startDate, LocalDate returnDate) {
        if (returnDate.isBefore(startDate)) {
            throw new IllegalArgumentException("Qaytish kuni ketish kunidan vohli bo'lmasligi kerak!");
        }

        int nights = (int) ChronoUnit.DAYS.between(startDate, returnDate);

        if (nights <= 0) {
            throw new IllegalArgumentException("Tur kamida 1 kun davom etishi kerak!");
        }
        return nights;
    }

}
