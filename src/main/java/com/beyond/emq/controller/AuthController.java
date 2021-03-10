package com.beyond.emq.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 认证接口
 *
 * @author beyond233
 * @date 2021/3/9 14:46
 */
@RestController
@RequestMapping("/mqtt")
@Slf4j
public class AuthController {

    @PostMapping("/auth")
    public ResponseEntity<?> connectAuth(@RequestBody AuthReq req) {
        log.info("连接校验，user={}", req.toString());
        if ("admin".equals(req.getUsername()) && "public".equals(req.getPassword())) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<Object>("ignore", HttpStatus.UNAUTHORIZED);
    }

}
