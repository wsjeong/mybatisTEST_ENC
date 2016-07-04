package com.rp.emp;
import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.rp.DBUtil;
import com.rp.LogUtil;
/**
 * Servlet implementation class ControllerServlet
 */
//@WebServlet("/ControllerServlet")
public class EmpController extends HttpServlet {
	final static Logger logger = Logger.getLogger(EmpController.class);
    private static final long serialVersionUID = 1L;       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpController() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stubimport java.sql.ResultSet;
        doPost(request, response);  
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
         
		 response.setCharacterEncoding("UTF-8");
		 response.setContentType("text/html; charset=UTF-8");
		 PrintWriter out = response.getWriter();
		 
        //response.getWriter().append("Served at: ").append(request.getContextPath());
        HttpSession session = request.getSession();
        
        logger.info("controller ############################################"); 
      //Operation Type
        String OperationType = request.getParameter("OperationType");
        
        if (session.getAttribute("id") == null &&  !"emplogin".equals(OperationType) )
        {
        	RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
        	System.out.println("senssion null!!!!!!!");
            
        	rd.forward(request, response);
        	//response.sendRedirect("emplogin.do");
        } else {
        	System.out.println("senssion not null!!!!!!!");
                
        int rt =0;
        
        if (OperationType == null) {
        	
            logger.info("OperationType = " + OperationType);
        	
        	  EmpSvc svc = new EmpSvc();
            request.setAttribute("list",(ArrayList<EmpDto>)svc.getEmpList(request));
            RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/emp_select_list.jsp");
            
            rd.forward(request, response);
            
        } else if (OperationType.equals("SearchList")) {
        	 logger.info("OperationType = " + OperationType);
        	 
        	  EmpSvc svc = new EmpSvc();
            request.setAttribute("list",(ArrayList<EmpDto>)svc.getEmpList(request));
            RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/emp_select_list.jsp");
             
            rd.forward(request, response);
            
        } else if (OperationType.equals("empDetail")) {
        	  logger.info("OperationType = " + OperationType);
        	
        	  EmpSvc svc = new EmpSvc();
            request.setAttribute("detail",(EmpDto)svc.selectDetail(request));    	   
            RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/emp_select_detail.jsp");
           
            rd.forward(request, response);
            
         } else if (OperationType.equals("EmpInsert_form")) {
        	 logger.info("OperationType = " + OperationType);
        	 
           RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/emp_insert_form.jsp");
           rd.forward(request, response);
        
        } else if (OperationType.equals("EmpInsert")) {
       	 logger.info("OperationType = " + OperationType);
       	 
        	 EmpSvc svc = new EmpSvc();
        	 rt = svc.addEmp(request);
        	 
        	 if ( rt > 0){
        		// request.setAttribute("list",(ArrayList<EmpDto>)svc.getEmpList(request));
        		// RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/emp_select_list.jsp");
        		 response.sendRedirect("emplist.do");
        	 } 	else {       		 
        		// PrintWriter out = response.getWriter();
        		 
        		 out.println("<script language='javascript'>");
        		 out.println("alert('정상적으로 처리 되지 않았습니다.');");
        		 //out.println("document.location.href='emplist.do'");
        		 out.println("history.back();");
        		 out.println("</script>");
        	 }
         } else if (OperationType.equals("EmpUpdate_form")) {
        	 logger.info("OperationType = " + OperationType);
        	 
      	     EmpSvc svc = new EmpSvc();
        	 request.setAttribute("detail",(EmpDto)svc.selectDetail(request)); 
      	     RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/emp_update_form.jsp");
            
      	     rd.forward(request, response);
           //response.sendRedirect("emp.do");
        } else if (OperationType.equals("EmpUpdate")) {
        	logger.info("OperationType = " + OperationType);
        	
            EmpSvc svc = new EmpSvc();
            rt = svc.EmpUpdate(request);
     	     
          	 if ( rt > 0){
        		// request.setAttribute("list",(ArrayList<EmpDto>)svc.getEmpList(request));
        		 RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/emp_select_list.jsp");
        		 
        		 response.sendRedirect("emplist.do");
          	 } 	else {       		 
        		 //PrintWriter out = response.getWriter();
        		 
        		 out.println("<script language='javascript'>");
        		 out.println("alert('정상적으로 처리 되지 않았습니다.');");
        		 //out.println("document.location.href='emplist.do'");
        		 out.println("history.back();");
        		 out.println("</script>");
        	 } 	
        } else if (OperationType.equals("EmpDelete")) {    
        	 logger.info("OperationType = " + OperationType);
        	
            EmpSvc svc = new EmpSvc();
            rt = svc.EmpDelete(request);

       	 if ( rt > 0){
    		 //request.setAttribute("list",(ArrayList<EmpDto>)svc.getEmpList(request));
    		// RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/src/emp_select_list.jsp");
    		 
    		 response.sendRedirect("emplist.do");
       	     }
    	 } else if (OperationType.equals("emplogin")) {       	 
        	 EmpSvc svc = new EmpSvc();
        	 EmpDto dto = new EmpDto();
        	 rt = svc.EmpLogin(request);
        	 
            System.out.println("rt = " + rt);   
        		
        		if ( rt == 1 ){
        			//HttpSession session = request.getSession();
					session.setAttribute("id",dto.getId());
					//request.setAttribute("list",(ArrayList<EmpDto>)svc.getEmpList(request));
        		    RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/emp_select_list.jsp");
                 
        		   //rd.forward(request, response);
        		   response.sendRedirect("emplist.do");
        		        
        	    } 	 else
        	       {
                  logger.info("rt : " + rt);
                  
         		    out.println("<script language='javascript'>");
         		    out.println("alert('아이디 또는 패스워드가 틀립니다..');");
         		 //out.println("document.location.href='emplist.do'");
         		    out.println("history.back();");
         		    out.println("</script>");
        	    	 //RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
              	 //rd.forward(request, response);
        	    	 //response.sendRedirect("emplogin.do");
        	       }
         }  else if (OperationType.equals("logout")) 
                   {
        	         session.invalidate();
        	         logger.info("Log OUT !!!!! ");
          		    out.println("<script language='javascript'>");
          		    out.println("alert('정상적으로 로그아웃 처리 되었습니다.');");
          		    out.println("document.location.href='emplist.do'");
          		    //out.println("history.back();");
          		    out.println("</script>");                 
                    } 
        	 
         }
       }
     }

