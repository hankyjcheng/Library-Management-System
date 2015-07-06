package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public abstract class BaseDAO<T> {
	
	private Connection connection = null;
	
	public BaseDAO(Connection connection) throws Exception {
		this.connection = connection;
	}
	
	public Connection getConnection() throws Exception{
		return connection;
	}

	public void save(String query, Object[] values) throws Exception{
		Connection connection = getConnection();

		PreparedStatement pst = connection.prepareStatement(query);
		int count = 1;
		for(Object object: values){
			pst.setObject(count, object);
			count++;
		}
		
		pst.executeUpdate();
	}
	
	public int saveWithId(String query, Object[] values) throws Exception{
		Connection connection = getConnection();

		PreparedStatement pst = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		int count = 1;
		for(Object object: values){
			pst.setObject(count, object);
			count++;
		}
		
		pst.executeUpdate();
		ResultSet resultSet = pst.getGeneratedKeys();
		if (resultSet.next()) {
			return resultSet.getInt(1);
		} 
		else {
			return -1;
		}
	}
	
	public List<?> read(String query, Object[] values) throws Exception{
		Connection connection = getConnection();
		PreparedStatement pst = connection.prepareStatement(query);
		
		if(values != null){
			int count = 1;
			for(Object object: values){
				pst.setObject(count, object);
				count++;
			}
		}
		ResultSet resultSet = pst.executeQuery();
		return extractData(resultSet);
	}
	
	public abstract List<T> extractData(ResultSet resultSet) throws Exception;
	
	public List<?> readFirstLevel(String query, Object[] values) throws Exception{
		Connection connection = getConnection();
		PreparedStatement pst = connection.prepareStatement(query);
		
		if(values != null){
			int count = 1;
			for(Object object: values){
				pst.setObject(count, object);
				count++;
			}
		}
		ResultSet resultSet = pst.executeQuery();
		return extractDataFirstLevel(resultSet);
	}
	
	public abstract List<T> extractDataFirstLevel(ResultSet resultSet) throws Exception;
}
