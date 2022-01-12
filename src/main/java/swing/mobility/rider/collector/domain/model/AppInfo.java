package swing.mobility.rider.collector.domain.model;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "app_info")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "appKey")
public class AppInfo {

    @Id
    @Column(name = "app_key", nullable = false)
    String appKey;

    @Column(name = "secret_key", nullable = false)
    String secretKey;

    @Column(name = "app_name", nullable = false)
    String appName;

    @Column(name = "app_version", nullable = false)
    String appVersion;

    @Column(name = "service_yn", nullable = false)
    String serviceYn;
}
