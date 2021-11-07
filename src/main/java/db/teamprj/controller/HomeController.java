package db.teamprj.controller;

import db.teamprj.TeamprjApplication;
import db.teamprj.domain.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.*;
import java.util.ArrayList;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) throws SQLException {

        ArrayList<Employee> emps = new ArrayList<Employee>();

        String fname, minit, lname, ssn, bdate, address, sex, salary, supervisor_name, department_name;

        Connection con;

        String dbaccount = "root";
        String pwd = "7651";
        String dbname = "jdbcTest";

        String url = "jdbc:mysql://localhost:3306/"+dbname+"?serverTimezone=UTC";
        con = DriverManager.getConnection(url, dbaccount, pwd);

        String SQL = "SELECT e.Fname, e.Minit, e.Lname, e.Ssn, e.Bdate, e.Address, e.Sex, e.Salary, s.Fname AS Supervisor_name, d.Dname" +
                "   FROM EMPLOYEE e, EMPLOYEE s, DEPARTMENT d" +
                "   WHERE e.Dno = d.Dnumber AND e.Super_SSn = s.Ssn;";
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
            Employee emp = new Employee(fname, minit, lname, ssn, bdate, address, sex, salary, supervisor_name, department_name);
            emps.add(emp);
            System.out.println(fname+" "+minit+" "+lname+" "+ssn+" "+bdate+" "+address+" "+sex+" "+salary+" "+supervisor_name+" "+department_name);
        }
        con.close();
        stmt.close();
        result.close();

        model.addAttribute("person", "suhwan");
        model.addAttribute("person_num", "10");
        model.addAttribute("employees", emps);
        return "home";
    }
}
