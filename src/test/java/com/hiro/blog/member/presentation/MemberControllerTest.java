package com.hiro.blog.member.presentation;

import com.hiro.blog.acceptance.AcceptanceTest;
import com.hiro.blog.member.fixtures.MemberFixtures;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberControllerTest extends AcceptanceTest {

    @DisplayName("회원 가입.")
    @Test
    void sign_up() {
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(MemberFixtures.memberRequest())
                .when()
                .post("/members")
                .then().log().all()
                .extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @DisplayName("로그인")
    @Test
    void form_login() {
        //회원 가입 요청
        회원가입();
        String sessionId = 로그인();

        assertThat(sessionId).isNotBlank();
    }

    public static void 회원가입() {
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(MemberFixtures.memberRequest())
                .when()
                .post("/members")
                .then().log().all()
                .extract();
    }

    public static String 로그인() {
        회원가입();
        return RestAssured.given().log().all()
                .contentType(ContentType.URLENC)
                .formParams("username", "my username")
                .formParams("password", "1234")
                .when()
                .post("/login")
                .then().log().all()
                .extract().sessionId();
    }
}