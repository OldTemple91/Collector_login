package swing.mobility.rider.collector.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swing.mobility.rider.collector.domain.model.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByKey(String key);

}
