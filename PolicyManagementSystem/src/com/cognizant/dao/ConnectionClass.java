package com.cognizant.dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import org.apache.log4j.Logger;

public class ConnectionClass implements Commons {

	public static Connection con;
	public static Driver driver = null;
	private static final Logger LOGGER = Logger
			.getLogger(ConnectionClass.class);

	public static Connection openConnection() {
		LOGGER.info("openConnection()");
		try {

			driver= new com.mysql.jdbc.Driver();

			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection("jdbc:mysql://" + host + ":"
					+ port + "/" + database_name, username, password);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

		try {
			LOGGER.info("Connection returned" + con.getCatalog());
		} catch (SQLException e) {
			LOGGER.error("SQLException while returning Connection " + con);
			LOGGER.error("Exception : "+e.getMessage());
		}

		return con;
	}

	public static void closeConnection() {
		LOGGER.info("closeConnection()");
		Enumeration<Driver> drivers = DriverManager.getDrivers();
		System.out.println("hits");
		while (drivers.hasMoreElements()) {
			Driver driver = drivers.nextElement();
			try {
				LOGGER.info("De-registering " + driver + " driver");
				DriverManager.deregisterDriver(driver);
			} catch (SQLException e) {
				LOGGER.error("SQLException on closeConnection()-deregisterDriver(): "
						+ e.getMessage());
			}
			try {
				LOGGER.info("Connection for " + con.getCatalog()
						+ " has been closed");
				if (con != null)
					con.close();
			} catch (SQLException e) {
				LOGGER.error("SQLException on closeConnection()-con.close(): "
						+ e.getLocalizedMessage());
			}
		}
	}
}
