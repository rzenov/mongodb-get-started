package com.mongodb.quickstart;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.json.JsonWriterSettings;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoDBSettings {
    public static MongoClientSettings getClientSettings() {
        String connectionValue = "mongodb+srv://voprosnik:87732284wer@cluster0.kkjrxxq.mongodb.net/?retryWrites=true&w=majority";
        ConnectionString connectionString = new ConnectionString(connectionValue);
        return MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .codecRegistry(fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), getPOJOCodecRegistry()))
                .build();
    }

    public static JsonWriterSettings getPrettyPrintSettings() {
        return JsonWriterSettings.builder().indent(true).build();
    }

    public static CodecRegistry getPOJOCodecRegistry() {
        return fromProviders(PojoCodecProvider.builder().automatic(true).build());
    }
}
