package com.rp.emp;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.rp.DBUtil;
import com.rp.emp.EmpDto;

public class EmpDao {  
	final static Logger logger = Logger.getLogger(EmpDao.class);
    private static EmpDao dao;
    
    int rt = 0;
	 Object obj = null;
	 EmpDto dto = new EmpDto();
    
    // Dao Instance 싱글톤
    public static EmpDao getInstance() {
        if (dao == null) {
            dao = new EmpDao();
            return dao;
        } else {
            return dao;
        }
    }
    
    // Emp 데이터 조회
	public List<EmpDto> selectEMPlist(EmpSearchDto sdto, SqlSession sqlSession) throws SQLException {		
        
        // Query ID 만 넘겨주면 해당 쿼리를 실행하여 데이터 조회
		List<EmpDto> list =  sqlSession.selectList("com.rp.emp.EmpDao.selectEmpList", sdto);
        
       return list; 
    }
	
	public EmpDto selectDetail(EmpDto dto, SqlSession sqlSession) throws SQLException, IOException, PropertyVetoException {
         logger.info("##########  EmpDao : selectDetail ========================================");
         
         EmpDto detail_dto =  (EmpDto) sqlSession.selectOne("selectDetail", dto);
         
        // logger.info("##########  EmpDao : Return list :" + list);
         
        return detail_dto;
    }
     
	public Object insertEMP(EmpDto dto, SqlSession sqlSession) throws SQLException {
		 logger.info("##########  EmpDao : insertEMP ========================================");
		 
	     obj = sqlSession.insert("insertEmp", dto);
	     
	     logger.info("##########  EmpDao : Return obj : " + obj);	
        logger.info("getName: {}"+ obj.getClass().getName());
        logger.info("id: {}"+ obj.toString());
       
        return obj;
    }

	public Object EmpUpdate(EmpDto dto, SqlSession sqlSession) throws SQLException, IOException {
		
		    logger.info("##########  EmpDao : EmpUpdate ========================================");
	  
		    obj = sqlSession.update("updateEMP", dto);
		     
		     logger.info("##########  EmpDao : Return obj : " + obj);	
/*		     logger.info("getName: {}"+ obj.getClass().getName());
		     logger.info("id: {}"+ obj.toString());*/
	      
	       return obj;
		}

public Object EmpDelete(EmpDto dto,SqlSession sqlSession) throws SQLException, IOException {
	    
	    logger.info("############ EmpDao : EmpDelete : start =====================================");
		  
	    obj = sqlSession.delete("deleteEMP", dto);	    
	    
	    logger.info("##########  EmpDao : Return obj : " + obj);        
       logger.info("##########  EmpDao : EmpDelete : END =====================================");
       
        return obj;
	}

public int EmpLogin(EmpDto dto,SqlSession sqlSession) throws SQLException, IOException {
	 logger.info("Login_emp.do : EmpDao ============================");
	 logger.info(dto.toString()); 
	  
	 obj = sqlSession.selectList("EmpLogin",dto);  
	 
	 logger.info("#############  EmpDao : return ResultSet : " + obj);
	 
	 rt = ((ArrayList<EmpDto>) obj).size();
	
     //logger.info("###### obj : " + ((ResultSet) obj).last());
	
	 logger.info("EmpDao : obj size : " + rt);  
		
return rt;
}

}