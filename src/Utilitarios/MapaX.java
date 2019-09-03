/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitarios;

/**
 *
 * @author Fernando
 */

import com.teamdev.jxmaps.GeocoderRequest;
import com.teamdev.jxmaps.GeocoderCallback;
import com.teamdev.jxmaps.Marker;
import com.teamdev.jxmaps.MapViewOptions;
import com.teamdev.jxmaps.InfoWindow;
import com.teamdev.jxmaps.GeocoderStatus;
import com.teamdev.jxmaps.GeocoderResult;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapOptions;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.MapTypeControlOptions;
import com.teamdev.jxmaps.swing.MapView;
import java.net.URL;
import javax.swing.JOptionPane;


public class MapaX extends MapView {

    public MapaX(MapViewOptions options, double lat, double lng) {
      
        super(options);
        setOnMapReadyHandler(new MapReadyHandler() {
            @Override
            public void onMapReady(MapStatus status) {
             
                if (status == MapStatus.MAP_STATUS_OK) {
                    final Map map = getMap();
                    map.setZoom(17);
                    GeocoderRequest request = new GeocoderRequest(map);
                     request.setLocation(new LatLng(lat, lng));
                    // request.setAddress("Kharkiv, UA");
                                                 

                    getServices().getGeocoder().geocode(request, new GeocoderCallback(map) {
                        @Override
                        public void onComplete(GeocoderResult[] result, GeocoderStatus status) {
                            if (status == GeocoderStatus.OK) {
                                map.setCenter(result[0].getGeometry().getLocation());
                                Marker marker = new Marker(map);
                                LatLng url = result[0].getGeometry().getLocation();
                                System.out.print(url);
                                marker.setPosition(result[0].getGeometry().getLocation());

                                final InfoWindow window = new InfoWindow(map);
                                window.setContent("O local é aqui!");
                                window.open(map, marker);
                                
                            }
                        }
                    });
                    
                }
            }
        });
    }

    
    

}
