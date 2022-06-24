package com.hiro.blog.member.presentation;

import com.hiro.blog.acceptance.AcceptanceTest;
import com.hiro.blog.member.fixtures.MemberFixtures;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

class MemberControllerTest extends AcceptanceTest {

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

}