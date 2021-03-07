package com.beyond.emq;

import com.beyond.emq.config.MqttProperty;
import com.beyond.emq.mqtt.EmqClient;
import com.beyond.emq.mqtt.MqttQos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.annotation.PostConstruct;

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

    @Autowired
    private EmqClient emqClient;

    @Autowired
    private MqttProperty mqttProperty;

    @PostConstruct
    public void mqttInit() {
        emqClient.connect(mqttProperty.getUsername(), mqttProperty.getPassword());
        emqClient.subscribe("testtopic/#", MqttQos.ONLY_ONCE);
        emqClient.publish("testtopic/demo", "hello mqtt!", MqttQos.ONLY_ONCE, true);
    }
}
