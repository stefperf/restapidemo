package com.example.restapidemo;

import org.springframework.boot.context.properties.ConfigurationProperties;

// settings exposed by the API, but not really used - created just as an exercise
@ConfigurationProperties(prefix = "restapidemo.somesillyconfiguration")
public record SomeSillyConfiguration(String setting1, String setting2) {
}
