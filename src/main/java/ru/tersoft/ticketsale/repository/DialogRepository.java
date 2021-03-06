package ru.tersoft.ticketsale.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.tersoft.ticketsale.entity.Account;
import ru.tersoft.ticketsale.entity.Dialog;

import java.util.UUID;

public interface DialogRepository extends JpaRepository<Dialog, UUID> {
    @Query("select d from Dialog d where d.answered = false and closed = false")
    Page<Dialog> findByAnswered(Pageable page);

    @Query("select d from Dialog d where d.closed = false")
    Page<Dialog> findOpenDialogs(Pageable page);

    @Query("select distinct m.dialog from Message m where m.user = ?1")
    Page<Dialog> findByUser(Account user, Pageable page);
}
