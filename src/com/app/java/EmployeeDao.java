package com.app.java;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class EmployeeDao {
	private JdbcTemplate jdbcTemplate;
	
	//insert new employee details
	public int addEmp(Employee e) {
		String query="insert into employee values("+e.getId()+",'"+
						e.getName()+"',"+e.getSalary()+")";
		
		return jdbcTemplate.update(query);
	}
	//update employee details
	public int updateEmp(Employee e) {
		String query="";
		return jdbcTemplate.update(query);
	}
	

	//Get all list of employees using ResultSetExtractor
	public List<Employee> getAllEmp() {
		return jdbcTemplate.query("select * from employee", new ResultSetExtractor<List<Employee>>() {

				List<Employee> empList=new ArrayList<Employee>();
				@Override
				public List<Employee> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
					while(resultSet.next()) {
						Employee e=new Employee();
						e.setId(resultSet.getInt(1));
						e.setName(resultSet.getString(2));
						e.setSalary(resultSet.getInt(3));
						empList.add(e);
					}
					return empList;
				}
			});
	}
	
	//Get all employees list using RowMapper
	public List<Employee> getAllEmployeesUsingRowMapper(){
		
		return jdbcTemplate.query("select * from employee", new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
				Employee e=new Employee();
				e.setId(resultSet.getInt(1));
				e.setName(resultSet.getString(2));
				e.setSalary(resultSet.getInt(3));
				
				return e;
			}
			
		});
	}
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
