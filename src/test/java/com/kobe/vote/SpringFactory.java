package com.kobe.vote;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author: kobe.wu
 * @since : 14-12-27
 */
public class SpringFactory {

    private static final String[] CONFIG_FILES = new String[]{"classpath*:config/spring/local/appcontext-app.xml"};

    private static ApplicationContext applicationContext;

    public static ApplicationContext getInstance() {
        if (applicationContext == null) {
            synchronized (SpringFactory.class) {
                applicationContext = new FileSystemXmlApplicationContext(CONFIG_FILES);
                System.out.println("########## starting initialize applicationContext");
            }
        }
        return applicationContext;
    }
}
