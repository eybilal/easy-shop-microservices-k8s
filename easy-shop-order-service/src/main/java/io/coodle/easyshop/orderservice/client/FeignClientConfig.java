package io.coodle.easyshop.orderservice.client;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 	Do not annotate this class with @Configuration annotation,
 * 	otherwise this configuration will become global i.e. all FeignClients will inherit this config in that case.
 */
public class FeignClientConfig {
    private static final String AUTHORIZATION_HEADER = "Authorization";

    public static String getBearerTokenHeader() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization");
    }

//    public interface RequestInterceptor {
//
//        /**
//         * Called for every request. Add data using methods on the supplied RequestTemplate.
//         */
//        void apply(RequestTemplate template);
//    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            if (RequestContextHolder.getRequestAttributes() != null) {
                System.out.println("********************** idToken **************** " +getBearerTokenHeader());

                requestTemplate.header(AUTHORIZATION_HEADER, getBearerTokenHeader());
            }
        };
    }
}
