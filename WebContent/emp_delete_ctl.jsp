<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%
    //Post로 넘어온 Parameter에 대한 인코딩
    request.setCharacterEncoding("utf-8");
    System.out.println("seq:" + request.getParameter("seq"));
     
     
    int seq = 0;
     
     
    seq = Integer.parseInt(request.getParameter("seq"));
     
    //STEP 2: Register JDBC driver
    Class.forName("com.mysql.jdbc.Driver");
     
    //DB연결 - 직접연결
    Connection conn = null;
    //STEP 3: Open a connection
    System.out.println("Connecting to database...");
    conn = DriverManager.getConnection("jdbc:mysql://172.16.168.2:3306/wsjeongdb","wsjeong","rplinux");
     
    //입력건수
    int rt;
     
    //PreparedStatement 선언
    PreparedStatement pstmt = null;
try {           
          
         
        StringBuffer sb= new StringBuffer("");
        sb.append(" delete from emp                  \n");      
        sb.append(" where seq = ?                               \n"); 
         
        System.out.println(sb.toString());
         
        //파라미터 바인딩
        pstmt = conn.prepareStatement(sb.toString());
        pstmt.setInt( 1, seq);
         
         
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
    if (pstmt != null) { try { pstmt.close(); } catch  (SQLException e) { e.printStackTrace();}}
    if (conn != null) { try { conn.close(); } catch  (SQLException e) { e.printStackTrace();}}
     
}
%>