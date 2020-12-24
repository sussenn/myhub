package com.itcodes.myhub.javaagent;

import org.junit.Test;

public class AgentMainTest {

    @Test
    public void test00(){
        UserService userService = new UserService();
        userService.sayHello();
    }
}