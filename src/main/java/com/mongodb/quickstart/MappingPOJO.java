package com.mongodb.quickstart;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.quickstart.model.Grade;
import com.mongodb.quickstart.model.Score;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mongodb.quickstart.MongoDBSettings.getClientSettings;
import static java.util.Arrays.asList;

@Slf4j
public class MappingPOJO {

    private static final Random rand = new Random();
    private static final double studentId = 10012d;
    private static final double classId = 1d;

    public static void main(String[] args) {
        try (MongoClient mongoClient = MongoClients.create(getClientSettings())) {
            MongoDatabase sampleTrainingDB = mongoClient.getDatabase("sample_training");
            MongoCollection<Grade> grades = sampleTrainingDB.getCollection("grades", Grade.class);

            grades.insertOne(generateNewGrade(studentId, classId));
            log.info("One grade inserted for studentId {}.", studentId);

            Grade studentGrades = grades.find(Filters.eq("student_id", studentId)).first();
            log.info("Grades of student with id: {} was found:\t {}", studentId, studentGrades);

            List<Score> newScores = new ArrayList<>(studentGrades.getScores());
            newScores.add(new Score("exam", 42d));
            studentGrades.setScores(newScores);
            Document filterByGradeId = new Document("_id", studentGrades.getId());
            FindOneAndReplaceOptions returnDocAfterReplace = new FindOneAndReplaceOptions()
                    .returnDocument(ReturnDocument.AFTER);
            Grade updatedGrade = grades.findOneAndReplace(filterByGradeId, studentGrades, returnDocAfterReplace);
            log.info("Grade replaced:\t {}", updatedGrade);

            log.info(grades.deleteOne(filterByGradeId).toString());
        }
    }

    private static Grade generateNewGrade(double studentId, double classId) {
        List<Score> scores = asList(
                new Score("exam", rand.nextDouble() * 100),
                new Score("quiz", rand.nextDouble() * 100),
                new Score("homework", rand.nextDouble() * 100),
                new Score("homework", rand.nextDouble() * 100)
        );

        return Grade.builder()
                .id(new ObjectId())
                .studentId(studentId)
                .classId(classId)
                .scores(scores)
                .build();
    }
}
