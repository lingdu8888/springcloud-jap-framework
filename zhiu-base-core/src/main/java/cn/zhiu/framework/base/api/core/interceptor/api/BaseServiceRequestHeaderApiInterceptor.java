package cn.zhiu.framework.base.api.core.interceptor.api;

import cn.zhiu.framework.base.api.core.exception.common.ClientTypeNotSupportException;
import cn.zhiu.framework.base.api.core.exception.common.ParamValidException;
import cn.zhiu.framework.base.api.core.util.MD5Util;
import cn.zhiu.framework.bean.core.enums.ClientType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static cn.zhiu.framework.base.api.core.constant.ClientAuthConstant.CLIENT_AUTH_SALT;
import static cn.zhiu.framework.base.api.core.constant.HeaderNameConstant.*;

@Component
public class BaseServiceRequestHeaderApiInterceptor extends BaseHandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String clientType = request.getHeader(API_CLIENT_TYPE);
        if (StringUtils.isBlank(clientType)) {
            throw new ClientTypeNotSupportException();
        }
        ClientType type = ClientType.valueOf(clientType);
        String signature = request.getHeader(API_CLIENT_SIGNATURE);
        String token = request.getHeader(API_CLIENT_TOKEN);
        if (StringUtils.isBlank(signature) || StringUtils.isBlank(token)) {
            throw new ParamValidException();
        }
        String md5Result = MD5Util.encoderByMd5(type.toString() + CLIENT_AUTH_SALT + signature);

        if (!md5Result.equals(token)) {
            throw new ParamValidException();
        }

        return super.preHandle(request, response, handler);
    }
}
