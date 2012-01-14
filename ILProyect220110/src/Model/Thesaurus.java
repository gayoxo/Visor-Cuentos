package Model;

import java.io.BufferedReader; 
import java.net.HttpURLConnection; 
import java.net.URL; 
import java.net.URLEncoder; 
import java.util.ArrayList;

import org.json.simple.*;

public class Thesaurus {
	
	 final static String endpoint = "http://thesaurus.altervista.org/thesaurus/v1";
	 final static String language = "es_ES";
	 final static String key="Lvw8gILIAOTuYpcz4PDH";
	 final static String output="json";
	
	public static void main(String[] args) { 
		// NOTE: replace test_only with your own key 
		//KEY:Lvw8gILIAOTuYpcz4PDH
		    //new SendRequest("Paz", "es_ES", "Lvw8gILIAOTuYpcz4PDH", "json"); 
		   ArrayList<String> Show=Thesaurus.getSinonimos("dentadura");
		   System.out.println("Resultado");
		   StringBuffer SB=new StringBuffer();
		   for (String string : Show) {
			SB.append(string + "|");
		}
		   System.out.println(SB);
		  } 
	
	
	public static ArrayList<String> getSinonimos(String word)
	{
		 try {
			 System.out.println("");
			 System.out.println("Palabra: "+word) ;
			 ArrayList<String> Salida=new ArrayList<String>();
		      URL serverAddress = new URL(endpoint + "?word="+URLEncoder.encode(word, "UTF-8")+"&language="+language+"&key="+key+"&output="+output); 
		      HttpURLConnection connection = (HttpURLConnection)serverAddress.openConnection(); 
		      connection.connect(); 
		      int rc = connection.getResponseCode(); 
		      if (rc == 200) { 
		        String line = null; 
		        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(connection.getInputStream())); 
		        StringBuilder sb = new StringBuilder(); 
		        while ((line = br.readLine()) != null) 
		          sb.append(line + '\n'); 
		        JSONObject obj = (JSONObject) JSONValue.parse(sb.toString()); 
		        JSONArray array = (JSONArray)obj.get("response"); 
		        for (int i=0; i < array.size(); i++) { 
		          JSONObject list = (JSONObject) ((JSONObject)array.get(i)).get("list"); 
		          String S=(String) list.get("synonyms");
		          String[] SPlus=S.split("\\|");
		          System.out.println(list.get("category")+":"+list.get("synonyms")); 
		          for (String string : SPlus) {
		        	  string=string.replace(' ', '_');
					Salida.add(string);
				}
		         
		        }
		        connection.disconnect(); 
		        return Salida;
		      } else  System.out.println("HTTP error:"+rc); 

		      connection.disconnect(); 
		    } catch (java.net.MalformedURLException e) { 
		      e.printStackTrace(); 
		    } catch (java.net.ProtocolException e) { 
		      e.printStackTrace(); 
		    } catch (java.io.IOException e) { 
		      e.printStackTrace(); 
		    }
		 return new ArrayList<String>();
	}
		} // end of Thesaurus 

class SendRequest { 
		  final String endpoint = "http://thesaurus.altervista.org/thesaurus/v1"; 

		  public SendRequest(String word, String language, String key, String output) { 
		    try { 
		      URL serverAddress = new URL(endpoint + "?word="+URLEncoder.encode(word, "UTF-8")+"&language="+language+"&key="+key+"&output="+output); 
		      HttpURLConnection connection = (HttpURLConnection)serverAddress.openConnection(); 
		      connection.connect(); 
		      int rc = connection.getResponseCode(); 
		      if (rc == 200) { 
		        String line = null; 
		        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(connection.getInputStream())); 
		        StringBuilder sb = new StringBuilder(); 
		        while ((line = br.readLine()) != null) 
		          sb.append(line + '\n'); 
		        JSONObject obj = (JSONObject) JSONValue.parse(sb.toString()); 
		        JSONArray array = (JSONArray)obj.get("response"); 
		        for (int i=0; i < array.size(); i++) { 
		          JSONObject list = (JSONObject) ((JSONObject)array.get(i)).get("list"); 
		          String S=(String) list.get("synonyms");
		          String[] SPlus=S.split("\\|");
		          System.out.println(list.get("category")+":"+list.get("synonyms")); 
		        } 
		      } else System.out.println("HTTP error:"+rc); 
		      connection.disconnect(); 
		    } catch (java.net.MalformedURLException e) { 
		      e.printStackTrace(); 
		    } catch (java.net.ProtocolException e) { 
		      e.printStackTrace(); 
		    } catch (java.io.IOException e) { 
		      e.printStackTrace(); 
		    } 
		  } 
		} // end of SendRequest

		