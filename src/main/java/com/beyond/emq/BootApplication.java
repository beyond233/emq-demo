package com.beyond.emq;

import com.beyond.emq.config.MqttProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * description: boot application
 *
 * @author beyond233
 * @since 2021/2/27 18:51
 */
@SpringBootApplication
@EnableConfigurationProperties({MqttProperty.class})
public class BootApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }
}
