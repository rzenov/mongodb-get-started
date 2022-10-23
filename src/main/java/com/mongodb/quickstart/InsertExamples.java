package com.mongodb.quickstart;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertManyOptions;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.mongodb.quickstart.MongoDBSettings.getClientSettings;
import static java.util.Arrays.asList;

public class InsertExamples {

    private static final Random rand = new Random();

    public static void main(String[] args) {
        try (MongoClient mongoClient = MongoClients.create(getClientSettings())) {

            MongoDatabase sampleTrainingDB = mongoClient.getDatabase("sample_training");
            MongoCollection<Document> gradesCollection = sampleTrainingDB.getCollection("grades");

            insertOneDocument(gradesCollection);
            insertManyDocuments(gradesCollection);
        }
    }

    private static void insertOneDocument(MongoCollection<Document> gradesCollection) {
        gradesCollection.insertOne(generateNewGrade(10000d, 1d));
        System.out.println("One grade inserted for studentId 10000.");
    }

    private static void insertManyDocuments(MongoCollection<Document> gradesCollection) {
        List<Document> grades = IntStream.range(1, 10)
                .mapToObj(id -> generateNewGrade(10000d + id, id))
                .collect(Collectors.toList());

        gradesCollection.insertMany(grades, new InsertManyOptions().ordered(false));
        System.out.println("Ten grades inserted for studentId 10001.");
    }

    private static Document generateNewGrade(double studentId, double classId) {
        List<Document> scores = asList(
                new Document("type", "exam").append("score", rand.nextDouble() * 100),
                new Document("type", "quiz").append("score", rand.nextDouble() * 100),
                new Document("type", "homework").append("score", rand.nextDouble() * 100),
                new Document("type", "homework").append("score", rand.nextDouble() * 100)
        );
        return new Document("_id", new ObjectId())
                .append("student_id", studentId)
                .append("class_id", classId)
                .append("scores", scores);
    }
}
