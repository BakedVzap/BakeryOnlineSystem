
package com.baked.databaseManager;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 
 * @author UnicornBrendan
 */
public class DBManager {
    private static DataSource dataSource;
    private static BasicDataSource dataSource1;
    
	//*************************************************************************************
	public DBManager() 
        {
		
	}
        // Rip and Stuart each used a different data source class, check how each works.
	//*************************************************************************************
	static {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context)initContext.lookup("java.comp/env");
            dataSource = (DataSource)envContext.lookup("jdbc/tagdb");
        } catch (NamingException ex) 
        {
            System.out.println("You FAILED,!* There is no Connection to the database!!!!!");
        }
            
                
	}
	public static Connection getConnection() throws SQLException{
            if(dataSource!=null)
            {
		return dataSource.getConnection();
            }else 
            {
                dataSource1= new BasicDataSource();
                dataSource1.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource1.setUrl("jdbc:mysql://localhost:3306/thedoughknot");
		dataSource1.setUsername("root");
		dataSource1.setPassword("root");
		dataSource1.setMinIdle(10);
		dataSource1.setMaxIdle(10);
		dataSource1.setMaxOpenPreparedStatements(100);	
                return (Connection)dataSource1;
            }
            
            
	}
}
