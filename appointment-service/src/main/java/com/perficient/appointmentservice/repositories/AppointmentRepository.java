package com.perficient.appointmentservice.repositories;

import com.perficient.appointmentservice.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

}
