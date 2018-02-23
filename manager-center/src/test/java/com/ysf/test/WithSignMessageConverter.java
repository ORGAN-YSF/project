package com.ysf.test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import com.ysf.common.enums.ExceptionEnum;
import com.ysf.common.excecption.BusinessException;
import com.ysf.config.properties.JwtProperties;
import com.ysf.util.Base64Util;
import com.ysf.util.HttpUtil;
import com.ysf.util.JwtTokenUtil;
import com.ysf.util.MD5Util;

/**
 * 实现签名校验，并可以把base64加密的信息转化为实体
 * @author sunwenxing
 */
public class WithSignMessageConverter extends FastJsonHttpMessageConverter4 {
	@Autowired
	private JwtProperties jwtProperties;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	public Object read(Type type,Class<?> contextClass,HttpInputMessage inputMessage) 
			throws IOException, HttpMessageNotReadableException {
		InputStream in = inputMessage.getBody();
		Object o = JSON.parseObject(in, super.getFastJsonConfig().getCharset(), BaseTransferEntity.class, super.getFastJsonConfig().getFeatures());
		
		BaseTransferEntity entity = (BaseTransferEntity) o;
		//校验签名
        String token = HttpUtil.getRequest().getHeader(jwtProperties.getHeader());
        String md5KeyFromToken = jwtTokenUtil.getMd5KeyFromToken(token);

        String object = entity.getObject();
        String json = Base64Util.decode(object);
        String encrypt = MD5Util.encrypt(object + md5KeyFromToken);

        if (encrypt.equals(entity.getSign())) {
            System.out.println("签名校验成功!");
        } else {
            System.out.println("签名校验失败,数据被改动过!");
            throw new BusinessException(ExceptionEnum.SIGN_ERROR.getCode().toString(),ExceptionEnum.SIGN_ERROR.getMessage());
        }

        //校验签名后再转化成应该的对象
        return JSON.parseObject(json, type);
	}
}
