/**
 *
 */
package com.tradenexus.option.aop;

import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.tradenexus.exception.NexusException;
import com.tradenexus.option.model.Response;

/**
 * This aspect intercepts all business exceptions and return a response object with error messages.
 *
 * @author Cain
 */
@Aspect
@Component
public class RestExceptionHandler {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Around("com.tradenexus.option.aop.SystemArchitecture.controllerRestMethod()")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
        try {
            return pjp.proceed();
        } catch (NexusException e) {
            String message = e.getMessage();
            logger.warning(message);
            return new Response(message, e.isInternal());
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(e.getMessage(), false);
        }
    }
}
