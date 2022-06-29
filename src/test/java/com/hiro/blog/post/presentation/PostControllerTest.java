package com.hiro.blog.post.presentation;

import com.hiro.blog.acceptance.AcceptanceTest;
import com.hiro.blog.post.presentation.dtos.WritePostRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static com.hiro.blog.member.presentation.MemberControllerTest.로그인;
import static org.assertj.core.api.Assertions.assertThat;

class PostControllerTest extends AcceptanceTest {

    @DisplayName("게시글 작성.")
    @Test
    void write() {
        String sessionId = 로그인();

        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .sessionId(sessionId)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(new WritePostRequest("new title", "new contents"))
                .when()
                .post("/posts")
                .then().log().all()
                .extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

}