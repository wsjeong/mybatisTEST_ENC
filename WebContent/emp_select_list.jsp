<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.rp.DBUtil" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script language="javascript">
    function search_data(){
        var frm=document.search_form;
        frm.submit();
    }
</script>
</head>
<body>
<%
request.setCharacterEncoding("utf-8");
String search_type = request.getParameter("search_type");
String search_string = request.getParameter("search_string");
 
//Query작성
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
//sb.append(" where id = ?                    \n"); 
System.out.println("sql=" + sb.toString());
System.out.println("search_string=" + search_string);
System.out.println("search_type=" + search_type);
  
//DB 연결
Connection conn = DBUtil.getConnection();
  
//Resultset 선언
ResultSet rs = null;
  
//PreparedStatement 선언
PreparedStatement pstmt = null;
try {
    //쿼리실행
    pstmt = conn.prepareStatement(sb.toString());
     
    if ( search_string != null) {
        // Parameter 바인딩
        pstmt.setString( 1, search_string);
    }
    rs = pstmt.executeQuery();
    %>
    <form name="search_form" method="post" action="emp_select_list.jsp">
        <select name="search_type">
            <option value="id">ID</option>
            <option value="이름">이름</option>
            <option value="성">성</option>
            <option value="나이">나이</option>
        </select>
        <input type="text" name="search_string" value="<%=search_string%>">
        <input type="button" value="조회" onClick="search_data();">
    </form>
    사원목록
    <table border="1" cellspacing="0" width="500">
    <tr>
        <th width="10%">ID</th><th width="25%">First</th><th width="40%">last</th><th width="25%">age</th>
    </tr>
    <%
    //Result Set Fetch
    while (rs.next()) {
      %>
      <tr>
          <td><a href="emp_select_detail.jsp?seq=<%=rs.getString("seq")%>"><%=rs.getString("id")%></a></td>
          <td><%=rs.getString("first")%></td>
          <td><%=rs.getString("last")%></td>
          <td><%=rs.getString("age")%></td>
      </tr>
    <%
    }
    %>
      <tr><td colspan="4"><input type="button" value="New" onclick="javascript:window.document.location.href='emp_insert_form.jsp'"></td></tr>
    </table>
    </body>
    </html>
    <%
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