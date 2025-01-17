package db.teamprj.controller;

import db.teamprj.dto.DeleteDto;
import db.teamprj.dto.FormDto;
import db.teamprj.dto.InsertDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

@Controller
public class HomeController {

    private ArrayList<HashMap<String, String>> emps = new ArrayList<HashMap<String, String>>();
    private Boolean b_name = true, b_ssn = true, b_bdate = true,
            b_address = true, b_sex = true, b_salary = true,
            b_supervisor_name = true, b_department_name = true;

    private final String dbaccount = "root";
    private final String pwd = "7651";
    private final String dbname = "jdbcTest";
    private final String url = "jdbc:mysql://localhost:3306/"+dbname+"?serverTimezone=UTC";

    @GetMapping("/")
    public String home(Model model) throws SQLException {

        emps = new ArrayList<HashMap<String, String>>();

        String fname, minit, lname, ssn, bdate, address, sex, salary, supervisor_name, department_name;

        Connection con;
        con = DriverManager.getConnection(url, dbaccount, pwd);

        String SQL = "SELECT e.Fname, e.Minit, e.Lname, e.Ssn, e.Bdate, e.Address, e.Sex, e.Salary, s.Fname AS Supervisor_name, Dname " +
                "FROM EMPLOYEE e " +
                "LEFT OUTER JOIN EMPLOYEE s " +
                "ON e.Super_ssn = s.Ssn " +
                "LEFT OUTER JOIN Department " +
                "ON e.Dno = Dnumber;";
        Statement stmt = con.createStatement();
        ResultSet result = stmt.executeQuery(SQL);

        while(result.next()) {
            fname = result.getString("Fname");
            minit = result.getString("Minit");
            lname = result.getString("Lname");
            ssn = result.getString("Ssn");
            bdate = result.getString("Bdate");
            address = result.getString("Address");
            sex = result.getString("Sex");
            salary = result.getString("Salary");
            supervisor_name = result.getString("Supervisor_name");
            department_name = result.getString("Dname");
            String name = fname+" "+minit+" "+lname;
            HashMap<String, String> emp = new HashMap<String, String>();
            emp.put("name", name);
            emp.put("ssn", ssn);
            emp.put("bdate", bdate);
            emp.put("address", address);
            emp.put("sex", sex);
            emp.put("salary", salary);
            emp.put("supervisor_name", supervisor_name);
            emp.put("department_name", department_name);
            emps.add(emp);
        }
        con.close();
        stmt.close();
        result.close();

        model.addAttribute("employees", emps);
        model.addAttribute("employee_num", emps.size());

        model.addAttribute("b_name", b_name);
        model.addAttribute("b_ssn", b_ssn);
        model.addAttribute("b_bdate", b_bdate);
        model.addAttribute("b_address", b_address);
        model.addAttribute("b_sex", b_sex);
        model.addAttribute("b_salary", b_salary);
        model.addAttribute("b_supervisor_name", b_supervisor_name);
        model.addAttribute("b_department_name", b_department_name);

        return "home";
    }

    @PostMapping("/")
    public String permit(Model model, FormDto formDto) throws SQLException {
        b_name = false; b_ssn = false; b_bdate = false;
        b_address = false; b_sex = false; b_salary = false;
        b_supervisor_name = false; b_department_name = false;

        ArrayList<HashMap<String, String>> tmp = emps;

        emps = new ArrayList<HashMap<String, String>>();
        ArrayList<String> attribute = new ArrayList<String>();
        String condition = "";


        String fname, minit, lname, ssn, bdate, address, sex, salary, supervisor_name, department_name;

        if(Objects.equals(formDto.getRoot_select(), "dname")){
            condition = "Dname = \"" + formDto.getInput_value() + "\"";
        }
        else if(Objects.equals(formDto.getRoot_select(), "sex")){
            condition = "e.Sex = \"" + formDto.getInput_value() + "\"";
        }
        else if(Objects.equals(formDto.getRoot_select(), "salary")){
            condition = "e.Salary > \"" + formDto.getInput_value() + "\"";
        }
        else if(Objects.equals(formDto.getRoot_select(), "bdate")){
            condition = "e.Bdate LIKE \'_____" + formDto.getInput_value() + "___\'";
        }
        else if(Objects.equals(formDto.getRoot_select(), "descent")){
            condition = "s.Fname = \"" + formDto.getInput_value() + "\"";
        }

        if(formDto.getName()!= null){
            attribute.add("e.Fname, e.Minit, e.Lname");
            b_name = true;
        }
        if(formDto.getSsn()!= null){
            attribute.add("e." + formDto.getSsn());
            b_ssn = true;
        }
        if(formDto.getBdate()!= null){
            attribute.add("e." + formDto.getBdate());
            b_bdate = true;
        }
        if(formDto.getAddress()!= null){
            attribute.add("e." + formDto.getAddress());
            b_address = true;
        }
        if(formDto.getSex()!= null){
            attribute.add("e." + formDto.getSex());
            b_sex = true;
        }
        if(formDto.getSalary()!= null){
            attribute.add("e." + formDto.getSalary());
            b_salary = true;
        }
        if(formDto.getSupervisor_name()!= null){
            attribute.add("s.Fname AS Supervisor_name");
            b_supervisor_name = true;
        }
        if(formDto.getDepartment_name()!= null){
            attribute.add("Dname");
            b_department_name = true;
        }
        String query = "";

        for (int i = 0; i < attribute.size(); i++) {
            if(i == 0){
                query = query + attribute.get(i);
            }
            else{
                query = query + ", ";
                query = query + attribute.get(i);
            }
        }

        Connection con;
        con = DriverManager.getConnection(url, dbaccount, pwd);

        Statement stmt = con.createStatement();
        String SQL = "SELECT DISTINCT "+query+
                " FROM EMPLOYEE e " +
                "LEFT OUTER JOIN EMPLOYEE s " +
                "ON e.Super_ssn = s.Ssn " +
                "LEFT OUTER JOIN Department " +
                "ON e.Dno = Dnumber WHERE "+condition+";";

        ResultSet result = stmt.executeQuery(SQL);

        while(result.next()) {
            HashMap<String, String> emp = new HashMap<String, String>();
            if (b_name == true) {
                fname = result.getString("Fname");
                minit = result.getString("Minit");
                lname = result.getString("Lname");
                String name = fname + " " + minit + " " + lname;
                emp.put("name", name);
            }
            if (b_ssn == true) {
                ssn = result.getString("Ssn");
                emp.put("ssn", ssn);
            }
            if (b_bdate == true) {
                bdate = result.getString("Bdate");
                emp.put("bdate", bdate);
            }
            if (b_address == true) {
                address = result.getString("Address");
                emp.put("address", address);
            }
            if (b_sex == true) {
                sex = result.getString("Sex");
                emp.put("sex", sex);
            }
            if (b_salary == true) {
                salary = result.getString("Salary");
                emp.put("salary", salary);
            }
            if (b_supervisor_name == true) {
                supervisor_name = result.getString("Supervisor_name");
                emp.put("supervisor_name", supervisor_name);
            }
            if (b_department_name == true) {
                department_name = result.getString("Dname");
                emp.put("department_name", department_name);
            }
            emps.add(emp);
        }

        model.addAttribute("employees", emps);
        model.addAttribute("employee_num", emps.size());



        model.addAttribute("b_name", b_name);
        model.addAttribute("b_ssn", b_ssn);
        model.addAttribute("b_bdate", b_bdate);
        model.addAttribute("b_address", b_address);
        model.addAttribute("b_sex", b_sex);
        model.addAttribute("tmp", tmp);
        model.addAttribute("b_salary", b_salary);
        model.addAttribute("b_supervisor_name", b_supervisor_name);
        model.addAttribute("b_department_name", b_department_name);

        return "home";
    }

    @PostMapping("/update")
    public String update(Model model, @ModelAttribute("delete")DeleteDto dto, String update_list, String update_name) throws SQLException {
        String ssn = "";
        ssn = dto.getCheck().get(0);
        String value = "";
        String table = "";

        if(Objects.equals(update_list, "address")){
            table = "EMPLOYEE";
            value = "Address = \"" + update_name + "\"";
        }
        else if(Objects.equals(update_list, "sex")){
            value = "Sex = \"" + update_name + "\"";
            table = "EMPLOYEE";
        }
        else if(Objects.equals(update_list, "salary")){
            value = "Salary = " + update_name;
            table = "EMPLOYEE";
        }
        else if(Objects.equals(update_list, "super_ssn")){
            value = "Super_ssn = \"" + update_name + "\"";
            table = "EMPLOYEE";
        }
        else if(Objects.equals(update_list, "dname")){
            value = "Dno = (SELECT Dnumber FROM DEPARTMENT WHERE Dname = \"" + update_name + "\")";
            table = "EMPLOYEE";
        }

        Connection con;
        con = DriverManager.getConnection(url, dbaccount, pwd);

        Statement stmt = con.createStatement();
        String SQL = "UPDATE "+table+" SET "+value+" WHERE Ssn = \""+ssn+"\";";

        stmt.executeUpdate(SQL);

        home(model);

        model.addAttribute("employees", emps);
        model.addAttribute("employee_num", emps.size());

        model.addAttribute("b_name", b_name);
        model.addAttribute("b_ssn", b_ssn);
        model.addAttribute("b_bdate", b_bdate);
        model.addAttribute("b_address", b_address);
        model.addAttribute("b_sex", b_sex);
        model.addAttribute("b_salary", b_salary);
        model.addAttribute("b_supervisor_name", b_supervisor_name);
        model.addAttribute("b_department_name", b_department_name);
        return "home";
    }

    @PostMapping("/delete")
    public String delete(Model model, @ModelAttribute("delete")DeleteDto deleteDto) throws SQLException {
        ArrayList<String> ssn = new ArrayList<String>();;
        ssn = deleteDto.getCheck();


        Connection con;
        con = DriverManager.getConnection(url, dbaccount, pwd);

        String query = "";

        for(int i = 0; i < ssn.size(); i++){
            String stmt = "DELETE from EMPLOYEE WHERE Ssn = ?";
            PreparedStatement p = con.prepareStatement(stmt);

            p.clearParameters();
            p.setString(1, ssn.get(i));
            p.executeUpdate();
        }


        for (String s : ssn) {
            //해당 ssn 삭제하기
            for (int j = 0; j < emps.size(); j++) {
                if (emps.get(j).get("ssn").equals(s)) {
                    emps.remove(j);
                    System.out.println("Isdelete: " + s);
                    break;
                }
            }
        }


        model.addAttribute("employees", emps);
        model.addAttribute("employee_num", emps.size());

        model.addAttribute("b_name", b_name);
        model.addAttribute("b_ssn", b_ssn);
        model.addAttribute("b_bdate", b_bdate);
        model.addAttribute("b_address", b_address);
        model.addAttribute("b_sex", b_sex);
        model.addAttribute("b_salary", b_salary);
        model.addAttribute("b_supervisor_name", b_supervisor_name);
        model.addAttribute("b_department_name", b_department_name);
        return "home";
    }

    @PostMapping("/insert")
    public String insert(Model model, @ModelAttribute("insert")InsertDto insertDto) throws SQLException{
        Connection con;
        con = DriverManager.getConnection(url, dbaccount, pwd);

        String value;
        value = insertDto.toString();

        String SQL = "INSERT INTO EMPLOYEE VALUES " + value + ";";
        Statement stmt = con.createStatement();
        stmt.executeUpdate(SQL);

        con.close();
        stmt.close();

        home(model);

        model.addAttribute("employees", emps);
        model.addAttribute("employee_num", emps.size());

        model.addAttribute("b_name", b_name);
        model.addAttribute("b_ssn", b_ssn);
        model.addAttribute("b_bdate", b_bdate);
        model.addAttribute("b_address", b_address);
        model.addAttribute("b_sex", b_sex);
        model.addAttribute("b_salary", b_salary);
        model.addAttribute("b_supervisor_name", b_supervisor_name);
        model.addAttribute("b_department_name", b_department_name);
        return "home";
    }

    @PostMapping("/insert_btn")
    public String insert_btn(Model model){
        return "insert";
    }
}
