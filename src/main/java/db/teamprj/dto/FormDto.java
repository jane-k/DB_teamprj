package db.teamprj.dto;

public class FormDto {
    private String root_select;
    private String input_value;
    private String name;
    private String ssn;
    private String bdate;
    private String address;
    private String sex;
    private String salary;
    private String supervisor_name;
    private String department_name;

    public String getRoot_select() {
        return root_select;
    }

    public void setRoot_select(String root_select) {
        this.root_select = root_select;
    }

    public String getInput_value() {
        return input_value;
    }

    public void setInput_value(String input_value) {
        this.input_value = input_value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getBdate() {
        return bdate;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getSupervisor_name() {
        return supervisor_name;
    }

    public void setSupervisor_name(String supervisor_name) {
        this.supervisor_name = supervisor_name;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public FormDto(String root_select, String input_value, String name, String ssn, String bdate, String address, String sex, String salary, String supervisor_name, String department_name) {
        this.root_select = root_select;
        this.input_value = input_value;
        this.name = name;
        this.ssn = ssn;
        this.bdate = bdate;
        this.address = address;
        this.sex = sex;
        this.salary = salary;
        this.supervisor_name = supervisor_name;
        this.department_name = department_name;
    }
}
