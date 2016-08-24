<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.rp.emp.EmpDto" %>

<%--
<jsp:useBean id="svc" class="com.rp.emp.EmpSvc" scope="page"/>
 --%>
<%

String search_type = request.getParameter("search_type");
String search_string = "";
if (request.getParameter("search_string") != null) {
    search_string = request.getParameter("search_string");
}

List<EmpDto> list = (List<EmpDto>)request.getAttribute("list");
%>

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
<jsp:include page="${contextPath}/inc/header.jsp"></jsp:include>
<jsp:include page="${contextPath}/inc/top.jsp"></jsp:include>
<div class="container-fluid">
      <div class="row">
        <jsp:include page="${contextPath}/inc/left.jsp"></jsp:include>
        
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        
    <form name="search_form" method="post" action="select_emp_list.do">
        <select name="search_type">
            <option value="id">ID</option>
            <option value="first">성</option>
            <option value="last">이름</option>
            <option value="age">나이</option>
            <option value="dept_nm">부서</option>
        </select>
        <input type="text" name="search_string" value="<%=search_string%>">
        <input type="button" value="조회" onClick="search_data();">
        <input type="hidden" name="OperationType" value="SearchList">
    </form>
   <h2 class="sub-header"> 사원목록 </h2> <br>
    <table class="table table-striped">
    <tr>
    <!-- 
        <th width="10%">ID</th><th width="25%">First</th><th width="40%">last</th><th width="25%">age</th><th width="25%">부서</th>
         -->
         <th>ID</th><th>성</th><th>이름</th><th>나이</th><th>부서</th>
    </tr>
    <%
    //Result Set Fetch
    for (int i=0; i < list.size(); i++){
        list.get(i).getAge();
      %>
      <tr>
          <td><a href="select_emp_detail.do?seq=<%=list.get(i).getSeq()%>"><%=list.get(i).getId()%></a></td>
          <td><%=list.get(i).getFirst()%></td>
          <td><%=list.get(i).getLast()%></td>
          <td><%=list.get(i).getAge()%></td>
          <td><%=list.get(i).getDeptNM()%></td>
      </tr>
    <%
    }
    
    %>
      <tr><td colspan="5"><input type="button" value="새로생성" onclick="javascript:window.document.location.href='insert_emp_form.do'"></td></tr>
   </table>
  </div>
  </div>
  </div>
  <jsp:include page="${contextPath}/inc/footer.jsp"></jsp:include>
 </body>
</html>

