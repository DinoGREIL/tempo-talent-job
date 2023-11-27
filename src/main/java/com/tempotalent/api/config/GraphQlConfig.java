package com.tempotalent.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;

import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;

@Configuration
public class GraphQlConfig {
     @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer(ObjectMapper objectMapper) {
        GraphQLScalarType jsonScalarType = GraphQLScalarType.newScalar()
                .name("JSON")
                .description("A JSON scalar")
                .coercing(new JsonNodeCoercing(objectMapper))
                .build();

        return wiringBuilder -> wiringBuilder
                .scalar(jsonScalarType);
    }
}