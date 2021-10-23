package com.douzone.mysite.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.TYPE }) // 어노테이션을 어디에 쓸 수 있는지를 설정해줌. 메소드에만 쓸 수 있게 하는지 어떤지..
@Retention(RetentionPolicy.RUNTIME) // 존속 기간을 설정해둠.
public @interface Auth {
	public String value() default "USER";

	public String role() default "USER";
	// public boolean test(); //이런 경우 ex) @Auth(test=false) 같은 식으로 사용할 수 있음.
	
}
