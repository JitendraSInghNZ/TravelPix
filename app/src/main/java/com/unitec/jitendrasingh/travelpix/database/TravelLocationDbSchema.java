package com.unitec.jitendrasingh.travelpix.database;

/**
 * Created by jitu on 17/06/16.
 * Creates schema for the columns of the database
 */
public class TravelLocationDbSchema {

    public static final class TravelLocationTable {
        public static final String NAME = "travel_locations";

        public static final class Columns {
            public static final String UUID = "uuid";
            public static final String DESCRIPTION = "description";
            public static final String DATE = "date";
            public static final String VISIT_AGAIN="visit_again";
            public static final String RATING = "rating";
        }
    }


}
