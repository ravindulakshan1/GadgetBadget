package service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import controller.OrderController;
import model.Order;

@Path("/Orders")
public class OrderService {
	OrderController orderController = new OrderController();

	  //Read Order List
	  	@GET
	  	@Path("/")
	  	@Produces(MediaType.TEXT_HTML)
	  	public String readOrders() {
	  		return orderController.readOrders();
	  	}
	  	


	    @POST
	    @Path("/")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.TEXT_PLAIN)
	    public String insertOrder(String orderJson) {
	    	
	        Order order = new Order(orderJson);
	        String output = orderController.insertOrder(order);
	        
	        return output;
	    }

	    @PUT
	    @Path("/")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.TEXT_PLAIN)
	    public String updateOrder(String orderJson) {

	        Order order = new Order(orderJson);
	        String output = orderController.updateOrder(order); 
	        
	        return output;
	    }

	    @DELETE
	    @Path("/")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.TEXT_PLAIN)
	    public String removeOrder(String orderData) {

			JsonObject OrderObject = new JsonParser().parse(orderData).getAsJsonObject();

			String itemId = OrderObject.get("ItemID").getAsString();
	        String output = orderController.removeOrder(itemId);
	        
	        return output;
	    }
}
