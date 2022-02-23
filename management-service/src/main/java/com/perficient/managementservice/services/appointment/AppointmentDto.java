package com.perficient.managementservice.services.appointment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentDto {
    private UUID id;

    private OffsetDateTime startTime;
    private OffsetDateTime endTime;

    private String apptName;
    private String apptType;
    private String description;
    private UUID user;
}

