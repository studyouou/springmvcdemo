package org.ougen.application.beanprocess;

import org.ougen.application.global.GlobalController;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * Author: OuGen
 * Discription:
 * Date: 12:48 2019/7/28
 */
@Component
public class OwnPostProcess implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("globalController")){
            GlobalController filmController = (GlobalController) bean;
            if (filmController==null){
                System.out.println("fimlService not init in before");
            }else {
                System.out.println("film init in before");
            }
            System.out.println("对filmController进行初始化方法前处理");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("filmController")){
            GlobalController filmController = (GlobalController) bean;
            if (filmController==null){
                System.out.println("fimlService not init in after");
            }else {
                System.out.println("film init in after");
            }
            System.out.println("对filmController进行初始化方法后处理");
        }
        return bean;
    }
}
