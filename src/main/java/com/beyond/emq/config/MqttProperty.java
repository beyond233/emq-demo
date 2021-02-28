package com.beyond.emq.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * description: mqtt配置类
 *
 * @author beyond233
 * @since 2021/2/28 19:41
 */
@ConfigurationProperties(prefix = "mqtt")
@Data
public class MqttProperty {
    private String host;
    private String clientId;
    private String username;
    private String password;
    private long timeout;
    private long keepalive;
}

