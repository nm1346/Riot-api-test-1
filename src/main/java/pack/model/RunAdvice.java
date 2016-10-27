package pack.model;

import java.util.Scanner;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RunAdvice {
	
	@Autowired
	RiotApiKeyRotate key;
	
	@Around("execution(public * Api*(..))")
	public Object trace(ProceedingJoinPoint joinPoint) throws Throwable{
		key.next();
		Object object=joinPoint.proceed();
		return object;
	}
}
