package cn.zhiu.framework.base.api.core.exception;

import cn.zhiu.framework.base.api.core.annotation.exception.ExceptionCode;
import org.apache.commons.lang3.StringUtils;

public class ZhiuException extends RuntimeException {

    private String code;
    private String desc;



    public ZhiuException(){
        super();
    }

    public ZhiuException(Throwable cause) {
        super(cause);
    }

    public ZhiuException(String message, Throwable cause) {
        super(message, cause);
    }

    public ZhiuException(String message) {
        super(message);
    }

    public void fillCodeDesc() {
        ExceptionCode exceptionCode = this.getClass().getAnnotation(ExceptionCode.class);
        if (getCause() instanceof ZhiuException) {
            exceptionCode = getCause().getClass().getAnnotation(ExceptionCode.class);
        }
        if (exceptionCode != null) {
            this.code = exceptionCode.code();
            if (StringUtils.isNotEmpty(this.getMessage()) && exceptionCode.recoverDesc()) {
                this.desc = this.getMessage();
            } else {
                this.desc = exceptionCode.desc();
            }
        }
    }
    
}
