package org.example.touragency.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class TourResponseDto {
    private UUID id;
    private String agencyName;
    private UUID agencyId;
    private String title;
    private String description;
    private Integer nights;
    private LocalDate startDate;
    private LocalDate returnDate;
    private BigDecimal price;
    private BigDecimal priceWithDiscount;
    private String hotel;
    private String city;
    private Integer seatsTotal;
    private Integer seatsAvailable;
    private Long views;
    private Float rating;
    private Integer discountPercent;
}
