package com.binfen.admin.common.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Title: Bean工厂，保存了spring加载的applicationContext<br>
 * Description: <br>
 * Company: www.360buy.com <br>
 *
 * @author 刘洪敏
 */
public class BeanFactoryUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * @return the context
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * @param applicationContext
     *            the context to set
     */
    public  void setApplicationContext(ApplicationContext applicationContext) {
        BeanFactoryUtil.applicationContext = applicationContext;
    }

    /**
     * 得到bean
     *
     * @param name
     * @return
     */
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }
}
