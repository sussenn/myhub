package com.itcodes.myhub.idcardshow;

import com.itcodes.myhub.util.IdWorker;
import com.itcodes.myhub.util.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @ClassName IdcardShowApplication
 * @Author sussen
 * @Version 1.0
 * @Data 2019/11/19
 */
@SpringBootApplication
public class IdcardShowApplication {
    public static void main(String[] args) {
        SpringApplication.run(IdcardShowApplication.class, args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(6, 9);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil();
    }
}
