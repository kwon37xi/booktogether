package com.google.code.booktogether.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class AnnotLoggingAspect {

	private Logger log = Logger.getLogger(getClass());

	@Before("execution(public * com.google.code.booktogether.service.*.*(..))")
	public String beforeLogging(JoinPoint joinPoint) {

		String className = joinPoint.getTarget().getClass().getSimpleName();

		String methodName = joinPoint.getSignature().getName();

		if (log.isInfoEnabled()) {
			log.info("ClassName=" + className + ":::: calling: " + methodName);
		}

		return methodName;
	}

	/*@AfterReturning(pointcut = "execution(public * com.google.code.booktogether.service.*.*(..))", returning = "ret")
	public void returningLogging(JoinPoint joinPoint, Object ret) {

		String methodName = joinPoint.getSignature().getName();

		if (log.isInfoEnabled()) {

			log.info("called successfully: " + methodName);
		}
	}

	@AfterThrowing(pointcut = "execution(public * com.google.code.booktogether.service.*.*(..))", throwing = "ex")
	public void throwingLogging(JoinPoint joinPoint, Throwable ex) {

		String methodName = joinPoint.getSignature().getName();

		if (log.isInfoEnabled()) {
			log.info("exception occured: " + methodName + " throws "
					+ ex.getClass().getName());
		}
	}
	@After("execution(public * com.google.code.booktogether.service.*.*(..))")
	public void afterLogging(JoinPoint joinPoint) {

		String className = joinPoint.getTarget().getClass().getSimpleName();

		String methodName = joinPoint.getSignature().getName();

		if (log.isInfoEnabled()) {
			log.info("ClassName=" + className + "::::: finish call: "
					+ methodName);
		}
	}
	 */
}
