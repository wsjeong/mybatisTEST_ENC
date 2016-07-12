package com.rp.emp;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import com.rp.DBUtil;
import com.rp.db.MyDataSource;

public class EmpSvc {
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
	 
	 public EmpDto selectDetail(HttpServletRequest request) {
	        Connection conn = null;
	        EmpDto dto = new EmpDto();
	                           
     try {
         conn = MyDataSource.getInstance().getConnection();
          
         // Transaction 시작
         conn.setAutoCommit(false);
          
         EmpDao dao = new EmpDao();
         dto = dao.selectDetail(request, conn);          
   
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
     
    public int addEmp(HttpServletRequest request) {
        Connection conn = null;
        int rt = 0;
         
        try {
            conn = MyDataSource.getInstance().getConnection();
             
            // Transaction 시작
            conn.setAutoCommit(false);
             
            EmpDao dao = new EmpDao();
            rt = dao.insertEMP(request, conn);
             
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
    
    
    public int EmpUpdate(HttpServletRequest request) {
        Connection conn = null;
        //EmpDto dto = new EmpDto();
        int rt = 0;
         
        try {
            conn = MyDataSource.getInstance().getConnection();
             
            // Transaction 시작
            conn.setAutoCommit(false);
             
            EmpDao dao = new EmpDao();
            rt = dao.EmpUpdate(request, conn);
             
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
    
    public int EmpDelete(HttpServletRequest request) {
        Connection conn = null;
        //EmpDto dto = new EmpDto();
        int rt = 0;
         
        try {
            conn = MyDataSource.getInstance().getConnection();
             
            // Transaction 시작
            conn.setAutoCommit(false);
             
            EmpDao dao = new EmpDao();
            rt = dao.EmpDelete(request, conn);
             
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
    public int EmpLogin(HttpServletRequest request) {
    	int rt = 0;
    	
    	Connection conn = null;
         
        try {
            conn = MyDataSource.getInstance().getConnection();
             
            // Transaction 시작
            conn.setAutoCommit(false);
             
            EmpDao dao = new EmpDao();
            rt = dao.EmpLogin(request, conn);
             
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