package db.teamprj.domain;

public class Dependent {

    private String essn;
    private String dependent_name;
    private String sex;
    private String bdate;
    private String relationship;

    public Dependent(String essn, String dependent_name, String sex, String bdate, String relationship) {
        this.essn = essn;
        this.dependent_name = dependent_name;
        this.sex = sex;
        this.bdate = bdate;
        this.relationship = relationship;
    }

    public String getEssn() {
        return essn;
    }

    public void setEssn(String essn) {
        this.essn = essn;
    }

    public String getDependent_name() {
        return dependent_name;
    }

    public void setDependent_name(String dependent_name) {
        this.dependent_name = dependent_name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBdate() {
        return bdate;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }
}
