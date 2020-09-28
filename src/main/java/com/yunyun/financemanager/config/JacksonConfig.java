package com.yunyun.financemanager.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

@JsonComponent
@SuppressWarnings({"unused", "RedundantSuppression"})
public class JacksonConfig {

    public static class CustomLocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
        @Override
        public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            long epochMilli = value.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            gen.writeNumber(epochMilli);
        }
    }

    public static class CustomLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            long longValue = p.getLongValue();
            return Instant.ofEpochMilli(longValue).atZone(ZoneId.systemDefault()).toLocalDateTime();
        }
    }

    public static class CustomLocalDateSerializer extends JsonSerializer<LocalDate> {
        @Override
        public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            long epochMilli = value.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
            gen.writeNumber(epochMilli);
        }
    }

    public static class CustomLocalDateDeserializer extends JsonDeserializer<LocalDate> {
        @Override
        public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            long longValue = p.getLongValue();
            return Instant.ofEpochMilli(longValue).atZone(ZoneId.systemDefault()).toLocalDate();
        }
    }

}