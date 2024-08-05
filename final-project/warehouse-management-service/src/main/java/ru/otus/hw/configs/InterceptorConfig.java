package ru.otus.hw.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import ru.otus.hw.interceptors.RequestInterceptor;
import ru.otus.hw.interceptors.HttpInterceptor;


@Component
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {
    private final RequestInterceptor requestInterceptor;

    private final HttpInterceptor httpInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestInterceptor)
                .order(1)
                .excludePathPatterns("/metrics", "/swagger-ui/**", "/v3/**", "/health");

        registry.addInterceptor(httpInterceptor)
                .order(2)
                .excludePathPatterns("/metrics", "/swagger-ui/**", "/v3/**", "/health");

    }
}
