package com.example.springboot_03.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @Auther: shiyunkai
 * @Date: 2019/03/21 18:52
 * @Description:
 */
@Configuration
public class MVCConfig implements WebMvcConfigurer {
    //@Override
    //public void addViewControllers(ViewControllerRegistry registry) {
    //    registry.addViewController("/intform").setViewName("form");
    //}


    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns("/**")//要拦截的请求
                .excludePathPatterns("/hello");//不拦截的请求
    }

    // 注册fastjson 作为消息转换器
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter fc = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fc.setFastJsonConfig(fastJsonConfig);
        converters.add(fc);
    }
}
