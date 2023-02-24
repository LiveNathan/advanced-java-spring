package platform.codingnomads.co.aspectorientedprogramming.lab.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.codingnomads.co.aspectorientedprogramming.aop.aspect.ServiceAspect;

@Aspect
@Component
public class PrintableAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceAspect.class);

    @Pointcut(value = "execution(* platform.codingnomads.co.aspectorientedprogramming.lab.service.GreetingService.salutation())")
    private void logSalutationMethod() { }

    @After("logSalutationMethod()")
    public void logAfterAdvice(JoinPoint joinPoint) {
        LOGGER.info("AFTER Advice : " + joinPoint.getSignature().getName());
    }
}
