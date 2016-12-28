package ru.tersoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.tersoft.entity.Account;
import ru.tersoft.entity.Order;

import java.util.Date;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    @Query("select o from Order o where o.account = ?1 and o.orderdate >= ?2")
    Iterable<Order> findByAccount(Account account, Date minDate);
}
