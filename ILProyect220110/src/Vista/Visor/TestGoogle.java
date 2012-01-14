package Vista.Visor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestGoogle {

	public static void main(String[] args) {
		try {
		URL google = new URL("http://images.google.com.uy/images?q=auto+rojo "); 
        HttpURLConnection connection = (HttpURLConnection) google.openConnection();
        connection.addRequestProperty("User-Agent", "Mozilla/4.76"); 
		BufferedReader in = new BufferedReader( 
		new InputStreamReader( 
				connection.getInputStream())); 

		String inputLine; 
		StringBuffer SB=new StringBuffer();
		
			while ((inputLine = in.readLine()) != null){
				//System.out.println(inputLine);
				SB.append(inputLine);
				SB.append("\n");
			}
			
		SplitImagenes(SB.toString());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	private static void SplitImagenes(String string) {
		String[] strings=string.split("imgurl=");
		for (String string2 : strings) {
			evalualinea(string2);
			//System.out.println(string2);
		}
		
	}

	private static void evalualinea(String string2) {
		if (string2.startsWith("http")){
			String[] aux=string2.split(".jpg");
			System.out.println(aux[0]+".jpg");
		}
		
	}
}
