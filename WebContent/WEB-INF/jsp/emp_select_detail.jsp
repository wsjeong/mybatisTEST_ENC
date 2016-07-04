<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.rp.emp.EmpDto" %>
<%

EmpDto al = (EmpDto)request.getAttribute("detail");
     
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/htmle4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Detail title here</title>
<script language="javascript">
    function modify(seq){
        alert(seq);
        document.location.href="empModify.do?OperationType=EmpUpdate_form&seq=" + seq;
    }
    function delData(seq){
       document.location.href="empDelete.do?OperationType=EmpDelete&seq=" + seq;
    }
</script>
</head>
<body>
<jsp:include page="${contextPath}/inc/header.jsp"></jsp:include>
<jsp:include page="${contextPath}/inc/top.jsp"></jsp:include>
    <div class="container-fluid">
      <div class="row">
        <jsp:include page="${contextPath}/inc/left.jsp"></jsp:include>
        
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
<form name="detail_form" method="get" action="emp.do">
<table class="table table-striped">
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
      <td>성</td>
      <td><%=al.getFirst()%></td>
  </tr>
  <tr>
      <td>이름</td>
      <td><%=al.getLast()%></td>
  </tr>
  <tr>
      <td>나이</td>
      <td><%=al.getAge()%></td>
  </tr>
    <tr>
      <td>부서</td>
      <td><%=al.getDept()%></td>
  </tr>
  <tr>
      <td colspan=2>
        <input type="button" value="수정" onClick="javascript:modify('<%=al.getSeq()%>');">
        <input type="button" value="삭제" onClick="javascript:delData('<%=al.getSeq()%>');">
      </td>
       
  </tr>
   </div>
</div>
</div>
<jsp:include page="${contextPath}/inc/footer.jsp"></jsp:include>
</table>
</form>
</body>
</html>

