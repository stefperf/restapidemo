package com.example.restapidemo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "restapidemo.db.users")
public record DbUsersConfig(String dbname, String username, String password) {
}
