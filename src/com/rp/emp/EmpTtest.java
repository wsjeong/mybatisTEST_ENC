package com.rp.emp;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class EmpTtest {
    @Test
    public void testSqlMapClient() {
        String resource = "SqlMapConfig.xml";
        Reader reader;
        try {
            reader = Resources.getResourceAsReader(resource);
            SqlMapClient sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(reader);
             
            EmpSearchDto search_dto = new EmpSearchDto();
            search_dto.setSearch_type("id");
            search_dto.setSearch_string("55");
             
            List<EmpDto> list =  (List<EmpDto>)sqlMapClient.queryForList("selectEmpList", search_dto);
                        
        } catch (IOException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         
    }
}