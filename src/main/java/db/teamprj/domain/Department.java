package db.teamprj.domain;

public class Department {

    private String dname;
    private String dnumber;
    private String mgr_ssn;
    private String mgr_start_date;

    public Department(String dname, String dnumber, String mgr_ssn, String mgr_start_date) {
        this.dname = dname;
        this.dnumber = dnumber;
        this.mgr_ssn = mgr_ssn;
        this.mgr_start_date = mgr_start_date;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDnumber() {
        return dnumber;
    }

    public void setDnumber(String dnumber) {
        this.dnumber = dnumber;
    }

    public String getMgr_ssn() {
        return mgr_ssn;
    }

    public void setMgr_ssn(String mgr_ssn) {
        this.mgr_ssn = mgr_ssn;
    }

    public String getMgr_start_date() {
        return mgr_start_date;
    }

    public void setMgr_start_date(String mgr_start_date) {
        this.mgr_start_date = mgr_start_date;
    }
}
