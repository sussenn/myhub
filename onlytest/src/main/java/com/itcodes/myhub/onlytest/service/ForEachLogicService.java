package com.itcodes.myhub.onlytest.service;

import com.itcodes.myhub.onlytest.pojo.User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName ForEachLogicService
 * @Author sussen
 * @Version 1.0
 * @Date 2020/3/20
 */
@Service
public class ForEachLogicService {

    public Set<User> logicHandle(List<User> list1, List<User> list2) {
        //存放 list1有,list2没有的数据
        Set<User> addSet = new HashSet<>();
        //list1和list2比较
        if (list1 != null && list1.size() > 0) {
            //遍历list1数据
            for (User user1 : list1) {
                boolean flag = false;
                if (list2 != null && list2.size() > 0) {
                    //遍历list2数据
                    for (User user2 : list2) {
                        //如果list1数据和list2数据的id一致,跳出本次循环,忽略不作处理
                        if (user1.getId().equals(user2.getId())) {
                            flag = true;
                            break;
                        }
                    }
                    //此处的boolean和业务逻辑Boolean无关.if(ture)满足才执行
                    if (!flag) { //debug输出:flag:false -> !flag=ture
                        //将比对不一致的数据存储
                        addSet.add(user1);
                    }
                } else {
                    //list2没有数据比较,直接将list1数据放入addSet
                    addSet.add(user1);
                }
            }
        }
        //-----------------------------------------
        return addSet;
    }
}
