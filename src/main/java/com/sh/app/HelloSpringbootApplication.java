package com.sh.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 
 * @SpringBootConfiguration @Configuration설정을 모아서 등록 
 * @EnableAutoConfiguration ApplicationContext 생성 및 설정
 * @ComponentScan 현재클래스가 속한 패키지 기준으로 @Component 클래스를 빈을 등록
 *
 * @EnableAspectJAutoProxy 
 * - @Aspect 등 aop관련 어노테이션등록, Proxy객체생성해 cross-cut처리
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class HelloSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringbootApplication.class, args);
	}

}
