package com.springbootawspractice.main.web;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = HelloController.class)
class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void hello가_리턴된다() throws Exception {

        String hello = "hello";


        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto가_리턴된다() throws Exception { // success 테스트도 하고... fail 테스트도 해야하는데..
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                .param("name", name)
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));

    }

    //enh: mvc.perform 나누기, given,when,then 나누기, 노션의 Inside vs Server-Side 참고
    @Test
    public void helloDto가_리턴된다_V2() throws Exception{

        //given
        String name = "hello";
        int amount = 1000;

        //when
        MockHttpServletResponse response = mvc.perform(get("/hello/dto")
                .param("name", name)
                .param("amount", String.valueOf(amount))).andReturn().getResponse();

        JSONObject resJson = new JSONObject(response.getContentAsString());

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(resJson.getString("name")).isEqualTo(name);
        assertThat(resJson.getInt("amount")).isEqualTo(amount);
    }



}