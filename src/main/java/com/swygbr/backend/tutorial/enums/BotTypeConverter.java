package com.swygbr.backend.tutorial.enums;

import org.springframework.core.convert.converter.Converter;

public class BotTypeConverter implements Converter<String, BotType> {
    @SuppressWarnings("null")
    @Override
    public BotType convert(String source) {
        return BotType.valueOf(source.toUpperCase());
    }
}