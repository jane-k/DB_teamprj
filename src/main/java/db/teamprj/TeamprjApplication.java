package db.teamprj;

import java.sql.*;
import java.util.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TeamprjApplication {

	public static void main(String[] args) throws SQLException {

		SpringApplication.run(TeamprjApplication.class, args);
		String fname, minit, lname, ssn, bdate, address, sex, salary, supervisor_name, department_name;

		Connection con;

		String dbaccount = "jiin3909";
		String pwd = "130613";
		String dbname = "mydb";

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
			System.out.println(fname+" "+minit+" "+lname+" "+ssn+" "+bdate+" "+address+" "+sex+" "+salary+" "+supervisor_name+" "+department_name);
		}
		con.close();
		stmt.close();
		result.close();

	}

}
