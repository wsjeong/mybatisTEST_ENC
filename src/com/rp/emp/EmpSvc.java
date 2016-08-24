package com.rp.emp;

import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rp.DBUtil;
import com.rp.db.MyAppSqlSession;
import com.rp.db.MyDataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

public class EmpSvc {
	final static Logger logger = Logger.getLogger(EmpSvc.class);
	
    int rt = 0;
    Object obj=null;
    
    EmpDao dao = new EmpDao();
	
	 public List<EmpDto> getEmpList(EmpSearchDto sdto) throws IOException {
	        List<EmpDto> list = null;
	        SqlSession sqlSession = null;
	        
        try {

        	sqlSession = MyAppSqlSession.getSqlSessionFactory().openSession(true);                     
       
            EmpDao dao = EmpDao.getInstance();
            list = dao.selectEMPlist(sdto, sqlSession);    
     
        } catch (Exception e) {
            logger.error("StackTrace Logger", e);
            e.printStackTrace();
             
        } finally {
        	sqlSession.close();
        }
        return list;
    }
 
   public EmpDto selectDetail(EmpDto dto) {
		  List<EmpDto> list = null;
		  EmpDto detail_dto = null;
		  SqlSession sqlSession = null;
		  
     try {

     	   sqlSession = MyAppSqlSession.getSqlSessionFactory().openSession(true);    
    	 
          EmpDao dao = EmpDao.getInstance();
          detail_dto = dao.selectDetail(dto, sqlSession);
          
          logger.info("##########  EmpSvr : Return list : " + detail_dto);
                    
          
     } catch (Exception e) {
         
         System.out.println("error : " + e);
         e.printStackTrace(System.out);
          
     } finally {
         //관련자원 닫기. ibatis와는 다르게 반드시 닫아야 함.
         sqlSession.close();
     }
     return detail_dto;
 }
     
    public Object addEmp(EmpDto dto) {
    	
    	SqlSession sqlSession = null;
    	sqlSession = MyAppSqlSession.getSqlSessionFactory().openSession(false);  
   	 	
        try {
      	 
             // Transaction 시작
        	 // sqlSession.insert();
             
            obj = dao.insertEMP(dto, sqlSession);
            
            // Transaction Commit
            sqlSession.commit();
             
        } catch (Exception e) {
            logger.error("StackTrace Logger", e);
            e.printStackTrace();
             
        } finally {
            //관련자원 닫기. ibatis와는 다르게 반드시 닫아야 함.
			sqlSession.close();
        }
        return obj;
    }
    
    
    public Object EmpUpdate(EmpDto dto) {
    	
    	SqlSession sqlSession = null;
    	sqlSession = MyAppSqlSession.getSqlSessionFactory().openSession(false);  

        try {
        	
            // Transaction 시작
           // sqlMapClient.startTransaction();
            obj = dao.EmpUpdate(dto, sqlSession);
             
            // Transaction Commit
            sqlSession.commit();
            
        } catch (Exception e) {
            logger.error("StackTrace Logger", e);
            e.printStackTrace();
             
        } finally {
            //관련자원 닫기. ibatis와는 다르게 반드시 닫아야 함.
			sqlSession.close();
        }
        return obj;
    }
    
    public Object EmpDelete(EmpDto dto) {
    	
    	SqlSession sqlSession = null;
    	sqlSession = MyAppSqlSession.getSqlSessionFactory().openSession(false);  
         
        try {
        	   // Transaction 시작
              //sqlMapClient.startTransaction();

            obj = dao.EmpDelete(dto, sqlSession);
            
            // Transaction Commit
            sqlSession.commit();
            
            logger.info("##########  EmpSvc : Return obj : " + obj);                
           
        } catch (Exception e) {
            logger.error("StackTrace Logger", e);
            e.printStackTrace();
             
        } finally {
            //관련자원 닫기. ibatis와는 다르게 반드시 닫아야 함.
			sqlSession.close();
        }
        return obj;
    }
    
    public int EmpLogin(EmpDto dto) {
		 logger.info(dto.toString()); 
		 SqlSession sqlSession = null;
         
        try {
        	  //SqlMapClient sqlMapClient = MyAppSqlMapConfig.getSqlMapInstance(); 
        	  sqlSession = MyAppSqlSession.getSqlSessionFactory().openSession(true);  
             
     		 logger.info("Login_emp.do : EmpSvc ============================");
     		          
             rt = dao.EmpLogin(dto, sqlSession);  
             
             logger.info("############ EmpSvc - return rt :" + rt);
                                     
        } catch (Exception e) {
            System.out.println("error : " + e);
            e.printStackTrace(System.out);
             
        } finally {
            //관련자원 닫기. ibatis와는 다르게 반드시 닫아야 함.
            sqlSession.close();
        }
        return rt;
    }

    
}