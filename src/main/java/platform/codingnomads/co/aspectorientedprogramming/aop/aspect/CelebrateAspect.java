package platform.codingnomads.co.aspectorientedprogramming.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CelebrateAspect {

    @After("@annotation(Celebrate)")
    public void logSaveStudentMethod(JoinPoint joinPoint) {
        System.out.println("You just saved a student!!");
    }
}
