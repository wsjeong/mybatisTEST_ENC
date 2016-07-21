package com.rp.emp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rp.DBUtil;
import com.rp.db.MyAppSqlMapConfig;
import com.rp.db.MyDataSource;

import org.apache.log4j.Logger;

public class EmpSvc {
	final static Logger logger = Logger.getLogger(EmpSvc.class);
	
    int rt = 0;
    Object obj=null;
    
    EmpDao dao = new EmpDao();
	
	 public ArrayList<EmpDto> getEmpList(EmpSearchDto sdto) throws IOException {
	        ArrayList<EmpDto> al = null;
	        
        try {

            SqlMapClient sqlMapClient = MyAppSqlMapConfig.getSqlMapInstance();           
             
       
            EmpDao dao = EmpDao.getInstance();
            al = dao.selectEMPlist(sdto, sqlMapClient);    
     
        } catch (Exception e) {
            logger.error("StackTrace Logger", e);
            e.printStackTrace();
             
        } finally {
          
        }
        return al;
    }
 
   public EmpDto selectDetail(EmpDto dto) {
		  List<EmpDto> list = null;
		  EmpDto detail_dto = null;
     try {

    	  SqlMapClient sqlMapClient = MyAppSqlMapConfig.getSqlMapInstance();   
    	 
          EmpDao dao = EmpDao.getInstance();
          detail_dto = dao.selectDetail(dto, sqlMapClient);
          
          logger.info("##########  EmpSvr : Return list : " + list);
                    
          
     } catch (Exception e) {
         
         System.out.println("error : " + e);
         e.printStackTrace(System.out);
          
     } finally {

     }
     return detail_dto;
 }
     
    public Object addEmp(EmpDto dto) {
    	
   	 	SqlMapClient sqlMapClient = MyAppSqlMapConfig.getSqlMapInstance();
   	 	
        try {
      	 
             // Transaction 시작
             sqlMapClient.startTransaction();
             
            obj = dao.insertEMP(dto, sqlMapClient);
            
            // Transaction Commit
            sqlMapClient.commitTransaction();
             
        } catch (Exception e) {
            logger.error("StackTrace Logger", e);
            e.printStackTrace();
             
        } finally {
            try {
                // Transaction End
                sqlMapClient.endTransaction();
                
            } catch (SQLException e) {
                logger.error("StackTrace Logger", e);
                e.printStackTrace();
            }
        }
        return obj;
    }
    
    
    public Object EmpUpdate(EmpDto dto) {
    	
    	SqlMapClient sqlMapClient = MyAppSqlMapConfig.getSqlMapInstance();

        try {
        	
            // Transaction 시작
            sqlMapClient.startTransaction();
             
            EmpDao dao = new EmpDao();
            obj = dao.EmpUpdate(dto, sqlMapClient);
             
            // Transaction Commit
            sqlMapClient.commitTransaction();
            
        } catch (Exception e) {
            logger.error("StackTrace Logger", e);
            e.printStackTrace();
             
        } finally {
            try {
                // Transaction End
                sqlMapClient.endTransaction();
                
            } catch (SQLException e) {
                logger.error("StackTrace Logger", e);
                e.printStackTrace();
            }
        }
        return obj;
    }
    
    public Object EmpDelete(EmpDto dto) {
    	
    	SqlMapClient sqlMapClient = MyAppSqlMapConfig.getSqlMapInstance();
         
        try {
        	   // Transaction 시작
            sqlMapClient.startTransaction();
             
            EmpDao dao = new EmpDao();
            obj = dao.EmpDelete(dto, sqlMapClient);
            
            // Transaction Commit
            sqlMapClient.commitTransaction();
            
            logger.info("##########  EmpSvc : Return obj : " + obj);                
           
        } catch (Exception e) {
            logger.error("StackTrace Logger", e);
            e.printStackTrace();
             
        } finally {
            try {
                // Transaction End
                sqlMapClient.endTransaction();
                
            } catch (SQLException e) {
                logger.error("StackTrace Logger", e);
                e.printStackTrace();
            }
        }
        return obj;
    }
    
    public int EmpLogin(EmpDto dto) {
		 logger.info(dto.toString()); 
         
        try {
        	  SqlMapClient sqlMapClient = MyAppSqlMapConfig.getSqlMapInstance();  
             
     		 logger.info("Login_emp.do : EmpSvc ============================");
     		
            EmpDao dao = new EmpDao();
            
             rt = dao.EmpLogin(dto, sqlMapClient);  
             logger.info("############ EmpSvc - return rt :" + rt);
                                     
        } catch (Exception e) {
            System.out.println("error : " + e);
            e.printStackTrace(System.out);
             
        } finally {
            //관련자원 닫기
        }
        return rt;
    }

    
}