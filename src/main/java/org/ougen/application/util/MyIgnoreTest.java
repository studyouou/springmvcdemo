package org.ougen.application.util;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.FactoryBean;

/**
 * Author: OuGen
 * Discription:
 * Date: 15:52 2019/7/28
 */
public class MyIgnoreTest implements BeanNameAware,FactoryBean {
    @Override
    public void setBeanName(String name) {

    }

    @Override
    public Object getObject() throws Exception {
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
