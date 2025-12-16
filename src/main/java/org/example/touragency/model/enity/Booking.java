package org.example.touragency.model.enity;

import lombok.*;
import org.example.touragency.model.base.BaseEntity;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking extends BaseEntity {
    private UUID userId;
    private UUID tourId;
}
