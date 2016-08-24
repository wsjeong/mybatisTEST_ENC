package com.rp.db;
import java.io.InputStream;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
public class MyAppSqlSession {
    private static final SqlSessionFactory sqlSessionFactory;
      
    static {
        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml"); 
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream); 
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error initializing class. Cause:" + e);
        }
    }
     
    public static SqlSessionFactory getSqlSessionFactory() {
          return sqlSessionFactory;
    }
}