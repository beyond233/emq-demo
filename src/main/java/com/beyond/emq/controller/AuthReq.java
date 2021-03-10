package com.beyond.emq.controller;

import lombok.Data;
import lombok.ToString;

/**
 * todo
 *
 * @author XuJie
 * @date 2021/3/9 14:50
 */
@Data
@ToString
public class AuthReq {
    private String username;
    private String password;
    private String clientid;
}
