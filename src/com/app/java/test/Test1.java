package com.app.java.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.app.java.Employee;
import com.app.java.EmployeeDao;

public class Test1 {

	public static void main(String[] args) {

		ApplicationContext context=new ClassPathXmlApplicationContext("com/app/xml/applicationContext.xml");
		
		EmployeeDao eDao=  (EmployeeDao) context.getBean("edao");
		
		/*int status = eDao.addEmp(new Employee(1002,"vijay",45000));
		if(status==1)
			System.out.println("Data is entered successfully");
		*/
		System.out.println("Get emp list using ResultSetExtractor");
		List<Employee> empList=eDao.getAllEmp();
		for (Employee emp : empList) {
			System.out.println(emp);
		}
		
		System.out.println("Get Emp list using RowMapper");
		for (Employee employee : eDao.getAllEmployeesUsingRowMapper()) {
			System.out.println(employee);
		}
	}

}
