package dev.jefersoncardoso.sampleoauthcode.infra.http.feign;

import feign.RequestInterceptor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

@Configuration
public class FeignConfig {
    private static final Log logger = LogFactory.getLog(FeignConfig.class);
    @Bean
    public RequestInterceptor requestInterceptor() {
        return template -> {
            // Log headers
            template.headers().forEach((key, values) -> values.forEach(value -> logger.error("Header: " + key + " Value: " + value)));

            // Log body
            if (template.body() != null) {
                logger.error("Body: " + new String(template.body(), StandardCharsets.UTF_8));
            }
        };
    }
}