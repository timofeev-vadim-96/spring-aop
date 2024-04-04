package ru.gb.springaop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.slf4j.event.Level;


@Aspect
@Component
@Slf4j
public class TimerAspect {
    @Around("@within(Timer) || @annotation(Timer)")
    private Object measureExecutionTime(ProceedingJoinPoint joinPoint){
        try {
            Level level = getAnnotationParam(joinPoint);

            long start = System.currentTimeMillis();

            Object result = joinPoint.proceed();

            long elapsedTime = System.currentTimeMillis() - start;

            log.atLevel(level).log("Метод " + joinPoint.getSignature().getName() + " класса " +
                    joinPoint.getTarget().getClass().getSimpleName() + " выполнился за " + elapsedTime + " миллисекунд");

            return result;
        } catch (Throwable exception){
            throw new RuntimeException(exception);
        }
    }

    private Level getAnnotationParam(ProceedingJoinPoint proceedingJoinPoint){
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Timer timerAnnotation = methodSignature.getMethod().getAnnotation(Timer.class);

        if (timerAnnotation != null){
            return timerAnnotation.level();
        }

        return proceedingJoinPoint.getTarget().getClass().getAnnotation(Timer.class).level();
    }
}
