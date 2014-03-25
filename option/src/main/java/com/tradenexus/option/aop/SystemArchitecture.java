/**
 *
 */
package com.tradenexus.option.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * This class carries all application point cuts.
 *
 * @author Cain
 */
@Aspect
@Component
public class SystemArchitecture {
    @Pointcut("execution(* com.tradenexus.option.controller..*.*(..))")
    public void controllerRestMethod() {
    }
}
