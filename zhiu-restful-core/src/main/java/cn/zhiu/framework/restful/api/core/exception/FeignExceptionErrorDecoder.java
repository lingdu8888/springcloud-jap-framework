package cn.zhiu.framework.restful.api.core.exception;

import cn.zhiu.framework.base.api.core.exception.BaseApiException;
import cn.zhiu.framework.base.api.core.exception.RestfulApiException;
import com.alibaba.fastjson.JSON;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Configuration;

/**
 * The type Feign exception error decoder.
 *
 * @author zhuzz
 * @time 2019 /04/14 18:37:06
 */
@Configuration
public class FeignExceptionErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {

        try {
            String[] apiMethod = s.split("#");
            String service = apiMethod[0];
            String method = apiMethod[1];
            if (response.body() != null) {
                String body = Util.toString(response.body().asReader());
                ExceptionInfo exceptionInfo = JSON.parseObject(body, ExceptionInfo.class);
                String message = exceptionInfo.getMessage();
                BaseApiException exception = JSON.parseObject(message, BaseApiException.class);
                exception.setInterfaceName(service);
                exception.setMethodName(method);

                return exception;
            }
        } catch (Exception e) {
            return new RestfulApiException(e.getMessage());
        }
        return new RestfulApiException();
    }

}

