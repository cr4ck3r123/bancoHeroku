/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitarios;

import com.teamdev.jxmaps.swing.MapView;
import javax.swing.JFrame;
import com.teamdev.jxmaps.*;
import java.awt.BorderLayout;

/**
 *
 * @author Fernando
 */

public class MapaJ extends MapView {

    private Map map;
      JFrame frame = new JFrame();
    public MapaJ(String nName) {
     
        setOnMapReadyHandler(new MapReadyHandler() {

            @Override
            public void onMapReady(MapStatus status) {
        
            if(status == MapStatus.MAP_STATUS_OK){
                
                map=getMap();
                
                MapOptions mapOptions = new MapOptions();
                MapTypeControlOptions controlOptions = new MapTypeControlOptions();
                mapOptions.setMapTypeControlOptions(controlOptions);
                
                map.setOptions(mapOptions);
                map.setCenter(new LatLng(41.8316578, -87.6374727));
                map.setZoom(17);
         
                Marker marker = new Marker(map);
                marker.setPosition(map.getCenter());
                
            }

            }
        });
        
        frame.add(this, BorderLayout.CENTER);
        frame.setSize(700, 500);
        frame.setVisible(true);
      
        
        
        
    }

    public static void main(String[] args) {
        
              MapaJ mapaJ = new MapaJ("teste");
             
    
    }

}
