package com.rp.emp;
import java.beans.PropertyVetoException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import com.rp.DBUtil;
import com.rp.LogUtil;
//import com.rp.db.MyDataSource;
import com.rp.emp.EmpDto;

public class EmpDao {  
	final static Logger logger = Logger.getLogger(EmpDao.class);
    private static EmpDao dao;
    
    public static EmpDao getInstance() {
        if (dao == null) {
            dao = new EmpDao();
            return dao;
        } else {
            return dao;
        }
    }
    
	public ArrayList<EmpDto> selectEMPlist(EmpSearchDto sdto, Connection conn) throws UnsupportedEncodingException {		
        
		 ResultSet rs = null;
        PreparedStatement pstmt = null;
         
        String search_type = sdto.getSearch_type();
        String search_string = sdto.getSearch_string();
        
        System.out.println("## search_type = " + search_type);
        System.out.println("## search_string = " + search_string);
              
         /*
        System.out.println("search_type = " + search_type);
        System.out.println("search_string = " + search_string);
        String search_type = request.getParameter("search_type");
        String search_string = "";
        if (request.getParameter("search_string") != null) {
           search_string = request.getParameter("search_string");
         }
         */

        ArrayList<EmpDto> al = new ArrayList<EmpDto>();
               
        StringBuffer sb= new StringBuffer("");
         
        sb.append(" SELECT                          \n");      
        sb.append("      seq                         ,\n");       
        sb.append("      id                         ,\n");       
        sb.append("      passwd                         ,\n");       
        sb.append("      first                      ,\n");       
        sb.append("      last                       ,\n");       
        sb.append("      age                        ,\n");
        sb.append("      emp_dept.dept_nm                        \n");
        sb.append(" FROM emp,emp_dept                        \n"); 
        sb.append(" where      1=1                  \n");
        sb.append(" and   emp.dept_seq = emp_dept.dept_seq   \n");
        
        if ( search_string != null) {
            if (search_type.equals("id")) {
                sb.append(" and id = ?                    \n"); 
            } else if (search_type.equals("first")) {
                sb.append(" and first = ?                    \n");
            } else if (search_type.equals("last")) {
                sb.append(" and last = ?                    \n");
            } else if (search_type.equals("age")) {
                sb.append(" and age = ?                    \n");
            } else if (search_type.equals("dept_nm")) {
                sb.append(" and emp_dept.dept_nm = ?                \n");
            }
        }

        // INFO 레벨로 로그출력
        logger.info("sql=" + sb.toString());
        logger.info("search_string=" + search_string);
        logger.info("search_type=" + search_type);
                 
        try {
           
            //쿼리준비
            pstmt = conn.prepareStatement(sb.toString());
                         
            if ( search_string != null) {
                // Parameter 바인딩
                pstmt.setString( 1, search_string);
            }
             
            //쿼리실행
            rs = pstmt.executeQuery();
             
            while (rs.next()){
                EmpDto dto = new EmpDto();
                dto.setSeq(rs.getInt("seq"));
                dto.setId(rs.getInt("id"));
                dto.setPasswd(rs.getString("passwd"));
                dto.setFirst(rs.getString("first"));
                dto.setLast(rs.getString("last"));
                dto.setAge(rs.getInt("age"));
                dto.setDept(rs.getString("dept_nm"));
                al.add(dto);
                 
            }             
        } catch (SQLException e){
            e.printStackTrace(System.out);
             
        } finally {
            //관련자원 닫기
            DBUtil.closeConnection(pstmt);
        }
        return al;
    }
	
	public EmpDto selectDetail(HttpServletRequest request, Connection conn) throws SQLException, IOException, PropertyVetoException {
        EmpDto dto = new EmpDto();
        String seq = request.getParameter("seq");
        
        LogUtil.LogRequestParams(request);
         
        //Resultset 선언
        ResultSet rs = null;
        //PreparedStatement 선언
        PreparedStatement pstmt = null;
         
        //Query작성
        StringBuffer sb= new StringBuffer("");
        sb.append(" SELECT                          \n");      
        sb.append("      seq                         ,\n");       
        sb.append("      id                         ,\n");       
        sb.append("      passwd                      ,\n");       
        sb.append("      first                      ,\n");       
        sb.append("      last                       ,\n");       
        sb.append("      age                        ,\n");       
        sb.append("      emp_dept.dept_nm           \n");
        sb.append(" FROM emp,emp_dept                        \n"); 
        sb.append(" where      seq = ?                  \n");
        sb.append(" and   emp.dept_seq = emp_dept.dept_seq   \n");
        
        // INFO 레벨로 로그출력
        logger.info("sql = " + sb.toString());
        logger.info("seq =" + seq);
         
        try {
            //쿼리실행
            pstmt = conn.prepareStatement(sb.toString());
             
            pstmt.setString( 1, seq);
            rs = pstmt.executeQuery();
             
            rs.next();
             
            dto.setSeq(Integer.parseInt(rs.getString("seq")));
            dto.setId(Integer.parseInt(rs.getString("id")));
            dto.setPasswd(rs.getString("passwd"));
            dto.setFirst(rs.getString("first"));
            dto.setLast(rs.getString("last"));
            dto.setAge(Integer.parseInt(rs.getString("age")));
            dto.setDept(rs.getString("dept_nm"));
             
        } catch (SQLException e){
            e.printStackTrace();
             
        } finally {
            //관련자원 닫기
            DBUtil.closeConnection(pstmt);
        }
        return dto;
    }
     
public int insertEMP(HttpServletRequest request, Connection conn) {
         int rt = 0;
          
        PreparedStatement pstmt = null;
         
        LogUtil.LogRequestParams(request);
         
        StringBuffer sb= new StringBuffer("");
         
        sb.append(" insert into emp (                  \n");      
        sb.append("      id                         ,\n");       
        sb.append("      passwd                     ,\n");       
        sb.append("      first                      ,\n");       
        sb.append("      last                       ,\n");       
        sb.append("      age                        ,\n");
        sb.append("      dept_seq                     \n");
        sb.append(" )                               \n"); 
        sb.append(" values (?,?,?,?,?,?)             \n");
           
         
        // INFO 레벨로 로그출력
        logger.info("sql = " + sb.toString());
         
        try {
                          
            //파라미터 바인딩
            pstmt = conn.prepareStatement(sb.toString());
            pstmt.setString( 1, request.getParameter("id"));
            pstmt.setString( 2, request.getParameter("passwd"));
            pstmt.setString( 3, request.getParameter("first"));
            pstmt.setString( 4, request.getParameter("last"));
            pstmt.setString( 5, request.getParameter("age"));  
            pstmt.setString( 6, request.getParameter("dept_seq"));           

            //쿼리실행
            rt = pstmt.executeUpdate();          
             
        } catch (SQLException e){
            e.printStackTrace();
             
        } finally {
            //관련자원 닫기
            DBUtil.closeConnection(pstmt);
        }
        return rt;
    }

public int EmpUpdate(HttpServletRequest request, Connection conn) throws SQLException, IOException {
	
	int rt = 0;
	LogUtil.LogRequestParams(request);
	
    int seq = Integer.parseInt(request.getParameter("seq"));
    String passwd = request.getParameter("passwd");
    String first = request.getParameter("first");
    String last = request.getParameter("last");
    int age = Integer.parseInt(request.getParameter("age"));
    int dept_seq = Integer.parseInt(request.getParameter("dept_seq"));
        
  
        //PreparedStatement 선언
        PreparedStatement pstmt = null;        
         
        StringBuffer sb= new StringBuffer("");
        
        sb.append(" update emp                  \n");      
        sb.append("      set                          \n");      
        sb.append("      passwd = ?,                     \n");   
        sb.append("      first = ?,                     \n");       
        sb.append("      last  = ?,                     \n");       
        sb.append("      age  = ?,                       \n"); 
        sb.append("      dept_seq = ?                     \n");
        sb.append(" where seq = ?                       \n"); 
               
        // INFO 레벨로 로그출력
        logger.info("sql = " + sb.toString());
        logger.info("seq = " + seq);
        logger.info("passwd = " + passwd);
        logger.info("first = " + first);
        logger.info("last = " + last);
        logger.info("age = " + age);
        logger.info("dept_seq = " + dept_seq);
      
        //파라미터 바인딩
        try {  
        pstmt = conn.prepareStatement(sb.toString());
        
        pstmt.setString( 1, passwd);
        pstmt.setString( 2, first);
        pstmt.setString( 3, last);
        pstmt.setInt( 4, age);
        pstmt.setInt( 5, dept_seq);
        pstmt.setInt( 6, seq);
                
        //쿼리실행
        //pstmt.executeUpdate();
        rt = pstmt.executeUpdate();
        
        logger.info("rt = " + rt);

		} catch (SQLException e){
				e.printStackTrace();
				
		} finally {
			//관련자원 닫기
			DBUtil.closeConnection(pstmt);
		}
		return rt;
	}

public int EmpDelete(HttpServletRequest request,Connection conn) throws SQLException, IOException {
	int rt = 0;
	
	LogUtil.LogRequestParams(request);
	
    int seq = Integer.parseInt(request.getParameter("seq"));
     
    //PreparedStatement 선언
    PreparedStatement pstmt = null;   
               
        StringBuffer sb= new StringBuffer("");
        sb.append(" delete from emp                  \n");      
        sb.append(" where seq = ?                    \n"); 
        
        // INFO 레벨로 로그출력
        logger.info("sql = " + sb.toString());
        logger.info("seq = " + seq);
         
        try { 
       //파라미터 바인딩
        pstmt = conn.prepareStatement(sb.toString());
        pstmt.setInt( 1, seq);
        
        System.out.println(sb.toString());
        System.out.println("Delete ID =" + seq);
        
        //쿼리실행
        rt = pstmt.executeUpdate();
       
} catch (SQLException e){
	e.printStackTrace();
	
} finally {
//관련자원 닫기
DBUtil.closeConnection(pstmt);
}
return rt;
}

public int  EmpLogin(HttpServletRequest request,Connection conn) throws SQLException, IOException {
	int rt = 0;
	 
	LogUtil.LogRequestParams(request);
	
	String id = request.getParameter("id");
	String passwd = request.getParameter("passwd");
     
    ///Resultset 선언
	ResultSet rs = null;
	//PreparedStatement 선언
	PreparedStatement pstmt = null;
	//Query작성
	StringBuffer sb= new StringBuffer("");

	sb.append(" SELECT                          \n");      
	sb.append("      id,passwd                    \n"); 
	sb.append(" FROM emp                        \n"); 
	sb.append(" where                           \n");
	sb.append("   id=?                          \n");
	sb.append("   and                          \n");
	sb.append("   passwd=?                   \n");
	
    // INFO 레벨로 로그출력
    logger.info("sql = " + sb.toString());
    logger.info("id = " + id);
    logger.info("passwd = " + passwd);
     

	try {
		//쿼리실행
		pstmt = conn.prepareStatement(sb.toString());
		
		if ( id != null) {
		    // Parameter 바인딩
		    pstmt.setString( 1, id);
		    pstmt.setString( 2, passwd);
		}
		rs = pstmt.executeQuery();http://127.0.0.1:8080/mvcTEST/index.html
		
		rs.last();
		
		rt = rs.getRow();
        
		logger.info("rt = " + rt);
} catch (SQLException e){
	e.printStackTrace();
	
} finally {
//관련자원 닫기
DBUtil.closeConnection(pstmt);
}
return rt;
}

}