package db.teamprj.domain;

public class Project {

    private String pname;
    private String pnumber;
    private String plocation;
    private String dnum;

    public Project(String pname, String pnumber, String plocation, String dnum) {
        this.pname = pname;
        this.pnumber = pnumber;
        this.plocation = plocation;
        this.dnum = dnum;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPnumber() {
        return pnumber;
    }

    public void setPnumber(String pnumber) {
        this.pnumber = pnumber;
    }

    public String getPlocation() {
        return plocation;
    }

    public void setPlocation(String plocation) {
        this.plocation = plocation;
    }

    public String getDnum() {
        return dnum;
    }

    public void setDnum(String dnum) {
        this.dnum = dnum;
    }
}
