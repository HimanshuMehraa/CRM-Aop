package com.hm.spring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {

	//setup logger
	private Logger myLogger= Logger.getLogger(getClass().getName());
	
	//setup pointcutDeclaration
	@Pointcut("execution(* com.hm.spring.controller.*.*(..))")
	private void forControllerPackage() {}
	
	@Pointcut("execution(* com.hm.spring.service.*.*(..))")
	private void forServicePackage() {}
	
	@Pointcut("execution(* com.hm.spring.dao.*.*(..))")
	private void forDaoPackage() {}
	
	@Pointcut("forControllerPackage()||forServicePackage()||forDaoPackage()")
	private void forAppFlow() {}
	//add @Before advice
	
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint) {
		
		String theMethod= theJoinPoint.getSignature().toShortString();
		myLogger.info("...=====>>>>in @Before calling method" +theMethod);
		
		//get the args
		Object[] args= theJoinPoint.getArgs();
		
		//loop through and get args
		for(Object temp: args)
			myLogger.info("..----->>> Argument-> " +temp);
		
	}
	//add @AfterReturning advice
	
	@AfterReturning(pointcut= "forAppFlow()", returning= "theResult")
	public void after(JoinPoint theJoinPoint, Object theResult) {
		
		String theMethod= theJoinPoint.getSignature().toShortString();
		myLogger.info("...=====>>>>in @After calling method" +theMethod);
		
		myLogger.info("===>>"+theResult);
		
	}
	
}
