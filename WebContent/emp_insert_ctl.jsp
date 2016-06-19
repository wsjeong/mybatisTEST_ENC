<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.rp.DBUtil" %>
<%@ page import="com.rp.emp.EmpBean" %>
<%@ page import="com.rp.emp.EmpDto" %>
<%
    //Post로 넘어온 Parameter에 대한 인코딩
    request.setCharacterEncoding("utf-8");

    EmpDto al = null;
    EmpBean bean = new EmpBean();
    al = bean.EmpInsert(request);

       %>
            <script language="javascript">
                        alert("저장되었습니다.");
                        window.document.location.href="emp_select_list.jsp";
            </script>

   