/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Fernando
 */
public final class GoogleAPI {

    public final Result results[];
    public final String status;

    public GoogleAPI(Result[] results, String status) {
        this.results = results;
        this.status = status;
    }

   
    public static final class Result {

        public final Address_component address_components[];
        public final String formatted_address;
        public final Geometry geometry;
        public final String place_id;
        public final String[] types;

        public Result(Address_component[] address_components, String formatted_address, Geometry geometry, String place_id, String[] types) {
            this.address_components = address_components;
            this.formatted_address = formatted_address;
            this.geometry = geometry;
            this.place_id = place_id;
            this.types = types;
        }

        public static final class Address_component {

            public final String long_name;
            public final String short_name;
            public final String[] types;

            public Address_component(String long_name, String short_name, String[] types) {
                this.long_name = long_name;
                this.short_name = short_name;
                this.types = types;
            }
        }

        public static final class Geometry {

            public final Bounds bounds;
            public final Location location;
            public final String location_type;
            public final Viewport viewport;

            public Geometry(Bounds bounds, Location location, String location_type, Viewport viewport) {
                this.bounds = bounds;
                this.location = location;
                this.location_type = location_type;
                this.viewport = viewport;
            }

            public static final class Bounds {

                public final Northeast northeast;
                public final Southwest southwest;

                public Bounds(Northeast northeast, Southwest southwest) {
                    this.northeast = northeast;
                    this.southwest = southwest;
                }

                public static final class Northeast {

                    public final double lat;
                    public final double lng;

                    public Northeast(double lat, double lng) {
                        this.lat = lat;
                        this.lng = lng;
                    }
                }

                public static final class Southwest {

                    public final double lat;
                    public final double lng;

                    public Southwest(double lat, double lng) {
                        this.lat = lat;
                        this.lng = lng;
                    }
                }
            }

            public static final class Location {

                public final double lat;
                public final double lng;

                public Location(double lat, double lng) {
                    this.lat = lat;
                    this.lng = lng;
                }
            }

            public static final class Viewport {

                public final Northeast northeast;
                public final Southwest southwest;

                public Viewport(Northeast northeast, Southwest southwest) {
                    this.northeast = northeast;
                    this.southwest = southwest;
                }

                public static final class Northeast {

                    public final double lat;
                    public final double lng;

                    public Northeast(double lat, double lng) {
                        this.lat = lat;
                        this.lng = lng;
                    }
                }

                public static final class Southwest {

                    public final double lat;
                    public final double lng;

                    public Southwest(double lat, double lng) {
                        this.lat = lat;
                        this.lng = lng;
                    }
                }
            }
        }
    }
}
