package ru.otus.hw.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.val;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class HttpInterceptor implements HandlerInterceptor {

    private static final String MDC_EXEC_TIME = "EXEC_TIME";

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request,
                                @NonNull HttpServletResponse response,
                                @NonNull Object handler,
                                Exception ex) {

        val startTime = (Long) request.getAttribute(RequestInterceptor.START_TIME_ATTRIBUTE_NAME);
        MDC.put(MDC_EXEC_TIME, String.valueOf((System.currentTimeMillis() - startTime)));

        log.info("<<< HTTP {} {} {}", request.getMethod(), request.getRequestURI(), response.getStatus());
        if (ex != null) {
            log.error("Unhandled error: ", ex);
        }
    }
}
