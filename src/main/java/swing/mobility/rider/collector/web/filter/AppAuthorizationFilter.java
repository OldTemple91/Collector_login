package swing.mobility.rider.collector.web.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import swing.mobility.rider.collector.domain.model.AppInfo;
import swing.mobility.rider.collector.domain.repository.AppInfoRepository;
import swing.mobility.rider.collector.web.exception.BizBaseException;
import swing.mobility.rider.collector.web.exception.ErrorResponse;
import swing.mobility.rider.collector.web.exception.UnAuthorizedApplicationException;
import swing.mobility.rider.collector.web.support.RiderAppInfo;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AppAuthorizationFilter extends OncePerRequestFilter {

    private static final String APP_KEY = "X-SW-APP-KEY";
    private static final String APP_VERSION = "X-SW-APP-VERSION";
    private static final String APP_NAME = "X-SW-APP-NAME";
    private static final String OS_VERSION = "X-SW-APP-OS-VERSION";
    private static final String OS_TYPE = "X-SW-APP-OS-TYPE";
    private static final String DEVICE_KEY = "X-SW-DEVICE-KEY";

    private final AppInfoRepository appInfoRepository;

    public AppAuthorizationFilter(AppInfoRepository appInfoRepository) {
        this.appInfoRepository = appInfoRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();

        if (path.startsWith("/api")) {

            String appKey = request.getHeader(APP_KEY);
            String appVersion = request.getHeader(APP_VERSION);
            String appName = request.getHeader(APP_NAME);
            String osVersion = request.getHeader(OS_VERSION);
            String osType = request.getHeader(OS_TYPE);
            String deviceKey = request.getHeader(DEVICE_KEY);

            String method = request.getMethod();
            String referer = request.getHeader("Referer");

            log.info("[APP인증필터] appKey = {}, {} {}, refer = {}", appKey, method, path, referer);
            if (Strings.isNullOrEmpty(appKey)) {
                writeTokenErrorResponse(response);
                return;
            }

            AppInfo appInfo = appInfoRepository.findById(appKey).orElse(null);
            if (appInfo == null || !"Y".equalsIgnoreCase(appInfo.getServiceYn())) {
                log.warn("[APP인증필터] 서비스 불가 앱 appKey = {}", appKey);
                writeTokenErrorResponse(response);
                return;
            }

            request.setAttribute(
                    "riderAppInfo",
                    RiderAppInfo.builder()
                            .appKey(appKey)
                            .appVersion(appVersion)
                            .appName(appName)
                            .osVersion(osVersion)
                            .osType(osType)
                            .deviceKey(deviceKey)
                            .build()
            );

            log.info("[APP인증필터][클라이언트정보] appVer={}, appName={}, osVer={}, osType={}, deviceKey={}",
                    appVersion, appName, osVersion, osType, deviceKey);
        }

        filterChain.doFilter(request, response);

    }

    private void writeTokenErrorResponse(HttpServletResponse response) throws IOException{
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(this.getErrorResponse(new UnAuthorizedApplicationException()));
    }

    private String getErrorResponse(BizBaseException ex) throws JsonProcessingException {
        ErrorResponse error = ErrorResponse.builder()
                .status(ex.getErrorCode().getStatus())
                .code(ex.getErrorCode().getCode())
                .message(ex.getErrorCode().getMessage())
                .build();

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return mapper.writeValueAsString(error);
    }
}
