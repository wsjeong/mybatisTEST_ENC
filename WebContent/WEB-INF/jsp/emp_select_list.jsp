<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="com.rp.emp.EmpDto" %>
<%@ page import="com.rp.emp.EmpSvc" %>
<%@ page import="com.rp.emp.EmpController" %>
<%--
<jsp:useBean id="svc" class="com.rp.emp.EmpSvc" scope="page"/>
 --%>
<%

String search_type = request.getParameter("search_type");
String search_string = "";
if (request.getParameter("search_string") != null) {
    search_string = request.getParameter("search_string");
}

ArrayList<EmpDto> al = (ArrayList<EmpDto>)request.getAttribute("list");
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
        
    <form name="search_form" method="post" action="emplist.do">
        <select name="search_type">
            <option value="id">ID</option>
            <option value="first">성</option>
            <option value="last">이름</option>
            <option value="age">나이</option>
        </select>
        <input type="text" name="search_string" value="<%=search_string%>">
        <input type="button" value="조회" onClick="search_data();">
        <input type="hidden" name="OperationType" value="SearchList">
    </form>
   <h2 class="sub-header"> 사원목록 </h2> <br>
    <table class="table table-striped">
    <tr>
        <th width="10%">ID</th><th width="25%">First</th><th width="40%">last</th><th width="25%">age</th>
    </tr>
    <%
    //Result Set Fetch
    
    for (int i=0; i < al.size(); i++){
        al.get(i).getAge();
      %>
      <tr>
          <td><a href="emp.do?OperationType=empDetail&seq=<%=al.get(i).getSeq()%>"><%=al.get(i).getId()%></a></td>
          <td><%=al.get(i).getFirst()%></td>
          <td><%=al.get(i).getLast()%></td>
          <td><%=al.get(i).getAge()%></td>
      </tr>
    <%
    }
    
    %>
      <tr><td colspan="4"><input type="button" value="새로생성" onclick="javascript:window.document.location.href='emp.do?OperationType=EmpInsert_form'"></td></tr>
   </table>
  </div>
  </div>
  </div>
  <jsp:include page="${contextPath}/inc/footer.jsp"></jsp:include>
    </body>
    </html>

