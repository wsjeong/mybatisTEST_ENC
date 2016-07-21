package com.rp.db;
import java.io.Reader;
import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
public class MyAppSqlMapConfig {
    private static final SqlMapClient sqlMapClient;
      
    static {
        try {
             // 설정파일을 읽어서 sqlMapClient 생성
            String resource = "SqlMapConfig.xml";
            Reader reader = Resources.getResourceAsReader(resource);
            sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(reader);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error initializing class. Cause:" + e);
        }
    }
     
    public static SqlMapClient getSqlMapInstance() {
          return sqlMapClient;
    }
}