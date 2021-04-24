package model;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Order {
	private int itemId;
	private String itemName;
	private String itemCode;
	private double itemPrice;
	private String itemDesc;
	
	public Order() {}
	
	public Order(String jsonOrderDoc){

        JsonObject itemObject = new JsonParser().parse(jsonOrderDoc).getAsJsonObject();

        if (itemObject.get("ItemID") != null) {
            this.itemId = itemObject.get("ItemID").getAsInt();
        }

        this.itemName  = itemObject.get("Iname").getAsString();
        this.itemCode  = itemObject.get("Icode").getAsString();
        this.itemPrice = itemObject.get("Iprice").getAsDouble();
        this.itemDesc  = itemObject.get("Idesc").getAsString();     
        
        //System.out.println(jsonOrderDoc);
    }

	public Order(int itemId, String itemName, String itemCode, double itemPrice, String itemDesc) {
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemCode = itemCode;
		this.itemPrice = itemPrice;
		this.itemDesc = itemDesc;
	}
	
	public Order( String itemName, String itemCode, double itemPrice, String itemDesc) {
		this.itemName = itemName;
		this.itemCode = itemCode;
		this.itemPrice = itemPrice;
		this.itemDesc = itemDesc;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
}
