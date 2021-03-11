/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.baked.databaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author dell pc
 */
public class DBManager {
    private static BasicDataSource dataSource = new BasicDataSource();
	//*************************************************************************************
	private DBManager() {
		
	}
	//*************************************************************************************
	static {
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/thedoughknot");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		dataSource.setMinIdle(10);
		dataSource.setMaxIdle(10);
		dataSource.setMaxOpenPreparedStatements(100);	
	}
	public static Connection getConnection () throws SQLException{
		return dataSource.getConnection();
	}
}
