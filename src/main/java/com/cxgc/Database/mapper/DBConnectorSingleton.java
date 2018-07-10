package com.cxgc.Database.mapper;

/**
 * Created by HeRui on 2018/3/10.
 * to initialize the database
 */

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class DBConnectorSingleton {

    private static volatile DBConnectorSingleton instance;

    private SqlSessionFactory sessionFactory;

    /**
     * a interface for others who want to call the instance of sessionFactory
     * return a sessionFactory instance
     */
    public static DBConnectorSingleton getInstance() {

        DBConnectorSingleton localInstance = instance;

        if (localInstance == null) {

            synchronized (DBConnectorSingleton.class) {
                localInstance = instance;
                if (localInstance == null) {
                    try {
                        instance = localInstance = new DBConnectorSingleton();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return instance;
    }
    /**
     * to create a sessionFactory which is the core of mybatis,which is for safety
     * return a sessionFactory
     */
    private DBConnectorSingleton() throws IOException {

        String resource = "mybatis-config.xml";

        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public SqlSessionFactory getSessionFactory() {
        return sessionFactory;
    }
}