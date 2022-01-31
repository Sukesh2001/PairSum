package pairSum;


import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Jsonprac{
	
	public static void main(String [] args){
		
			
			String inline = "";
			try
			{
				URL url = new URL("https://en.wikipedia.org/w/api.php?format=json&action=query&titles=SMALL&prop=revisions&rvprop=content");
				//Parse URL into HttpURLConnection in order to open the connection in order to get the JSON data
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				//Set the request to GET or POST as per the requirements
				conn.setRequestMethod("GET");
				//Use the connect method to create the connection bridge
				conn.connect();
				//Get the response status of the Rest API
				int responsecode = conn.getResponseCode();
				System.out.println("Response code is: " +responsecode);
				
				//Iterating condition to if response code is not 200 then throw a runtime exception
				//else continue the actual process of getting the JSON data
				if(responsecode != 200)
					throw new RuntimeException("HttpResponseCode: " +responsecode);
				else
				{
					//Scanner functionality will read the JSON data from the stream
					Scanner sc = new Scanner(url.openStream());
					while(sc.hasNext())
					{
						inline+=sc.nextLine();
					}
					//Close the stream when reading the data has been finished
					sc.close();
				}
				JSONParser parse = new JSONParser();
				JSONObject jobj = (JSONObject)parse.parse(inline);
				
					
				JSONObject queries = ((JSONObject)((JSONObject)jobj.get("query")).get("pages"));
//				Map pages = ((Map)queries.get("pages"));

				Set pageid = queries.keySet();
		        System.out.println(pageid);
		        //System.out.println(queries.get("1808130"));
		        JSONObject links = ((JSONObject)queries.get(pageid));
		        //JSONObject links = ((JSONObject)queries.get(pageid.toString()));
		        //System.out.println(links);
		        
		        
				conn.disconnect();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

