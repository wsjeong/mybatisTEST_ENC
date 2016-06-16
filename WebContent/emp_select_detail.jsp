<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.rp.DBUtil" %>
<%@ page import="com.rp.emp.EmpBean" %>
<%@ page import="com.rp.emp.EmpDto" %>
<%
request.setCharacterEncoding("utf-8");
String seq = request.getParameter("seq");

//Resultset 선언
EmpDto al = null;
EmpBean bean = new EmpBean();
al = bean.selectDetail(request);
     
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script language="javascript">
    function modify(id){
        alert(id);
        document.location.href="emp_update_form.jsp?id=" + id;
    }
     
    function delData(seq){
       document.location.href="emp_delete_ctl.jsp?seq=" + seq;
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
      <td><%=al.getId()%></td>
  </tr>
  <tr>
      <td>비밀번호</td>
      <td><%=al.getPasswd()%></td>
  </tr>
  <tr>
      <td>First Name</td>
      <td><%=al.getFirst()%></td>
  </tr>
  <tr>
      <td>Last Name</td>
      <td><%=al.getLast()%></td>
  </tr>
  <tr>
      <td>Age</td>
      <td><%=al.getAge()%></td>
  </tr>
  <tr>
      <td colspan=2>
        <input type="button" value="수정" onClick="javascript:modify('<%=al.getId()%>');">
        <input type="button" value="삭제" onClick="javascript:delData('<%=al.getId()%>');">
      </td>
       
  </tr>
</table>
</form>
</body>
</html>
