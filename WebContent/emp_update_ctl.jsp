<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.rp.DBUtil" %>
<%
    //Post로 넘어온 Parameter에 대한 인코딩
    request.setCharacterEncoding("utf-8");
    //System.out.println("seq:" + request.getParameter("seq"));
    System.out.println("id:" + request.getParameter("id"));
    System.out.println("password:" + request.getParameter("passwd"));
    System.out.println("first name:" + request.getParameter("first"));
    System.out.println("last name:" + request.getParameter("last"));
    System.out.println("age:" + request.getParameter("age"));
     
    //int seq = 0;
    int id = 0;
    int age = 0;
    String passwd = "";
    String first = "";
    String last = "";
     
    //seq = Integer.parseInt(request.getParameter("seq"));
    id = Integer.parseInt(request.getParameter("id"));
    passwd = request.getParameter("passwd");
    first = request.getParameter("first");
    last = request.getParameter("last");
    age = Integer.parseInt(request.getParameter("age"));
   
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
        
        if (rt > 0 ){
            %>
            <script language="javascript">
                        alert("저장되었습니다.");
                        window.document.location.href="emp_select_list.jsp";
            </script>
            <%
        }
         
         
        //커밋
        //conn.commit();
         
} catch (Exception e) {
    //에러인 경우 Rollback
    //conn.rollback();
    System.out.println("error : " + e);
    e.printStackTrace(System.out);
     
} finally {
    //관련자원 닫기
    DBUtil.closeConnection(conn, pstmt);        
}
%>