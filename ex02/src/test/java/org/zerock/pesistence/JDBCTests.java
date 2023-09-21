package org.zerock.pesistence;



import java.sql.DriverManager;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class JDBCTests {

	@Test
	public void testConnection() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe"
				, "book_ex", "book_ex");
	
	}

	
}
