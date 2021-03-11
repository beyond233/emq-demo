package com.beyond.emq.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * description: mqtt配置类
 *
 * @author beyond233
 * @since 2021/2/28 19:41
 */
@ConfigurationProperties(prefix = "mqtt")
@Configuration
@Data
public class MqttProperty {

    /**
     * mqtt broker服务器地址
     * */
    private String serverAddr;
    /**
     * mqtt客户端id
     * */
    private String clientId;
    /**
     * 用户名
     * */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 超时时间(单位：秒)
     */
    private int timeout;
    /**
     * 存活时间(单位：秒)
     */
    private long keepalive;
    /**
     * 断开连接时是否清除会话信息，对应的会话时效： true-临时会话;false-持久会话
     */
    private boolean cleanSession = true;
}

