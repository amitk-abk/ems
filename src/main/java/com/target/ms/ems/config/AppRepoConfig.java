package com.target.ms.ems.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.target.ms.ems.repository")
public class AppRepoConfig {

}
