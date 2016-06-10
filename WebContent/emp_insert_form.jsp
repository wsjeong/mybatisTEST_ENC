<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form name="new_form" method="post" action="emp_insert_ctl.jsp">
<table border="1" cellspacing="0" width="500">
  <tr>
    <th width="10%">구분</th><th width="25%">입력값</th>
  </tr>
  <tr>
      <td>ID</td>
      <td><input name="id"></td>
  </tr>
  <tr>
      <td>비밀번호</td>
      <td><input name="passwd"></td>
  </tr>
  <tr>
      <td>이름</td>
      <td><input name="first"></td>
  </tr>
  <tr>
      <td>성</td>
      <td><input name="last"></td>
  </tr>
  <tr>
      <td>나이</td>
      <td><input name="age"></td>
  </tr>
  <tr>
      <td colspan=2><input type="submit"></td>
       
  </tr>
</table>
</form>
</body>
</html>