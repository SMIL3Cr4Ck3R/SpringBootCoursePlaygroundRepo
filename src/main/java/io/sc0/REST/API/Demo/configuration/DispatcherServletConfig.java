package io.sc0.REST.API.Demo.configuration;

import io.sc0.REST.API.Demo.service.LoggingService;
import io.sc0.REST.API.Demo.utils.logging.CustomDispatcherServlet;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletPath;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DispatcherServletConfig {

    private final LoggingService loggingService;

    public DispatcherServletConfig(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    @Bean(name = "dispatcherServlet")
    public CustomDispatcherServlet dispatcherServlet() {
        return new CustomDispatcherServlet(loggingService);
    }

    @Bean
    public ServletRegistrationBean<?> dispatcherServletRegistration() {
        ServletRegistrationBean<?> registration = new ServletRegistrationBean<>(dispatcherServlet());
        registration.setName("dispatcherServlet");
        registration.addUrlMappings("/"); // Ensure it maps to root by default
        return registration;
    }

    @Bean
    public DispatcherServletPath dispatcherServletPath() {
        return () -> "/";
    }
}
