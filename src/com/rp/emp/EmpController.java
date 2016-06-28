package com.rp.emp;
import java.io.IOException;
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
         
        response.setContentType("text/html"); 
        //response.getWriter().append("Served at: ").append(request.getContextPath());
        HttpSession session = request.getSession();
        
        System.out.println("=========================================");
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
        	  EmpSvc svc = new EmpSvc();
            request.setAttribute("list",(ArrayList<EmpDto>)svc.getEmpList(request));
            RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/emp_select_list.jsp");

           logger.info("OperationType = " + OperationType);

        	rd.forward(request, response);
      	
        } else if (OperationType.equals("SearchList")) {
        	  EmpSvc svc = new EmpSvc();
            request.setAttribute("list",(ArrayList<EmpDto>)svc.getEmpList(request));
            RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/emp_select_list.jsp");
             
            rd.forward(request, response);
        } else if (OperationType.equals("empDetail")) {
      	   EmpSvc svc = new EmpSvc();
          
      	   EmpDto al = null;
      	   al = svc.selectDetail(request);

          RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/emp_select_detail.jsp");
           
          rd.forward(request, response);
         } else if (OperationType.equals("EmpInsert_form")) {
             
           RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/emp_insert_form.jsp");
           rd.forward(request, response);
        
        } else if (OperationType.equals("EmpInsert")) {
        	        	 
        	 EmpSvc svc = new EmpSvc();
        	 rt = svc.addEmp(request);
        	 
             System.out.println("rt =" + rt);
        	 
        	 if ( rt > 0){
        		 request.setAttribute("list",(ArrayList<EmpDto>)svc.getEmpList(request));
        		 RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/emp_select_list.jsp");
        		 
        		 response.sendRedirect("emplist.do");
        	 } 	 
         } else if (OperationType.equals("EmpUpdate_form")) {
      	     EmpSvc svc = new EmpSvc();
        	 EmpDto al = null;
          	 al = svc.selectDetail(request);
      	     
           RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/emp_update_form.jsp");
           rd.forward(request, response);
           //response.sendRedirect("emp.do");
        } else if (OperationType.equals("EmpUpdate")) {
            //EmpDto al = null;
            EmpSvc svc = new EmpSvc();
           // EmpDto dto = null;
            rt = svc.EmpUpdate(request);
     	     
          	 if ( rt > 0){
        		 request.setAttribute("list",(ArrayList<EmpDto>)svc.getEmpList(request));
        		 RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/emp_select_list.jsp");
        		 
        		 response.sendRedirect("emplist.do");
        	 } 	
        } else if (OperationType.equals("EmpDelete")) {        
            EmpSvc svc = new EmpSvc();
            rt = svc.EmpDelete(request);

       	 if ( rt > 0){
    		 request.setAttribute("list",(ArrayList<EmpDto>)svc.getEmpList(request));
    		 RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/src/emp_select_list.jsp");
    		 
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
        	    	 RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
              	 //rd.forward(request, response);
        	    	 response.sendRedirect("emplogin.do");
        	       }
         }  else if (OperationType.equals("logout")) 
                   {
        	         session.invalidate();
        	         logger.info("Log OUT !!!!! ");
        	         response.sendRedirect("emplogin.do");                   
                    } 
        	 
         }
       }
     }

