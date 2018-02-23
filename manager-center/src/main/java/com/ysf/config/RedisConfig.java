package com.ysf.config;

import java.io.Serializable;
import java.lang.reflect.Method;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {
	@Value("${redis.host}")
	private String host;
	@Value("${redis.port}")
	private Integer port;
	@Value("${redis.password}")
	private String password;

	@Bean
	public RedisCacheManager cacheManager(RedisTemplate<?, ?> redisTemplate) {
		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
		return cacheManager;
	}

	@Bean
	public JedisConnectionFactory redisConnectionFactory() {
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		jedisConnectionFactory.setHostName(host);
		jedisConnectionFactory.setPort(port);
		jedisConnectionFactory.setPassword(password);
		return jedisConnectionFactory;
	}
	
	@Bean
	public RedisTemplate<Serializable,Serializable> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
		RedisTemplate<Serializable, Serializable> redisTemplate = new RedisTemplate<>();
		/*key序列化方式;（不然会出现乱码;）但是如果方法上有Long等非String类型的话，会报类型转换错误；
		 * 所以在没有自己定义key生成策略的时候，以下这个代码建议不要这么写，可以不配置或者自己实现 ObjectRedisSerializer
		 * 或者JdkSerializationRedisSerializer序列化方式;*/
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
		redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
		//以上4条配置可以不用
		redisTemplate.setConnectionFactory(jedisConnectionFactory);
		return redisTemplate;
	}

	/**
	 * key生成策略
	 * 类名 + 方法名 + 参数 
	 */
	@Override
	public KeyGenerator keyGenerator() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object o, Method method, Object... objects) {
				StringBuilder sb = new StringBuilder();
				sb.append(o.getClass().getName());
				sb.append(method.getName());
				for (Object obj : objects) {
					sb.append(obj.toString());
				}
				return sb.toString();
			}
		};
	}
}
