package com;

import model.Customer;


//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/Customers") 
public class CustomerService {

	
	Customer customerObj = new Customer();
	private String customer; 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readCustomers() 
	 { 
	 return  customerObj.readCustomers(); 
	 } 
	
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertCustomer(@FormParam("customerName") String customerName, 
				@FormParam("address") String address, 
				@FormParam("phoneNumber") String phoneNumber, 
				@FormParam("email") String email) 
	{ 
		String output = customerObj.insertCustomer(customerName, address, phoneNumber, email); 
		return output; 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateCustomer(String customerData) 
	{ 
//Convert the input string to a JSON object 
		JsonObject customerObject = new JsonParser().parse(customerData).getAsJsonObject(); 
//Read the values from the JSON object
		String customerID = customerObject.get("customerID").getAsString(); 
		String customerName = customerObject.get("customerName").getAsString(); 
		String address = customerObject.get("address").getAsString(); 
		String phoneNumber = customerObject.get("phoneNumber").getAsString(); 
		String email = customerObject.get("email").getAsString(); 
		String output = customerObj.updateCustomer(customerID, customerName, address, phoneNumber, email); 
		return output; 
	}
	
	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String removeCustomer(String customerData) 
	{ 
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(customerData, "", Parser.xmlParser()); 
 
		//Read the value from the element <itemID>
		String customerID = doc.select("customerID").text(); 
		String output = customerObj.removeCustomer(customerID); 
		return output; 
}
	
}
