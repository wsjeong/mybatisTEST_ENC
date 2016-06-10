<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.rp.DBUtil" %>
<%
request.setCharacterEncoding("utf-8");
String seq = request.getParameter("seq");
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
  
// DB연결
Connection conn = DBUtil.getConnection();
//Resultset 선언
ResultSet rs = null;
//PreparedStatement 선언
PreparedStatement pstmt = null;
try {
    //쿼리실행
    pstmt = conn.prepareStatement(sb.toString());
     
    pstmt.setString( 1, seq);
    rs = pstmt.executeQuery();
     
    rs.first();
     
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script language="javascript">
    function modify(seq){
        alert(id);
        document.location.href="emp_update_form.jsp?seq=" + seq;
    }
     
    function delData(seq){
         
        document.location.href="emp_delete_ctl.jsp?seq="+seq;
    }
</script>
</head>
<body>
<form name="detail_form" method="get" action="emp_insert_ctl.jsp">
<table border="1" cellspacing="0" width="500">
  <tr>
    <th width="10%">구분</th><th width="25%">입력값</th>
  </tr>
  <tr>
      <td>ID</td>
      <td><%=rs.getString("id")%></td>
  </tr>
  <tr>
      <td>비밀번호</td>
      <td><%=rs.getString("passwd")%></td>
  </tr>
  <tr>
      <td>First Name</td>
      <td><%=rs.getString("first")%></td>
  </tr>
  <tr>
      <td>Last Name</td>
      <td><%=rs.getString("last")%></td>
  </tr>
  <tr>
      <td>Age</td>
      <td><%=rs.getString("age")%></td>
  </tr>
  <tr>
      <td colspan=2>
        <input type="button" value="수정" onClick="javascript:modify('<%=rs.getString("seq")%>');">
        <input type="button" value="삭제" onClick="javascript:delData('<%=rs.getString("id")%>');">
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
    DBUtil.closeConnection(conn, pstmt);
     
}
%>