package com.mongodb.quickstart;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import org.bson.json.JsonWriterSettings;

public class MongoDBSettings {
    public static MongoClientSettings getClientSettings() {
        String connectionValue = "mongodb+srv://voprosnik:87732284wer@zenovcluster.6iwihdx.mongodb.net/?retryWrites=true&w=majority";
        ConnectionString connectionString = new ConnectionString(connectionValue);
        return MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
    }

    public static JsonWriterSettings getPrettyPrintSettings() {
        return JsonWriterSettings.builder().indent(true).build();
    }
}
