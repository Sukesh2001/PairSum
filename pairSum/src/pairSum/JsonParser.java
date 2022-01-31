package pairSum;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

import org.json.simple.parser.JSONParser;

public class JsonParser {

private static String readUrl(String urlString) throws Exception {
    BufferedReader reader = null;
    try {
        URL url = new URL(urlString);
        reader = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuffer buffer = new StringBuffer();
        int read;
        char[] chars = new char[1024];
        while ((read = reader.read(chars)) != -1)
            buffer.append(chars, 0, read); 

        return buffer.toString();
    } 
    finally {
        if (reader != null)
            reader.close();
    }
}

  public  List<String> JsonStringParser(String url) throws IOException, JSONException {
      JSONObject json;
      try {
        json = new JSONObject(readUrl(url));
        JSONObject pid = json.getJSONObject("query").getJSONObject("pages");
        
        Iterator<String> keys = pid.keys();
        String pageid = "";
        while(keys.hasNext()) {
            String key = keys.next();
            pageid=key;
        }
        
        String s = json.getJSONObject("query").getJSONObject("pages").getJSONObject(pageid).getJSONArray("revisions").getJSONObject(0).getString("*");
        
        String[] lines = s.split("\n\n");
        
        String seealso="";
        for(int line=0;line<lines.length;line++) {
        	lines[line]=lines[line].strip();
        	if(lines[line].startsWith("==See also")) {
        		seealso=lines[line];
        	}
        }
        String[] see_also=seealso.split("\n");
        List<String> see_also_links = new ArrayList<String>();
        for(int i=1;i<see_also.length;i++) {
        	see_also[i]=see_also[i].replace("*[[","");
        	see_also[i]=see_also[i].replace("]]","");
        	see_also[i]=see_also[i].replace("(","");
        	see_also[i]=see_also[i].replace(")","");
        	see_also[i]=see_also[i].replace(" ","_");
        	see_also_links.add(see_also[i]);
        }
        
        String url_final = "https://en.wikipedia.org/wiki/";
        List<String> final_links = new ArrayList<String>();
        final_links.add(pageid);
        for(int i=0;i<see_also_links.size();i++) {
        	final_links.add(url_final+see_also_links.get(i));
        }
        return final_links; 
    }
    
    catch (Exception e) {
        e.printStackTrace();
    }
	return null;
       
  }
  
  public static void main(String args[]) {
	  String url = "https://en.wikipedia.org/w/api.php?format=json&action=query&titles=";
	  JsonParser jp = new JsonParser();
	  try {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Title");
		String title = sc.nextLine();
		List<String> links = jp.JsonStringParser(url+title+"&prop=revisions&rvprop=content");
		System.out.println("Page Id : "+links.get(0));
		System.out.println("See Also Links");
		for(int i=1;i<links.size();i++) {
			System.out.println(links.get(i));
		}
	} 
	catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
}

