package com.itcodes.myhub.idcardshow.service;

import com.itcodes.myhub.idcardshow.dao.UseresDao;
import com.itcodes.myhub.idcardshow.pojo.entity.Useres;
import com.itcodes.myhub.util.IdWorker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName UseresService 用户信息查询业务层
 * @Author sussen
 * @Version 1.0
 * @Data 2019/8/23
 */
@Service
@Slf4j
public class UseresService {

    @Autowired
    private UseresDao useresDao;

    @Autowired
    private StringRedisTemplate redisTemplate;      //使用StringRedisTemplate，解决缓存K/V乱码(or:启动类@Bean重写注册)

    @Autowired
    private IdWorker idWorker;

    public Page<Useres> findUseresInfo(Integer page, Integer size, Map<String, String> searMap) {

        //将page和size封装成pageable
        Pageable pageable = PageRequest.of(page - 1, size);
        //将searMap构建成specification
        Specification<Useres> specification = creSpecification(searMap);

        Page<Useres> allList = useresDao.findAll(specification, pageable);

        //缓存看看效果(分页条件查询无法使用缓存)
        long uid = idWorker.nextId();
        redisTemplate.opsForValue().set("useres-" + String.valueOf(uid), String.valueOf(allList),1L, TimeUnit.DAYS);
        log.info("用户[{}]缓存成功",uid);
        return allList;
    }

    private Specification<Useres> creSpecification(Map<String, String> searMap) {
        return (Specification<Useres>) (root, criteriaQuery, cb) -> {
            //参数1 root:根对象, 获得实体类里面的属性
            //参数2 query:查询对象, 定义了查询(一般不用)
            //参数3 cb: 条件对象. 内部封装了条件的方法
            List<Predicate> predicateList = new ArrayList<>();
            //封装"姓名"条件
            if (searMap.get("name") != null && !"".equals(searMap.get("name"))){
                predicateList.add(cb.equal(root.get("name"),searMap.get("name")));
            }
            if (searMap.get("phone") != null && !"".equals(searMap.get("phone"))){
                predicateList.add(cb.equal(root.get("phone"),searMap.get("phone")));
            }
            if (searMap.get("idcard") != null && !"".equals(searMap.get("idcard"))){
                predicateList.add(cb.equal(root.get("idcard"),searMap.get("idcard")));
            }
            //把predicateList转成 Predicate[]
            Predicate[] predicates = predicateList.toArray(new Predicate[predicateList.size()]);
            //将搜索条件searMap拼接
            return cb.and(predicates);
        };
    }
}
