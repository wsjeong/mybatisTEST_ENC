package com.rp.emp;
public class EmpDto {
    int seq;
    int id;
    String passwd;
    String first;
    String last;
    int age;
    String dept_nm;
    int dept_seq;
    
    public int getSeq() {
        return seq;
    }
    public void setSeq(int seq) {
        this.seq = seq;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getPasswd() {
        return passwd;
    }
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
    public String getFirst() {
        return first;
    }
    public void setFirst(String first) {
        this.first = first;
    }
    public String getLast() {
        return last;
    }
    public void setLast(String last) {
        this.last = last;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getDeptNM() {
        return dept_nm;
    }
    public void setDeptNM(String dept_nm) {
        this.dept_nm = dept_nm;
    }
    public int getDeptSeq() {
        return dept_seq;
    }
    public void setDeptSeq(int dept_seq) {
        this.dept_seq = dept_seq;
    } 
    @Override
    public String toString() {
        return "EmpDto [seq=" + seq + ", id=" + id + ", passwd=" + passwd
                + ", first=" + first + ", last=" + last + ", age=" + age + ",dept_nm=" + dept_nm + ",dept_seq=" + dept_seq + "]";
    }
     
     
}
