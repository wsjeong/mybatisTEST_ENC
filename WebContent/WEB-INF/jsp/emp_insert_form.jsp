<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="${contextPath}/inc/header.jsp"></jsp:include>
<jsp:include page="${contextPath}/inc/top.jsp"></jsp:include>    
<div class="container-fluid">
      <div class="row">
        <jsp:include page="${contextPath}/inc/left.jsp"></jsp:include>
        
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
<form name="new_form" method="post" action="insert_emp.do">
<table class="table table-striped">
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
      <td>성</td>
      <td><input name="first"></td>
  </tr>
  <tr>
      <td>이름</td>
      <td><input name="last"></td>
  </tr>
  <tr>
      <td>나이</td>
      <td><input name="age"></td>
  </tr>
  <tr>
  	   <td>부서</td>
  	   <td>
  	   <select name="dept_seq">
            <option value="1">미들웨어</option>
            <option value="2">DB1</option>
            <option value="3">플랫폼</option>
        </select>
  	   </td>
  </tr>
  <tr>
      <td colspan=2>
      <input type="submit" value="입력">
      </td>                   
  </tr>

</table>
</form>
        </div>
</div>
</div>
</body>
</html>

<jsp:include page="${contextPath}/inc/footer.jsp"></jsp:include>