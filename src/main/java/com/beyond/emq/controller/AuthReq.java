package com.beyond.emq.controller;

import lombok.Data;

/**
 * mqtt客户端连接emq broker的校验请求类
 *
 * @author XuJie
 * @date 2021/3/9 14:50
 */
@Data
public class AuthReq {
    private String username;
    private String password;
    private String clientid;
}
