package com.mongodb.quickstart.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonProperty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Score {
    @BsonProperty(value = "type")
    private String type;
    @BsonProperty(value = "score")
    private Double score;
}
