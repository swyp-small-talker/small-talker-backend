package com.swygbr.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.swygbr.backend.tutorial.enums.BotTypeConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @SuppressWarnings("null")
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new BotTypeConverter());
    }
}
