/*
 * DeviceResolver.java
 * Copyright 2020 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.fs.springmvc.extend;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

@Component
public class DeviceResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(final MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(DeviceInfo.class);
    }

    @Override
    public Object resolveArgument(final MethodParameter methodParameter,
            final ModelAndViewContainer modelAndViewContainer,
            final NativeWebRequest nativeWebRequest,
            final WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request =
                (HttpServletRequest)  nativeWebRequest.getNativeRequest(HttpServletRequest.class);

        // 从head头中获取设备信息
        String id = request.getHeader("x-device-id");
        if (id != null) {
            DeviceInfo deviceInfo = new DeviceInfo();
            deviceInfo.setId("id");
            return deviceInfo;
        }
        return null;
    }
}
