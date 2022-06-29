package com.hiro.blog.post.presentation;

import com.hiro.blog.acceptance.AcceptanceTest;
import com.hiro.blog.post.fixtures.PostFixtures;
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

    @DisplayName("게시글 수정")
    @Test
    void modify() {
        String sessionId = 로그인();
        String url = 게시글_등록(sessionId);

        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .sessionId(sessionId)
                .body(PostFixtures.modifyPostRequest())
                .when()
                .put(url)
                .then().log().all()
                .extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @DisplayName("게시글 삭제.")
    @Test
    void delete() {
        String sessionId = 로그인();
        String url = 게시글_등록(sessionId);

        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .sessionId(sessionId)
                .body(PostFixtures.modifyPostRequest())
                .when()
                .delete(url)
                .then().log().all()
                .extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    private String 게시글_등록(String sessionId) {
        ExtractableResponse<Response> response = RestAssured.given().log().all()
                .sessionId(sessionId)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(new WritePostRequest("new title", "new contents"))
                .when()
                .post("/posts")
                .then().log().all()
                .extract();

        return response.header("Location");
    }

}