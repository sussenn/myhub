package com.itcodes.myhub.idcardshow.controller;

import com.itcodes.myhub.idcardshow.pojo.dto.PageResult;
import com.itcodes.myhub.idcardshow.pojo.dto.Result;
import com.itcodes.myhub.idcardshow.pojo.dto.StatusCode;
import com.itcodes.myhub.idcardshow.pojo.entity.Useres;
import com.itcodes.myhub.idcardshow.service.UseresService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @ClassName UseresController  用户信息查询
 * @Author sussen
 * @Version 1.0
 * @Data 2019/8/23
 */
@CrossOrigin
@RestController
@RequestMapping("/idcard")
@Slf4j
public class UseresController {

    @Autowired
    private UseresService useresService;

    /**
     * 分页 条件查询用户信息
     * @param searMap
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/finduseresinfo/{page}/{size}")
     public Result findUseresInfo(@RequestBody Map<String,String> searMap,
                                  @PathVariable(value = "page") Integer page,
                                  @PathVariable(value = "size") Integer size,
                                  HttpServletRequest request){

        if (request.getAttribute("admin_claims") == null) {
            return new Result(false, StatusCode.ACCESSERROR, "Token失效,请重新登录");
        }
        //获得JPA的分页对象 Page<T>
        Page<Useres> pageList = useresService.findUseresInfo(page,size,searMap);
        if (pageList != null) {
            //再封装成自定义的分页对象 PageResult<T>
            PageResult<Useres> pageResult = new PageResult<>(pageList.getTotalElements(), pageList.getContent());
            log.info("【查询用户】：" + pageResult.getRows());
            return new Result(true,StatusCode.OK,"查询成功",pageResult);
        } else {
            return new Result(false, StatusCode.ERROR, "暂无数据");
        }
    }
}
