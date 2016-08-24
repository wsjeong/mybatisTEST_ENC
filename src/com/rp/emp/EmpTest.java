package com.rp.emp;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.ibatis.common.logging.LogFactory;
import com.ibatis.common.resources.Resources;

public class EmpTest {
    final Logger logger = Logger.getLogger(EmpTest.class);
	//final Logger logger = LoggerFactory.getLogger(EmpTest.class);
    @Test
    public void testSqlSession() {
        String resource = "mybatis-config.xml";
        logger.info("mybatis:");
        SqlSession sqlSession = null;
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            logger.info("mybatis:");
            EmpSearchDto search_dto = new EmpSearchDto();
            search_dto.setSearch_type("id");
            search_dto.setSearch_string("1");
             
            sqlSession = sqlSessionFactory.openSession(true);
             
            List<EmpDto> list =  sqlSession.selectList("com.rp.emp.EmpDao.selectEmpList", search_dto);
            logger.info("list:{}");
            //logger.info("list:{}", list.toString());
             
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         
    }
}