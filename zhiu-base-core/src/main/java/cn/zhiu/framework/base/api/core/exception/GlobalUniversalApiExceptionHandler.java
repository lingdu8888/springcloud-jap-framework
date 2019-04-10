package cn.zhiu.framework.base.api.core.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.thoughtworks.xstream.core.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The type Global universal api exception handler.
 *
 * @author zhuzz
 * @time 2019 /04/03 15:16:24
 */
@ControllerAdvice
public class GlobalUniversalApiExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(GlobalUniversalApiExceptionHandler.class);

    private static StringBuilder stringBufferFormat = new StringBuilder();

    private ObjectMapper objectMapper = new ObjectMapper();

    static {
        //组织日志内容
        stringBufferFormat
                .append("接口调用出现异常")
                .append("\n\nERROR")
                .append("\n*******************************")
                .append("\n[Model]")
                .append("\n%s")
                .append("\n[Service]")
                .append("\n%s")
                .append("\n[Method]")
                .append("\n%s")
                .append("\n[ErrorCode]")
                .append("\n%s")
                .append("\n[ErrorMessage]")
                .append("\n%s")
                .append("\n[RequestParams]")
                .append("\n%s")
                .append("\n[ErrorDetail]")
                .append("\n%s")
                .append("\n[ExceptionStackTrace]")
                .append("\n%s")
                .append("\nERROR END")
                .append("\n*******************************\n");
    }

    private static String MODEL = "PU";

//    @ExceptionHandler(value = BaseException.class)
////    @ResponseBody
//    public void baseErrorHandler(BaseException e) throws Exception {
//        logger.error(e.getMessage());
//    }

    @ExceptionHandler(value = Exception.class)
//    @ResponseBody
    public void exceptionErrorHandler(Exception e) throws Exception {
        logger.error(e.getMessage());
    }

}
