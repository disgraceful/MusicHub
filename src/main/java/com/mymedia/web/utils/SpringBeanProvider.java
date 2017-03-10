package com.mymedia.web.utils;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringBeanProvider implements ApplicationContextAware {
	
	private static ApplicationContext appContext;
	
	@Override
	public void setApplicationContext(ApplicationContext context) {
		appContext = context; // NOSONAR
	}
	
    /**
     * Get a Spring bean by type.
     **/
    public static <T> T getBean(Class<T> beanClass) {
        return appContext == null ? null : appContext.getBean(beanClass);
    }
    
    /**
     * Get a Spring bean by name.
     **/
    public static Object getBean(String beanName) {
        return appContext == null ? null : appContext.getBean(beanName);
    }
    
    public static DataSource getDataSource(){
    	Map<String, DataSource> map = appContext == null ? null : appContext.getBeansOfType(DataSource.class);
    	return map == null || map.isEmpty() ? null : map.entrySet().iterator().next().getValue();
    }

}
