package com.example.annotationManager;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example.annotationManager")
public class AppConfig {
}
