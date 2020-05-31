/*
 * EmojiReplaceAdvice.java
 * Copyright 2020 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.fs.springmvc.extend;

import com.vdurmont.emoji.EmojiParser;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;
import sun.misc.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

@RestControllerAdvice
public class EmojiReplaceAdvice implements RequestBodyAdvice {
    @Override
    public boolean supports(final MethodParameter methodParameter, final Type targetType,
            final Class<? extends HttpMessageConverter<?>> converterType) {
        return methodParameter.hasParameterAnnotation(EmojiReplace.class);
    }

    @Override
    public Object handleEmptyBody(final Object body, final HttpInputMessage inputMessage,
            final MethodParameter parameter, final Type targetType,
            final Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @Override
    public HttpInputMessage beforeBodyRead(final HttpInputMessage inputMessage,
            final MethodParameter parameter,
            final Type targetType, final Class<? extends HttpMessageConverter<?>> converterType)
            throws IOException {
        return new HttpInputMessage() {
            @Override
            public InputStream getBody() throws IOException {
                final String content = IOUtils.toString(inputMessage.getBody());
                final String emojiUnicodeToAlias = EmojiParser.parseToAliases(content);
                return new ByteArrayInputStream(
                        emojiUnicodeToAlias.getBytes(StandardCharsets.UTF_8));
            }

            @Override
            public HttpHeaders getHeaders() {
                return inputMessage.getHeaders();
            }
        };
    }

    @Override
    public Object afterBodyRead(final Object body, final HttpInputMessage inputMessage,
            final MethodParameter parameter, final Type targetType,
            final Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }
}