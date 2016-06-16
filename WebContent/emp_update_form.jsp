<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%
request.setCharacterEncoding("utf-8");
String id = request.getParameter("id");

//DB 연결
//STEP 2: Register JDBC driver
Class.forName("com.mysql.jdbc.Driver");
//DB연결 - 직접연결
Connection conn = null;

//STEP 3: Open a connection
System.out.println("Connecting to database...");
conn = DriverManager.getConnection("jdbc:mysql://172.16.168.2:3306/wsjeongdb","wsjeong","rplinux");

//Query작성
StringBuffer sb= new StringBuffer("");
sb.append(" SELECT                          \n");      
sb.append("      id                         ,\n");       
sb.append("      passwd                     ,\n");       
sb.append("      first                      ,\n");       
sb.append("      last                       ,\n");       
sb.append("      age                        \n");       
sb.append(" FROM emp                        \n"); 
sb.append(" where      id = ?                  \n");
 
//Resultset 선언
ResultSet rs = null;
//PreparedStatement 선언
PreparedStatement pstmt = null;
try {
    //쿼리실행
    pstmt = conn.prepareStatement(sb.toString());
     
    pstmt.setString( 1, id);
    rs = pstmt.executeQuery();
     
    rs.first();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form name="new_form" method="post" action="emp_update_ctl.jsp">
<table border="1" cellspacing="0" width="500">
  <tr>
    <th width="10%">구분</th><th width="25%">입력값</th>
  </tr>
  <tr>
      <td>ID</td>
      <td><input name="id" value="<%=rs.getString("id")%>"></td>
  </tr>
  <tr>
      <td>Password</td>
      <td><input name="passwd" value="<%=rs.getString("passwd")%>"></td>
  </tr>
  <tr>
      <td>First Name</td>
      <td><input name="first"  value="<%=rs.getString("first")%>"></td>
  </tr>
  <tr>
      <td>Last Name</td>
      <td><input name="last"  value="<%=rs.getString("last")%>"></td>
  </tr>
  <tr>
      <td>Age</td>
      <td><input name="age"  value="<%=rs.getString("age")%>"></td>
  </tr>
  <tr>
      <td colspan=2>
          <input type="submit" value="수정">
           
      </td>
       
  </tr>
</table>
</form>
</body>
</html>
<%
} catch  (SQLException e) {
     
    e.printStackTrace(System.out);
     
} finally {
    //관련자원 닫기
        if (pstmt != null) { try { pstmt.close(); } catch  (SQLException e) { e.printStackTrace();}}
        if (conn != null) { try { conn.close(); } catch  (SQLException e) { e.printStackTrace();}}
     
}
%>