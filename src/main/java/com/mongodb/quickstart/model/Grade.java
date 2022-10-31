package com.mongodb.quickstart.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "scores")
@EqualsAndHashCode(exclude = "scores")
public class Grade {
    private ObjectId id;
    @BsonProperty(value = "student_id")
    private Double studentId;
    @BsonProperty(value = "class_id")
    private Double classId;
    private List<Score> scores;
}
