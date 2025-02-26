package by.com.lifetech.billingapi.models.repository;

import by.com.lifetech.billingapi.models.entity.SimCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SimCardRepository extends JpaRepository<SimCard, String> {
    Optional<SimCard> findByImsi(String imsi);
}