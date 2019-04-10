package cn.zhiu.framework.base.api.core.exception.common;

import cn.zhiu.framework.base.api.core.exception.BaseApiException;

/**
 * The type Bean mapping exception.
 *
 * @author zhuzz
 * @time 2019 /04/02 15:00:58
 */
public class BeanMappingException extends BaseApiException {

    private static final long serialVersionUID = -2934359528500883752L;

    public BeanMappingException(String message) {
        super(message);
    }

    public BeanMappingException(Throwable cause) {
        super(cause);
    }

}
