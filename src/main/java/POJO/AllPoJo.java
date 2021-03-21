package POJO;

import java.util.Map;

import com.google.gson.Gson;

public class AllPoJo {
	

	public String differentItemType(Map<String,String> data) {
			
		ItemType upDateId = new ItemType();
    	 
		 upDateId.setItemType(data.get("itemType"));
		 
		 Gson Josnbody = new Gson();
		 return Josnbody.toJson(upDateId);		
	}

}
