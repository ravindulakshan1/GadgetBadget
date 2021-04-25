package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Researcher {
	
	//A common method to connect to the DB
	private Connection connect() 
	 { 
	 Connection con = null; 
	 try
	 { 
	 Class.forName("com.mysql.jdbc.Driver"); 
	 
	 //Provide the correct details: DBServer/DBName, username, password 
	 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root"); 
	 } 
	 catch (Exception e) 
	 {e.printStackTrace();} 
	 return con; 
	 } 
	
	
	
	
	public String insertResearcher(String name, String address, String email, String desc) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for inserting."; } 
	 
	 // create a prepared statement
	 String query = " insert into researcher (resID, resName, resAddress, resEmail, resDesc)" + " values (?, ?, ?, ?, ?)"; 
	 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, 0); 
	 preparedStmt.setString(2, name); 
	 preparedStmt.setString(3, address); 
	 preparedStmt.setString(4, email); 
	 preparedStmt.setString(5, desc); 
	// execute the statement3
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Inserted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while inserting the researcher."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	
	
	
	public String readResearchers() 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for reading."; } 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Researcher Name</th><th>Address</th>" +
	 "<th>Email</th>" + 
	 "<th>Research Description</th>" +
	 "<th>Update</th><th>Remove</th></tr>"; 
	 
	 String query = "select * from researcher"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String resID = Integer.toString(rs.getInt("resID")); 
	 String resName = rs.getString("resName"); 
	 String resAddress = rs.getString("resAddress"); 
	 String resEmail = rs.getString("resEmail"); 
	 String resDesc = rs.getString("resDesc"); 
	 // Add into the html table
	 output += "<tr><td>" + resName + "</td>"; 
	 output += "<td>" + resAddress + "</td>"; 
	 output += "<td>" + resEmail + "</td>"; 
	 output += "<td>" + resDesc + "</td>"; 
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
	 + "<td><form method='post' action='researcher.jsp'>"
	 + "<input name='btnRemove' type='submit' value='Remove'  class='btn btn-danger'>"
	 + "<input name='resID' type='hidden' value='" + resID 
	 + "'>" + "</form></td></tr>"; 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the researcher."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	
	
	
	public String updateResearcher(String ID, String name, String address, String email, String desc)
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for updating."; 
	 } 
	 // create a prepared statement
	 String query = "UPDATE researcher as r SET r.resName = ?, r.resAddress = ?, r.resEmail = ?, r.resDesc = ? WHERE r.resID = ?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setString(1, name); 
	 preparedStmt.setString(2, address); 
	 preparedStmt.setString(3, email); 
	 preparedStmt.setString(4, desc); 
	 preparedStmt.setInt(5, Integer.parseInt(ID)); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Updated successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while updating the researcher."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	
	
	
	public String deleteResearcher(String resID) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for deleting."; } 
	 // create a prepared statement
	 String query = "delete from researcher where researcher.resID = ?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(resID)); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Deleted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while deleting the researcher."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }

}
