package com.asl.pms.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.asl.pms.mymodel.User;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AuditConfig {
    @Bean
    public AuditorAware<User> auditorAware() {
        AuditorAwareImpl auditorAware = new AuditorAwareImpl();
        return auditorAware;
    }
}
