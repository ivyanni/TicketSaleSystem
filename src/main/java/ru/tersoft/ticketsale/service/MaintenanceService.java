package ru.tersoft.ticketsale.service;

import org.springframework.http.ResponseEntity;
import ru.tersoft.ticketsale.entity.Maintenance;

import java.util.UUID;

public interface MaintenanceService {
    Maintenance get(UUID id);
    ResponseEntity<?> add(Maintenance maintenance);
    ResponseEntity<?> delete(UUID id);
}
