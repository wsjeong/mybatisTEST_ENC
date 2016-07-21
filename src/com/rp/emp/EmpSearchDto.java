package com.rp.emp;

public class EmpSearchDto {
	String Search_type;
	String Search_string;
	public String getSearch_type() {
		return Search_type;
	}
	public void setSearch_type(String search_type) {
		Search_type = search_type;
	}
	public String getSearch_string() {
		return Search_string;
	}
	public void setSearch_string(String search_string) {
		Search_string = search_string;
	}
	@Override
    public String toString() {
        return "EmpSearchDto [Search_type=" + Search_type + ", Search_string=" + Search_string + "]";
	}
}