/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.GoogleAPI;
import com.google.gson.Gson;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Fernando
 */
public class GoogleAPIControl {
    
    private Object ObjShowMaps;

   public double pegaLatitude(String rua, int numero, String bairro, String cidade){
       
       HttpExemplo http = new HttpExemplo();
        String key = "AIzaSyDZL37hvqGfIKRTun_KQ6uzt0SpuFRKJos";
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address="+rua.replace(" ", "%20")+","+numero+","+bairro.replace(" ", "%20")+","+cidade.replace(" ", "%20")+"+CA&key="+key+"";
        String json;
        try {
       json = http.sendGet(url);
       GoogleAPI j;
       Gson g = new Gson();
       String s = g.toJson(json);
   
        } catch (Exception ex) {
            Logger.getLogger(GoogleAPIControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        //JsonReader reader = new JsonReader();
       double lat = 0;
         
      
       
       
        return lat;
   } 
   
   
    
       public double pegaLongitude(String rua, int numero, String bairro, String cidade) throws MalformedURLException{
       
         String key = "AIzaSyDZL37hvqGfIKRTun_KQ6uzt0SpuFRKJos";
        String json = "https://maps.googleapis.com/maps/api/geocode/json?address="+rua.replace(" ", "%20")+","+numero+","+bairro.replace(" ", "%20")+","+cidade.replace(" ", "%20")+"+CA&key="+key+"";
        //JsonReader reader = new JsonReader();
       double lng = 0;
       System.out.print(json);
        GoogleAPI j;
        try {
         j = loadResultFromJSONGson(getJson(new URL(json)));
         lng = j.results[0].geometry.location.lng;
       
        System.out.println("Formatted Address: "+j.results[0].geometry.location.lng);
        
        } catch (MalformedURLException ex) {
            Logger.getLogger(GoogleAPIControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.print(lng);
        return lng;
   } 
    
    
    
    public static GoogleAPI loadResultFromJSONGson(String jsonString) {

        Gson gson = new Gson();

        GoogleAPI user = gson.fromJson(jsonString, GoogleAPI.class);

        return user;

    }

    public static String getJson(URL url){
        String s = "", s1="";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
            while ((s = br.readLine()) != null){
                s1+=s;
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return s1;
    }
    
    
        public void abrirMapa(String rua, String num, String bairro, String cidade, String cep, String lat, String lng)throws URISyntaxException, IOException{
         
        String key = "AIzaSyDZL37hvqGfIKRTun_KQ6uzt0SpuFRKJos";
        String url = "https://www.google.com.br/maps/place/"+rua.replace(" ", "%20")+",+"+num+"+"+bairro.replace(" ", "%20")+",+"+cidade.replace(" ", "%20")+"+-+PR,+85858-480/";
       
        String url2 = "https://www.google.com.br/maps/search/"+rua.replace(" ", "%20")+",+"+num+"+"+bairro.replace(" ", "%20")+",+"+cidade.replace(" ", "%20")+"+-+PR,+"+cep.replace(" ", "%20")+"/@"+lat+","+lng+",17z/data=!3m1!4b1";
        
        System.out.print(url);
        
                try {
                URI link = new URI(url2);
                    Desktop.getDesktop().browse(link);
            } catch (Exception e) {
                System.out.printf(null, e);
            }
            
            
        }
    
        
        public void abriMapa2(String url) throws URISyntaxException, IOException{
            
            try{
                URI link = new URI(url);
                Desktop.getDesktop().browse(link);
            }catch (Exception e){
                System.out.printf(null, e);
            }
            
            
        }
  
}
