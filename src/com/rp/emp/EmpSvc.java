package com.rp.emp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.rp.DBUtil;
import com.rp.db.MyDataSource;

import org.apache.log4j.Logger;

public class EmpSvc {
	final static Logger logger = Logger.getLogger(EmpSvc.class);
	 public ArrayList<EmpDto> getEmpList(EmpSearchDto sdto) {
	        Connection conn = null;
	        ArrayList<EmpDto> al = null;
	                           
        try {
            conn = MyDataSource.getInstance().getConnection();
             // Transaction 시작
            conn.setAutoCommit(false);
             
            EmpDao dao = new EmpDao();
            al = dao.selectEMPlist(sdto, conn);    
            
           // System.out.println("svc========================================" + al.size());
      
            // Transaction 종료
            conn.commit();
            conn.setAutoCommit(true);                        
             
        } catch (Exception e) {
            try {
                if ( conn != null) conn.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            System.out.println("error : " + e);
            e.printStackTrace(System.out);
             
        } finally {
            //관련자원 닫기
            DBUtil.closeConnection(conn);
        }
        return al;
    }
	 
	 public EmpDto selectDetail(EmpDto dto) {
	        
		 Connection conn = null;
	                           
     try {
         conn = MyDataSource.getInstance().getConnection();
          
         // Transaction 시작
         conn.setAutoCommit(false);
          
         EmpDao dao = new EmpDao();
         dto = dao.selectDetail(dto, conn);          
   
         // Transaction 종료
         conn.commit();
         conn.setAutoCommit(true);                        
          
     } catch (Exception e) {
         try {
             conn.rollback();
         } catch (SQLException e1) {
             // TODO Auto-generated catch block
             e1.printStackTrace();
         }
         System.out.println("error : " + e);
         e.printStackTrace(System.out);
          
     } finally {
         //관련자원 닫기
         DBUtil.closeConnection(conn);
     }
     return dto;
 }
     
    public int addEmp(EmpDto dto) {
        Connection conn = null;
        int rt = 0;
         
        try {
            conn = MyDataSource.getInstance().getConnection();
             
            // Transaction 시작
            conn.setAutoCommit(false);
             
            EmpDao dao = new EmpDao();
            rt = dao.insertEMP(dto, conn);
             
            // Transaction 종료
            conn.commit();
            conn.setAutoCommit(true);
                         
             
        } catch (Exception e) {
        	e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            System.out.println("error : " + e);
            e.printStackTrace(System.out);
             
        } finally {
            //관련자원 닫기
            DBUtil.closeConnection(conn);
        }
        return rt;
    }
    
    
    public int EmpUpdate(EmpDto dto) {
        Connection conn = null;
        //EmpDto dto = new EmpDto();
        int rt = 0;
         
        try {
            conn = MyDataSource.getInstance().getConnection();
             
            // Transaction 시작
            conn.setAutoCommit(false);
             
            EmpDao dao = new EmpDao();
            rt = dao.EmpUpdate(dto, conn);
             
            // Transaction 종료
            conn.commit();
            conn.setAutoCommit(true);
                                     
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            System.out.println("error : " + e);
            e.printStackTrace(System.out);
             
        } finally {
            //관련자원 닫기
            DBUtil.closeConnection(conn);
        }
        return rt;
    }
    
    public int EmpDelete(EmpDto dto) {
        Connection conn = null;
        //EmpDto dto = new EmpDto();
        int rt = 0;
         
        try {
            conn = MyDataSource.getInstance().getConnection();
             
            // Transaction 시작
            conn.setAutoCommit(false);
             
            EmpDao dao = new EmpDao();
            rt = dao.EmpDelete(dto, conn);
             
            // Transaction 종료
            conn.commit();
            conn.setAutoCommit(true);
                                     
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            System.out.println("error : " + e);
            e.printStackTrace(System.out);
             
        } finally {
            //관련자원 닫기
            DBUtil.closeConnection(conn);
        }
        return rt;
    }
    
    public int EmpLogin(EmpDto dto) {
    	int rt = 0;
    	Connection conn = null;
    	
		 logger.info(dto.toString());   	
         
        try {
            conn = MyDataSource.getInstance().getConnection();
             
            // Transaction 시작
            conn.setAutoCommit(false);
             
     		 logger.info("Login_emp.do : EmpSvc ============================");
     		
            EmpDao dao = new EmpDao();
            
            rt = dao.EmpLogin(dto, conn);
             
            // Transaction 종료
            conn.commit();
            conn.setAutoCommit(true);
                                     
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            System.out.println("error : " + e);
            e.printStackTrace(System.out);
             
        } finally {
            //관련자원 닫기
            DBUtil.closeConnection(conn);
        }
        return rt;
    }

    
}