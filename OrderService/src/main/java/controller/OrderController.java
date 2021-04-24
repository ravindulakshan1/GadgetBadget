package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import dbhandler.DBConnection;
import model.Order;

public class OrderController {
	public String insertOrder(Order order) {

		String output = "";
		try {
			Connection con = DBConnection.connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = "insert into Orders" + "(`ItemID`,`Iname`,`Icode`,`Iprice`,`Idesc`)"
					+ " values (?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, order.getItemId());
			preparedStmt.setString(2, order.getItemName());
			preparedStmt.setString(3, order.getItemCode());
			preparedStmt.setDouble(4, order.getItemPrice());
			preparedStmt.setString(5, order.getItemDesc());

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted Order successfully";
		} catch (Exception e) {
			output = "Error while inserting order";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readOrders() {
		String output = "";
		try {
			Connection con = DBConnection.connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed

			output = "<table border=\"1\">" + "<tr>" + "<th>Item ID</th>" + "<th>Item Name</th>"
					+ "<th>Item Code</th>" + "<th>Item Price</th>" + "<th>Description</th>" + "<th>Update</th>" + "<th>Remove</th>";

			String query = "select * from Orders";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
											
				String itemId = Integer.toString(rs.getInt("ItemID"));
				String itemName = rs.getString("Iname");
				String itemCode = rs.getString("Icode");
				Double itemPrice = rs.getDouble("Iprice");
				String itemDesc = rs.getString("Idesc");

				// Add into the html table
				output += "<tr>" + "<td>" + itemId + "</td>";
				output += "<td>" + itemName + "</td>";
				output += "<td>" + itemCode + "</td>";
				output += "<td>" + itemPrice + "</td>";
				output += "<td>" + itemDesc + "</td>";
				
				//buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='order.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						+ "<input name='itemId' type='hidden' value'" + itemId + "'>" + "</form></td></tr>";
			}

			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the orders.";
			System.out.println(e.toString());
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateOrder(Order order) {

		String output = "";

		try {
			Connection con = DBConnection.connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE Orders SET Iname = ?, Icode = ?,"
					+ "Iprice = ?, Idesc = ? WHERE ItemID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, order.getItemName());
			preparedStmt.setString(2, order.getItemCode());
			preparedStmt.setDouble(3, order.getItemPrice());
			preparedStmt.setString(4, order.getItemDesc());
			preparedStmt.setInt(5, order.getItemId());

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String removeOrder(String itemId) {

		String output = "";
		try {
			Connection con = DBConnection.connect();

			if (con == null) {

				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from Orders where ItemID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(itemId));

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted order successfully";

		} catch (Exception e) {

			output = "Error while deleting the order.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
