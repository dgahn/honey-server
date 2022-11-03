package com.kakaopay.honey.util

import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@AutoConfigureMockMvc
abstract class SpringMockMvcTestSupport {
    @Autowired
    protected lateinit var mockMvc: MockMvc

    protected inline fun <reified T> mockMvcGetTest(
        uri: URI,
        expectedResponseDto: String,
        customHeaders: HttpHeaders = HttpHeaders.EMPTY,
        status: HttpStatus = HttpStatus.OK
    ) {
        mockMvc.perform(
            MockMvcRequestBuilders.get(uri.value)
                .accept(MediaType.APPLICATION_JSON)
                .headers(customHeaders)
        )
            .andExpect { result -> result.response.status shouldBe status.value() }
            .andExpect(MockMvcResultMatchers.content().string(expectedResponseDto.toJson()))
    }

    protected inline fun <reified T, reified K> mockMvcPostTest(
        uri: URI,
        requestDto: T,
        expectedResponseDto: K,
        customHeaders: HttpHeaders = HttpHeaders.EMPTY,
        status: HttpStatus = HttpStatus.OK
    ) {
        mockMvc.perform(
            MockMvcRequestBuilders.post(uri.value)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .headers(customHeaders)
                .content(requestDto.toJson())
        )
            .andExpect(MockMvcResultMatchers.content().string(expectedResponseDto.toJson()))
            .andExpect { result -> result.response.status shouldBe status.value() }
    }
}
