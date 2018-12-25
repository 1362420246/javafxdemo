package com.qbk.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author: quboka
 * @Date: 2018/12/7 13:54
 * @Description:
 */
@Component
public class ContextAware implements ApplicationContextAware {

    private static ApplicationContext context ;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext ;
    }

    public static ApplicationContext getApplicationContext(){
        return context;
    }

}
