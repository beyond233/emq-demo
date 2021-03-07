package com.beyond.emq.mqtt;

/**
 * 描述:  mqtt消息服务质量
 *
 * @author beyond233
 * @since 2021/3/7 22:01
 */
public enum MqttQos {
    /**
     * Qos0 "至多一次": 消息发布完全依赖底层TCP/IP网络,会发生消息丢失或重复
     * */
    AT_MOST_ONCE(0),
    /**
     * Qos1 "至少一次": 确保消息到达，但消息重复可能会发生
     * */
    AT_LEAST_ONCE(1),
    /**
     * Qos2 "只有一次": 确保消息到达一次
     * */
    ONLY_ONCE(2);

    private final int value;

    MqttQos(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
