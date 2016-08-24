package com.rp.emp;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.rp.AESEncrypt;

@WebServlet("*.do")
public class EmpController extends HttpServlet {
	final static Logger logger = Logger.getLogger(EmpController.class);
    private static final long serialVersionUID = 1L;       

    public EmpController() {
        super();
    
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        doPost(request, response);  
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        Object obj;
        int rt = 0;
        
		 response.setCharacterEncoding("UTF-8");
		 response.setContentType("text/html; charset=UTF-8");
		 
		 PrintWriter out = response.getWriter();
		 
        HttpSession session = request.getSession();
        
        EmpSearchDto sdto = new EmpSearchDto();
        sdto.setSearch_type(request.getParameter("search_type"));
        sdto.setSearch_string(request.getParameter("search_string"));
        
   	     EmpDto dto = new EmpDto();
   	     EmpSvc svc = new EmpSvc();
         // Provider 추가
         Security.addProvider(new BouncyCastleProvider());
       
        //RequestURI 구하기
        String command = request.getRequestURI();        // /jdbc-sample-mvc/login/login.do
        if (command.indexOf(request.getContextPath()) == 0) {       
            command = command.substring(request.getContextPath().length()); // /hello.do
        }
        System.out.println("command # " + command + " - ");
            
        
     /*
      *  Controller Start !!!!!!!!!!!!!!   
      */
        
        
  logger.info("##############  controller start ==============================================="); 
        
        
	if (session.getAttribute("id") == null &&  !("/login_emp.do").equals(command) )  {
				logger.info("#########  senssion null!!!!!!!");
				logger.info("#########  Login Page Forwarding ~~~~~~"); 
			
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			
				rd.forward(request, response);
				//response.sendRedirect("login.do");
	} else if (session.getAttribute("id") != null) {     
        	
		logger.info("#########  senssion not null!!!!!!!"); 

       if (("/select_emp_list.do").equals(command)) {
       
    	   logger.info("controller : select_emp_list.do ==============================================="); 
        	
             request.setAttribute("list",(ArrayList<EmpDto>)svc.getEmpList(sdto));
                         
          logger.info("#########  Forwading Page ===>  select_emp_list.jsp "); 
           
             RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/emp_select_list.jsp");
             rd.forward(request, response);
                         
      } else if (("/select_emp_detail.do").equals(command)) {
      
    	   logger.info("controller : select_emp_detail.do ===============================================");

        	dto.setSeq(Integer.parseInt(request.getParameter("seq")));
          request.setAttribute("detail",(EmpDto)svc.selectDetail(dto));    
            
          logger.info("#########  Forwading Page ===>  emp_select_deatail.jsp "); 
            
          RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/emp_select_detail.jsp");
          rd.forward(request, response);
            //response.sendRedirect("select_emp_detail.do");
            
      } else if (("/insert_emp_form.do").equals(command)) {
    	      
    	   logger.info("#########  controller : insert_emp_form.do ===============================================");
        	 
          RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/emp_insert_form.jsp");
          rd.forward(request, response);
         // response.sendRedirect("insert_emp_form.do");
        
       } else if (("/insert_emp.do").equals(command)) {
         	 
          	logger.info("#########  controller : insert_emp.do ===============================================");
          	
          	 String passwd = request.getParameter("passwd");
          	 String encodedKey = "RockpalceMidware";
            
          	 System.out.println("#########  secretKey: " + encodedKey);
          	 System.out.println("#########  passwd   : " + passwd);
          	 
           AESEncrypt Enc = new AESEncrypt();
           
           try {
				  dto.setPasswd(Enc.Encrypt(passwd, encodedKey));
				  System.out.println("#########  passwd   :" + dto.passwd);
				  
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          	          	 
        	 //dto.setSeq(Integer.parseInt(request.getParameter("seq")));
        	 dto.setId(Integer.parseInt(request.getParameter("id")));
        	 dto.setAge(Integer.parseInt(request.getParameter("age")));
        	 dto.setFirst(request.getParameter("first"));
        	 dto.setLast(request.getParameter("last"));
        	 dto.setDeptSeq(Integer.parseInt(request.getParameter("dept_seq")));      
    	 
        	 logger.info("############  controller : insert_emp.do : EmpDto Info " + dto.toString());
        	 
        	 obj = svc.addEmp(dto);
        	 
        	 logger.info("############ controller : insert_emp.do : Return obj : " + obj);
        	 
        	 if ( !("0").equals(obj)) {
        		 logger.info("controller : insert_emp.do : Success !! ===============================================");
        		 response.sendRedirect("select_emp_list.do");
        		 
        	 } 	else {       		 
        		// PrintWriter out = response.getWriter();
        		 logger.info("#########  controller : insert_emp.do : Fail !! ===============================================");
        		 
        		 out.println("<script language='javascript'>");
        		 out.println("alert('정상적으로 처리 되지 않았습니다.');");
        		 //out.println("document.location.href='emplist.do'");
        		 out.println("history.back();");
        		 out.println("</script>");
        	 }
        	 
       } else if (("/update_emp_form.do").equals(command)) {
	        
    	     logger.info("#########  controller : update_emp_form.do ===============================================");
	
        	 dto.setSeq(Integer.parseInt(request.getParameter("seq")));
        	 
        	 EmpDto detail = null ;
        	 detail = (EmpDto)svc.selectDetail(dto);
        	 
        	 String passwd = detail.getPasswd();
        	 String encodedKey = "RockpalceMidware";
            
        	 logger.info(" ###### Controller Return Dto : " + detail);
        	 
          	 System.out.println("#########  secretKey: " + encodedKey);
          	 System.out.println("#########  passwd   : " + detail.getPasswd());
          	 
           AESEncrypt Enc = new AESEncrypt();
        	 
           try {
				  detail.setPasswd(Enc.Decrypt(passwd, encodedKey));
				  System.out.println("#########  passwd   :" + detail.getPasswd());
				  
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
      	     
        	 request.setAttribute("detail",detail); 
      	     RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/emp_update_form.jsp");
            
      	     rd.forward(request, response);
           //response.sendRedirect("emp.do");
      	     
       } else if (("/update_emp.do").equals(command)) {
	    
    	     logger.info("#########  controller : update_emp.do ===============================================");
	
    	     String passwd = request.getParameter("passwd");
            String encodedKey = "RockpalceMidware";
            
          	 System.out.println("#########  secretKey: " + encodedKey);
          	 System.out.println("#########  passwd   : " + passwd);
          	 
           AESEncrypt Enc = new AESEncrypt();
           
           try {
				  dto.setPasswd(Enc.Encrypt(passwd, encodedKey));
				  System.out.println("#########  passwd   :" + dto.passwd);
				  
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	     
       	 dto.setSeq(Integer.parseInt(request.getParameter("seq")));
       	 dto.setId(Integer.parseInt(request.getParameter("id")));
       	 dto.setAge(Integer.parseInt(request.getParameter("age")));
       	 dto.setFirst(request.getParameter("first"));
       	 //dto.setPasswd(request.getParameter("passwd"));
       	 dto.setLast(request.getParameter("last"));
       	 dto.setDeptSeq(Integer.parseInt(request.getParameter("dept_seq"))); 
            
            obj = svc.EmpUpdate(dto);
            
            logger.info("############ controller : insert_emp.do : Return obj : " + obj);
     	     
          	 //if ( ("1").equals(obj)){
            if (!("0").equals(obj)){
          		logger.info("#########  controller : update_emp.do : Success ~!!!! ===============================================");
        		 RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/emp_select_list.jsp");
        		 
        		 response.sendRedirect("select_emp_list.do");
          	 } 	else {
          		logger.info("#########  controller : update_emp.do : Fail ~!!!! ===============================================");
        		 //PrintWriter out = response.getWriter();
        		 
        		 out.println("<script language='javascript'>");
        		 out.println("alert('정상적으로 처리 되지 않았습니다.');");
        		 out.println("history.back();");
        		 out.println("</script>");
        	 }
          	 
        } else if (("/delete_emp.do").equals(command)) {  
        	
        	logger.info("#########  controller : delete_emp.do ===============================================");

          	 dto.setSeq(Integer.parseInt(request.getParameter("seq")));
        	 obj = svc.EmpDelete(dto);
        	 
        	 logger.info("##########  controller : Return obj : " + obj);   

       	 if (!("".equals(obj))){
       		 response.sendRedirect("select_emp_list.do");
       	     }
       	 
    	 } else if (("/logout_emp.do").equals(command)) { 

	        session.invalidate();
	        logger.info("Log OUT !!!!! ");
	         
  		    out.println("<script language='javascript'>");
  		    out.println("alert('정상적으로 로그아웃 처리 되었습니다.');");
  		    out.println("document.location.href='select_emp_list.do'");
  		    out.println("</script>");     
  		    
        } else if (("/").equals(command)) {  
        	
        	logger.info("############ controller : Maing Page Forwarding !!!! ===============================================");

    		 response.sendRedirect("select_emp_list.do");
       	     }
       
    } else {
	        	 
        	 if (("/login_emp.do").equals(command)) {       	 
        		
               String passwd = request.getParameter("passwd");       	 
               String encodedKey = "RockpalceMidware";
                
           	 System.out.println("#########  secretKey:" + encodedKey);
          	    System.out.println("#########  passwd   : " + passwd);
        		 
	        	 dto.setId(Integer.parseInt(request.getParameter("id")));
	         	 dto.setPasswd(request.getParameter("passwd"));
	        	 
	           AESEncrypt Enc = new AESEncrypt();
	             
	             try {
	  				  dto.setPasswd(Enc.Encrypt(passwd, encodedKey));
					  System.out.println("#########  passwd   :" + dto.passwd);
	  			} catch (Exception e) {
	  				// TODO Auto-generated catch block
	  				e.printStackTrace();
	  			}
	         	 
	     		logger.info("#########  controller : Login_emp.do =======================================");
	     		logger.info(dto.toString());
	     		
	        	 rt = svc.EmpLogin(dto);
	        	 
	        	 logger.info("################### controller : Return rt : " + rt); 
	        	 
	        	 
	        		if (  rt == 1) {
	        			//HttpSession session = request.getSession();
						session.setAttribute("id",dto.getId());
	                    
						 logger.info("################### controller : emp_select_list Forwarding "); 
	
						 RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/jsp/emp_select_list.jsp");
						 response.sendRedirect("select_emp_list.do");
						 
	        	    } 	else {
	        	       
	                  logger.info("###################  rt : " + rt);
	                  
	         		    out.println("<script language='javascript'>");
	         		    out.println("alert('아이디 또는 패스워드가 틀립니다..');");
	         		    out.println("history.back();");
	         		    out.println("</script>");
	        	    	 
	        	     }
	         } else {
	        	  
				        logger.info("#########  Login Page ( command = null) !!!!!!!");
			        	  RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			          	
			             rd.forward(request, response);
			          } 
			}
		}
	}


