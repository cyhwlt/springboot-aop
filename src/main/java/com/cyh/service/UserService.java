package com.cyh.service;

import com.cyh.annotation.OperateLogAnnotation;
import com.cyh.enums.OperateType;
import com.cyh.enums.OperateUnit;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @OperateLogAnnotation(detail = "通过手机号[{{tel}}]获取用户名",level = 3,
            operateUnit = OperateUnit.USER,operateType = OperateType.SELECT)
    public String findUserName(String tel) {
        System.out.println("tel:" + tel);
        return "zhangsan";
    }
}
