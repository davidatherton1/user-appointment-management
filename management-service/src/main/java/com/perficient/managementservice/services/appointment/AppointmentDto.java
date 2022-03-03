package com.perficient.managementservice.services.appointment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentDto {
    private UUID id;

    private Date startTime;
    private Date endTime;

    private String apptName;
    private String apptType;
    private String description;
    private UUID user;
}

