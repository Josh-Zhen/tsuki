package com.moonlit.common.network;

import com.moonlit.common.exception.BusinessException;
import com.moonlit.common.exception.enums.CommonErrorEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * HttpServlet工具类
 *
 * @author Joshua
 * @version 1.0
 * @date 21/3/2023 0:19
 * @email by.Moonlit@hotmail.com
 */
public class HttpServletUtils {

    /**
     * 获取当前请求的request对象
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            throw new BusinessException(CommonErrorEnum.OBJECT_EMPTY);
        } else {
            return requestAttributes.getRequest();
        }
    }

    /**
     * 获取当前请求的response对象
     */
    public static HttpServletResponse getResponse() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            throw new BusinessException(CommonErrorEnum.OBJECT_EMPTY);
        } else {
            return requestAttributes.getResponse();
        }
    }

}
