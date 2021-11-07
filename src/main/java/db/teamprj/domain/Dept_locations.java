package db.teamprj.domain;

public class Dept_locations {

    private String dnumber;
    private String dlocation;

    public Dept_locations(String dnumber, String dlocation){
        this.dnumber = dnumber;
        this.dlocation = dlocation;
    }

    public String getDnumber() {
        return dnumber;
    }

    public void setDnumber(String dnumber) {
        this.dnumber = dnumber;
    }

    public String getDlocation() {
        return dlocation;
    }

    public void setDlocation(String dlocation) {
        this.dlocation = dlocation;
    }
}

