<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
</head>
<body>
<jsp:include page="${contextPath}/inc/header.jsp"></jsp:include>
<jsp:include page="${contextPath}/inc/top.jsp"></jsp:include>
<div class="container-fluid">
  <div class="row">
    <jsp:include page="${contextPath}/inc/left.jsp"></jsp:include>
        
    <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

    <div align="center"><img src="img/jboss_middleware1.jpg" width=1000 height=300>
    <form name="Login" method="post" action="login_emp.do"><br/>
    <br><br>
    <table class="table table-striped">
       <tr>
           <td></td><td></td><td></td><td></td>
    		<td>ID</td>
    		<td><input name="id"></td>
    	</tr>
    	<tr>
    	    <td></td><td></td><td></td><td></td>
    		<td>PASSWORD</td>
    		<td><input name="passwd"></td>
    	</tr>
    	<tr>
    	     <td></td><td></td><td></td><td></td><td></td>
    	    <td><input type="submit" value="로그인"></td>
    	</tr>
    	</table>   
    </form>
    </div>
    </div>
 </div>
</div>
</body>
</html>

<jsp:include page="${contextPath}/inc/footer.jsp"></jsp:include>