package db.teamprj.domain;

public class Works_on {

    private String essn;
    private String pno;
    private String hours;

    public Works_on(String essn, String pno, String hours) {
        this.essn = essn;
        this.pno = pno;
        this.hours = hours;
    }

    public String getEssn() {
        return essn;
    }

    public void setEssn(String essn) {
        this.essn = essn;
    }

    public String getPno() {
        return pno;
    }

    public void setPno(String pno) {
        this.pno = pno;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }
}
