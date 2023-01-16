package de.berlin.htw;

import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.string.StringCommands;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.inject.Inject;

@QuarkusTest
class BasketResourceTest {

    @Inject
    protected RedisDataSource redisDS;






}
