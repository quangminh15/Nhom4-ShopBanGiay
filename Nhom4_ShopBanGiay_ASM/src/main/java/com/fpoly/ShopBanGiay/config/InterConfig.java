package com.fpoly.ShopBanGiay.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fpoly.ShopBanGiay.interceptor.LoggerInterceptor;
import com.fpoly.ShopBanGiay.interceptor.SecurityInterceptor;


@Configuration
public class InterConfig implements WebMvcConfigurer{

	@Autowired
	LoggerInterceptor interceptor;
	
	@Autowired
	SecurityInterceptor auth;
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(interceptor)
//			.addPathPatterns("/**")
//			.excludePathPatterns("/assets/**");
		
		
		registry.addInterceptor(auth)
			.addPathPatterns("/admin/**", "/doimatkhau/**","/giohang", "/yeuthich", "/addyeuthich/{masp}")
			.excludePathPatterns("/static","/dangnhap", "/dangky","trangchu");
		
	}
}