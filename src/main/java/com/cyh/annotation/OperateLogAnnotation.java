package com.cyh.annotation;

import com.cyh.enums.OperateType;
import com.cyh.enums.OperateUnit;

import java.lang.annotation.*;

/**
 * @author Pc
 */
@Documented  // 说明该注解将被包含在javadoc中
@Target({ElementType.METHOD}) // 设置该注解使用的位置
@Retention(RetentionPolicy.RUNTIME) // 设置该注解保留的时间
public @interface OperateLogAnnotation {
    /**
     * 方法描述,可使用占位符获取参数:{{tel}}
     */
    String detail() default "";

    /**
     * 日志等级:自己定，此处分为1-9
     */
    int level() default 0;

    /**
     * 操作类型(enum):主要是select,insert,update,delete
     */
    OperateType operateType() default OperateType.UNKNOWN;

    /**
     * 被操作的对象(此处使用enum):可以是任何对象，如表名(user)，或者是工具(redis)
     */
    OperateUnit operateUnit() default OperateUnit.UNKNOWN;
}
