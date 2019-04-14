package cn.zhiu.framework.base.api.core.exception.common;


import cn.zhiu.framework.base.api.core.annotation.exception.ExceptionCode;
import cn.zhiu.framework.base.api.core.exception.BaseApiException;

@ExceptionCode(code = "BA0001", desc = "参数错误", recoverDesc = true)
public class ParamValidException extends BaseApiException {
    private static final long serialVersionUID = -3255395453192134889L;

    public ParamValidException() {
        super();
    }

    public ParamValidException(Throwable cause) {
        super(cause);
    }

    public ParamValidException(String message) {
        super(message);
    }

    public ParamValidException(String message, Throwable cause) {
        super(message, cause);
    }
}
