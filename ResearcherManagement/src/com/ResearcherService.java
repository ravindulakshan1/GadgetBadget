package com;
import model.Researcher;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;




@Path("/Reasearchers")
public class ResearcherService {
	
	Researcher rObj = new Researcher(); 
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String readResearchers() 
	 { 
		return rObj.readResearchers(); 
	 } 
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertResearcher(@FormParam("resName") String resName, 
	 @FormParam("resAddress") String resAddress, 
	 @FormParam("resEmail") String resEmail, 
	 @FormParam("resDesc") String resDesc) 
	{ 
	 String output = rObj.insertResearcher(resName, resAddress, resEmail, resDesc); 
	return output; 
	}

	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateResearcher(String resData) 
	{ 
	//Convert the input string to a JSON object 
	 JsonObject rObject = new JsonParser().parse(resData).getAsJsonObject(); 
	//Read the values from the JSON object
	 String resID = rObject.get("resID").getAsString(); 
	 String resName = rObject.get("resName").getAsString(); 
	 String resAddress = rObject.get("resAddress").getAsString(); 
	 String resEmail = rObject.get("resEmail").getAsString(); 
	 String resDesc = rObject.get("resDesc").getAsString(); 
	 String output = rObj.updateResearcher(resID, resName, resAddress, resEmail, resDesc); 
	return output; 
	}
	

@DELETE
@Path("/") 
@Consumes(MediaType.APPLICATION_XML) 
@Produces(MediaType.TEXT_PLAIN) 
public String deleteResearcher(String resData) 
{ 
//Convert the input string to an XML document
 Document doc = Jsoup.parse(resData, "", Parser.xmlParser()); 
 
//Read the value from the element <reID>
 String resID = doc.select("resID").text(); 
 String output = rObj.deleteResearcher(resID); 
return output; 
}

	
	

}
