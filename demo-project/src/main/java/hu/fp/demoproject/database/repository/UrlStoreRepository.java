package hu.fp.demoproject.database.repository;

import hu.fp.demoproject.database.entity.UrlStore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlStoreRepository extends JpaRepository<UrlStore, Long> {
    Optional<UrlStore> findByOriginal(String originalUrl);
}
