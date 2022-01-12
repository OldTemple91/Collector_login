package swing.mobility.rider.collector.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import swing.mobility.rider.collector.domain.model.AppInfo;

import java.util.Optional;

public interface AppInfoRepository extends JpaRepository<AppInfo, String> {
    Optional<AppInfo> findByAppKey(String appKey);
}
