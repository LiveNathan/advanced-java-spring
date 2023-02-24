package platform.codingnomads.co.aspectorientedprogramming.lab.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import platform.codingnomads.co.aspectorientedprogramming.aop.aspect.ServiceAspect;

@Aspect
@Component
public class GreetingServiceAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceAspect.class);

    @Before("execution(* platform.codingnomads.co.aspectorientedprogramming.lab.service.GreetingService.greeting())")
    public void logSaveStudentMethodBefore(JoinPoint joinPoint) {
        LOGGER.info("BEFORE the execution of : " + joinPoint.getSignature());
    }

    @After("execution(* platform.codingnomads.co.aspectorientedprogramming.lab.service.GreetingService.greeting())")
    public void logSaveStudentMethodAfter(JoinPoint joinPoint) {
        LOGGER.info("AFTER the execution of : " + joinPoint.getSignature());
    }

    @Pointcut(value = "execution(* platform.codingnomads.co.aspectorientedprogramming.lab.service.GreetingService.salutation())")
    private void logSalutationMethod() { }

    @Before("logSalutationMethod()")
    public void logBeforeAdvice(JoinPoint joinPoint) {
        LOGGER.info("Before Advice : " + joinPoint.getSignature().getName());
    }

}
