<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.rp.emp.EmpDto" %>
<%

EmpDto al = (EmpDto)request.getAttribute("detail");
%>
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
           <h2 class="sub-header"> 사원 정보 수정 </h2> <br>

<form name="detail_form" method="post" action="update_emp.do">
<input type="hidden" name="seq" value="<%=al.getSeq()%>">
<table class="table table-striped">
  <tr>
     <th>구분</th><th>입력값</th>
  </tr>
  <tr>
      <td>ID</td>
      <td><input name="id" value="<%=al.getId()%>"></td>
  </tr>
  <tr>
      <td>비밀번호</td>
      <td><input name="passwd" value="<%=al.getPasswd()%>"></td>
  </tr>
  <tr>
      <td>성</td>
      <td><input name="first"  value="<%=al.getFirst()%>"></td>
  </tr>
  <tr>
      <td>이름</td>
      <td><input name="last"  value="<%=al.getLast()%>"></td>
  </tr>
  <tr>
      <td>나이</td>
      <td><input name="age"  value="<%=al.getAge()%>"></td>
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
      <td colspan=3>
          <input type="submit" value="수정">   
      </td>       
  </tr>
</table>
   </div>
</div>
</div>
<jsp:include page="${contextPath}/inc/footer.jsp"></jsp:include>
</form>
</body>
</html>
