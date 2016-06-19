package com.rp.emp;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
 
import com.rp.DBUtil;
import com.rp.db.DataSource;
public class EmpBean {
    public static void main(String[] args){
        /*EmpDto dto = new EmpDto();
        HttpServletRequest request = mock(HttpServletRequest.class); 
        when(request.getParameter("seq")).thenReturn("1");
         
        EmpBean bean = new EmpBean();
        dto = bean.selectDetail(request);
        System.out.println(dto.toString());*/
        
    }
    public ArrayList<EmpDto> selectEmpList(HttpServletRequest request) throws SQLException, IOException, PropertyVetoException {
        ResultSet rs = null;
        PreparedStatement pstmt = null;
         
        String search_type = request.getParameter("search_type");
        String search_string = request.getParameter("search_string");
        ArrayList<EmpDto> al = new ArrayList<EmpDto>();
         
        StringBuffer sb= new StringBuffer("");
         
        sb.append(" SELECT                          \n");      
        sb.append("      seq                         ,\n");       
        sb.append("      id                         ,\n");       
        sb.append("      passwd                         ,\n");       
        sb.append("      first                      ,\n");       
        sb.append("      last                       ,\n");       
        sb.append("      age                        \n");       
        sb.append(" FROM emp                        \n"); 
        sb.append(" where      1=1                  \n");
         
        if ( search_string != null) {
            if (search_type.equals("id")) {
                sb.append(" and id = ?                    \n"); 
            } else if (search_type.equals("first")) {
                sb.append(" and first = ?                    \n");
            } else if (search_type.equals("last")) {
                sb.append(" and last = ?                    \n");
            } else if (search_type.equals("age")) {
                sb.append(" and age = ?                    \n");
            }
        }
         
        System.out.println("sql=" + sb.toString());
        System.out.println("search_string=" + search_string);
        System.out.println("search_type=" + search_type);
         
         
         
        try {
            Connection conn;
            conn = DBUtil.getConnection();
             
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
                al.add(dto);
                 
            }
             
             
        } catch (SQLException e){
            e.printStackTrace();
             
        } finally {
            //관련자원 닫기
            DBUtil.closeConnection(pstmt);
            //DBUtil.closeConnection(pstmt);            
             
        }
        return al;
    }
     
    public EmpDto selectDetail(HttpServletRequest request) throws SQLException, IOException, PropertyVetoException {
        EmpDto dto = new EmpDto();
        String seq = request.getParameter("seq");
         
        Connection conn;
        conn = DataSource.getInstance().getConnection();
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
        sb.append("      age                        \n");       
        sb.append(" FROM emp                        \n"); 
        sb.append(" where      seq = ?                  \n");
 
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
             
        } catch  (SQLException e) {
             
            e.printStackTrace(System.out);
             
        } finally {
            //관련자원 닫기
            DBUtil.closeConnection(conn, pstmt);
        }
        return dto;
    }
    
    public EmpDto EmpInsert(HttpServletRequest request) throws SQLException, IOException, PropertyVetoException {
        EmpDto dto = new EmpDto();
         
        int id = Integer.parseInt(request.getParameter("id"));
        String passwd = request.getParameter("passwd");
        int age = Integer.parseInt(request.getParameter("age"));
        String first = request.getParameter("first");
        String last = request.getParameter("last");
        
        int rt;
        
        //DB 연결
        Connection conn = DBUtil.getConnection();
         
        //PreparedStatement 선언
        PreparedStatement pstmt = null;
    try {           
           
            StringBuffer sb= new StringBuffer("");
            sb.append(" insert into emp (                  \n");      
            sb.append("      id                         ,\n");       
            sb.append("      passwd                         ,\n");       
            sb.append("      first                      ,\n");       
            sb.append("      last                       ,\n");       
            sb.append("      age                        \n");       
            sb.append(" )                               \n"); 
            sb.append(" values (?,?,?,?,?)                   \n");
            //sb.append(" values (" + id + ",'" + first + "','" + last + "'," + age +") \n");
            System.out.println(sb.toString());
             
            //파라미터 바인딩
            pstmt = conn.prepareStatement(sb.toString());
            pstmt.setInt( 1, id);
            pstmt.setString( 2, passwd);
            pstmt.setString( 3, first);
            pstmt.setString( 4, last);
            pstmt.setInt( 5, age);
            
            //쿼리실행
            rt = pstmt.executeUpdate();
            
            //if (rt > 0 ){
            	
           // }
            
            //커밋
            //conn.commit();
             
    } catch (Exception e) {
        //에러인 경우 Rollback
        //conn.rollback();
        System.out.println("error : " + e);
        e.printStackTrace(System.out);
         
    } finally {
        //관련자원 닫기
        //DB 연결
        DBUtil.closeConnection(conn, pstmt);     
    }
    return dto;
  }

public EmpDto EmpUpdate(HttpServletRequest request) throws SQLException, IOException, PropertyVetoException {
    EmpDto dto = new EmpDto();
     
  //System.out.println("seq:" + request.getParameter("seq"));
    System.out.println("id:" + request.getParameter("id"));
    System.out.println("password:" + request.getParameter("passwd"));
    System.out.println("first name:" + request.getParameter("first"));
    System.out.println("last name:" + request.getParameter("last"));
    System.out.println("age:" + request.getParameter("age"));
     
    //int seq = 0;
     
    //seq = Integer.parseInt(request.getParameter("seq"));
    int id = Integer.parseInt(request.getParameter("id"));
    String passwd = request.getParameter("passwd");
    String first = request.getParameter("first");
    String last = request.getParameter("last");
    int age = Integer.parseInt(request.getParameter("age"));
   
    //입력건수
    int rt;
    
    //DB 연결
    Connection conn = DBUtil.getConnection();
     
    //PreparedStatement 선언
    PreparedStatement pstmt = null;
    
try {           
         
        StringBuffer sb= new StringBuffer("");
        sb.append(" update emp                  \n");      
        sb.append("      set                          \n");       
        sb.append("      first = ?,                     \n");       
        sb.append("      last  = ?,                     \n");       
        sb.append("      age  = ?                       \n");       
        sb.append(" where id = ?                       \n"); 
               
        System.out.println(sb.toString());
        System.out.println("id =" + id);
        System.out.println("passwd =" + passwd);
        System.out.println("first =" + first);
        System.out.println("last =" + last);
        System.out.println("age =" + age);
      
        //파라미터 바인딩
        pstmt = conn.prepareStatement(sb.toString());
        pstmt.setString( 1, first);
        pstmt.setString( 2, last);
        pstmt.setInt( 3, age);
        pstmt.setInt( 4, id);
         
         
        //쿼리실행
        rt = pstmt.executeUpdate();
        
         
} catch (Exception e) {
    //에러인 경우 Rollback
    //conn.rollback();
    System.out.println("error : " + e);
    e.printStackTrace(System.out);
     
} finally {
    //관련자원 닫기
    //DB 연결
    DBUtil.closeConnection(conn, pstmt);     
}
return dto;
}
public EmpDto EmpDelete(HttpServletRequest request) throws SQLException, IOException, PropertyVetoException {
    EmpDto dto = new EmpDto();
     
    System.out.println("seq:" + request.getParameter("seq"));
          
    int seq = Integer.parseInt(request.getParameter("seq"));
     
    //DB 연결
    Connection conn = DBUtil.getConnection();
     
    //입력건수
    int rt;
     
    //PreparedStatement 선언
    PreparedStatement pstmt = null;
try {           
               
        StringBuffer sb= new StringBuffer("");
        sb.append(" delete from emp                  \n");      
        sb.append(" where seq = ?                    \n"); 
         
       //파라미터 바인딩
        pstmt = conn.prepareStatement(sb.toString());
        pstmt.setInt( 1, seq);
        
        System.out.println(sb.toString());
        System.out.println("Delete ID =" + seq);
        
        //쿼리실행
        rt = pstmt.executeUpdate();
        
         
} catch (Exception e) {
    //에러인 경우 Rollback
    //conn.rollback();
    System.out.println("error : " + e);
    e.printStackTrace(System.out);
     
} finally {
    //관련자원 닫기
    //DB 연결
    DBUtil.closeConnection(conn, pstmt);     
}
return dto;
}
}  
    
    
    
    