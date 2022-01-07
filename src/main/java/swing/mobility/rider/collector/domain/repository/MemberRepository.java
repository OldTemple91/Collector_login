package swing.mobility.rider.collector.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import swing.mobility.rider.collector.domain.model.Collector;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Collector, Long> {
    Optional<Collector> findByUserId(String userId);
    boolean existsByUserId(String userId);


}
