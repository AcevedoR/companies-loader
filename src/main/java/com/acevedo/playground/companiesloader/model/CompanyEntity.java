package com.acevedo.playground.companiesloader.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "companies")
@Data
public class CompanyEntity {

    @Id
    @Field("_id")
    private ObjectId id;

    private String name;

    @Field("total_money_raised")
    private String totalMoneyRaised;
}
