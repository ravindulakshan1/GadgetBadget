package model;

import java.sql.*;

public class Customer {
	
	public Connection connect()
	{ 
	 Connection con = null; 
	 
	 try 
	 { 
	 Class.forName("com.mysql.jdbc.Driver"); 
	 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/customer", "root", ""); 
	 //For testing
	 System.out.print("Successfully connected"); 
	 } 
	 catch(Exception e) 
	 { 
	 e.printStackTrace(); 
	 } 
	 
	 return con; 
	}
	
	
	
	public String insertCustomer(String name, String addre, String phone, String emai)
	{ 
	 String output = ""; 
	try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 { 
	 return "Error while connecting to the database"; 
	 } 
	 // create a prepared statement
	 String query = " insert into customers(`customerID`,`customerName`,`address`,`phoneNumber`,`email`)" + " values (?, ?, ?, ?, ?)"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, 0); 
	 preparedStmt.setString(2, name); 
	 preparedStmt.setString(3, addre); 
	 preparedStmt.setString(4, phone); 
	 preparedStmt.setString(5, emai); 
	//execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Inserted successfully"; 
	 } 
	catch (Exception e) 
	 { 
	 output = "Error while inserting"; 
	 System.err.println(e.getMessage()); 
	 } 
	return output; 
	}

	
	
	
	public String readCustomers()
	{ 
	 String output = ""; 
	try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 { 
	 return "Error while connecting to the database for reading."; 
	 } 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Customer Name</th>" 
	 +"<th>Address</th><th>Phone Number</th>"
	 + "<th>Email</th>" 
	 + "<th>Update</th><th>Remove</th></tr>"; 
	 String query = "select * from customers"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String customerID = Integer.toString(rs.getInt("customerID")); 
	 String customerName = rs.getString("customerName"); 
	 String address = rs.getString("address"); 
	 String phoneNumber = rs.getString("phoneNumber"); 
	 String email = rs.getString("email"); 
	 // Add a row into the html table
	 output += "<tr><td>" + customerName + "</td>"; 
	 output += "<td>" + address + "</td>"; 
	 output += "<td>" + phoneNumber + "</td>"; 
	 output += "<td>" + email + "</td>";
	 // buttons
	 output += "<td><form method='post' action='Customers.jsp'>"
				+ "<input name='btnUpdate' "
				+ " type='submit' value='Update'>"
				+ "<input name='customerID' type='hidden' "
				+ " value='" + customerID + "'>" + "</form></td>"
				
				+ "<td><form method='post' action='Customers.jsp'>"
				+ "<input name='btnRemove'"
				+ " type='submit' value='Remove' class='btn btn-danger'>"
				+ "<input name='customerID' type='hidden' "
				+ " value='" + customerID + "'>" + "</form></td></tr>";
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	catch (Exception e) 
	 { 
	 output = "Error while reading the customers."; 
	 System.err.println(e.getMessage()); 
	 } 
	return output; 
	}
	
	
public String removeCustomer(String Id) {
		
		String output = "";
		
		try {
			
			Connection con = connect();
			
			if(con == null) {
				return "Error while connecting to the database for removing";
			}
			
			//create a prepared statement
			String query = " delete from customers where customerID = " + " ? ";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			//binding values
			preparedStmt.setString(1,Id);
			
			
			//execute the statement
			preparedStmt.executeUpdate();
			con.close();
			
			output = "Removed successfully";
		}
		catch(Exception e) {
			
			output = "Error while removing";
			System.err.println(e.getMessage());
			
		}
		
		return output;
		
	}
	

	public String updateCustomer(String ID, String name, String addre, String phone, String emai)
	{ 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for updating."; } 
	 // create a prepared statement
	 String query = "UPDATE customers SET customerName=?,address=?,phoneNumber=?,email=? WHERE customerID=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setString(1, name); 
	 preparedStmt.setString(2, addre); 
	 preparedStmt.setString(3, phone);  
	 preparedStmt.setString(4, emai); 
	 preparedStmt.setInt(5, Integer.parseInt(ID)); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Updated successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while updating the customers."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 

}

