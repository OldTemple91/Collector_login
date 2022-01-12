package swing.mobility.rider.collector.web.support;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RiderAppInfo {

    String appKey;
    String appVersion;
    String appName;
    String osVersion;
    String osType;
    String deviceKey;

}
