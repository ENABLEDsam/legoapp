package threads;

// Imporit
import java.io.OutputStream;


import java.net.HttpURLConnection;
import java.net.URL;


import data.*;

public class SendData implements Runnable {

	// urli johon data lähetetään
	URL url = null;

	// HTTP-yhteys
	HttpURLConnection conn = null;

	// JSON data
	String json = null;

	@Override
	public void run() {

		// Pyörii nii kauan kun arvo on 1
		while (Robot.getRun() == 1) {

			try {

				
                // Venataa pari sekkaa ettei hajoo
				Thread.sleep(2000);
			
            }
			catch (InterruptedException e) {

				
                // Tulostetaan mahollinen virhe konsoliin
				e.printStackTrace();
			
            }

			try {

				// Urli johon data lähetetään
				url = new URL("http://10.82.95.23:8080/rest/lego/setvalues");

				
                
                // Avataa HTTP-yhteys
				conn = (HttpURLConnection)url.openConnection();

				
                // Määritetään requeti POSTiksi
				conn.setRequestMethod("POST");

				
                
                
                // Määritetään että data on JSON-muodossa
				conn.setRequestProperty("Content-Type","application/json");

				// Määritetään että dataa lähetetään backendille
				conn.setDoOutput(true);

				
                
                
                
                
                
                // Rakennetaan JSON data Stringiksi
				json = "{"
						+ "\"id\":1,"
						+ "\"run\":" + Robot.getRun() + ","
						+ "\"speed\":" + Robot.getSpeed() + ","
						+ "\"turn\":" + Robot.getTurn()
						+ "}";

				
                
                
                
                // Lähetetään data backendille
				OutputStream os = null;

				
                
                try {

					// Avataan OutputStream 
					os = conn.getOutputStream();

					
                    
                    
                    // Kirjoitetaan JSON data
					os.write(json.getBytes());

					// Flushataan jotta data varmasti lähtee
					os.flush();

					
                    
                    
                    
                    // Suljetaan
					os.close();

					
                    
                    
                    
                    // Varmistetaan että data on lähetetty
					conn.getResponseCode();
				
                
                
                
                
                
                
                
                
                
                
                }
				catch (Exception e) {

					
                    
                    
                    // Tulostetaan mahollinen virhe konsoliin
					System.out.println("Exception conn.getOutputStream()");

					e.printStackTrace();

					System.out.println("Cannot send OutputStream!");
				}

				
                
                
                // Suljetaan yhteys
				conn.disconnect();
			
            
            
            
            
            }
			catch(Exception e) {

				// Tulostetaan muut maholliset virheet konsoliin
				e.printStackTrace();

				System.out.println("Some problem!");
			
            
            
            
            
            }
		
        
        
        
        
        
        
        }
	
    
    
    
    
    
    
    
   
   
    }



    
}