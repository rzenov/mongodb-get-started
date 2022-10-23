package com.mongodb.quickstart;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.json.JsonWriterSettings;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.quickstart.MongoDBSettings.getClientSettings;

public class Connection {
    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create(getClientSettings());
        try {
            MongoDatabase sampleTrainingDB = mongoClient.getDatabase("sample_training");
            MongoCollection<Document> grades = sampleTrainingDB.getCollection("grades");
            Document student = grades.find(Filters.eq("student_id", 10000d)).first();
            System.out.println(student.toJson(JsonWriterSettings.builder().indent(true).build()));

            List<Document> students = grades.find(Filters.gte("student_id", 10000d)).into(new ArrayList<>());
            students.forEach(st -> System.out.println(st.toJson()));
        } catch (Exception exception) {
            System.out.println("Something went wrong: " + exception.getMessage());
        }


    }
}
