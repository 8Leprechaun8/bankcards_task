//package com.example.bankcards.controller;
//
//import com.example.bankcards.dto.CardDto;
//import com.example.bankcards.dto.UserDto;
//import com.example.bankcards.entity.CardStatus;
//import com.example.bankcards.entity.Role;
//import com.example.bankcards.security.JwtAuthenticationFilter;
//import com.example.bankcards.service.UserService;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.bean.override.mockito.MockitoBean;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.security.test.context.support.WithMockUser;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.UUID;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
////@WebMvcTest(UserController.class)
////@ExtendWith(MockitoExtension.class)
//@AutoConfigureMockMvc(addFilters = false)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class UserControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @Mock
//    private UserService userService;
//
//    @MockitoBean
//    JwtAuthenticationFilter jwtAuthenticationFilter;
//
//    @Test
//    @WithMockUser(username = "testuser", roles = {"ADMIN"})
//    public void findAllTest_whenAdmin() throws Exception {
//
//        List<UserDto> userDtoList = List.of(createUserDto());
//        Pageable pageable = createPageable();
//        Long count = 10L;
//        PageImpl page = new PageImpl(userDtoList, pageable, count);
//
//        when(userService.findAll(pageable)).thenReturn(page);
//
//        mvc.perform(get("/users/admin/all")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(content().json(createJson()));
//    }
//
//    private String createJson() {
//        return "{\n" +
//                "  \"totalPages\": 0,\n" +
//                "  \"totalElements\": 0,\n" +
//                "  \"size\": 0,\n" +
//                "  \"content\": [\n" +
//                "    {\n" +
//                "      \"id\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n" +
//                "      \"firstName\": \"string\",\n" +
//                "      \"secondName\": \"string\",\n" +
//                "      \"lastName\": \"string\",\n" +
//                "      \"username\": \"string\",\n" +
//                "      \"password\": \"string\",\n" +
//                "      \"role\": \"ROLE_USER\",\n" +
//                "      \"email\": \"string\",\n" +
//                "      \"cardList\": [\n" +
//                "        {\n" +
//                "          \"id\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n" +
//                "          \"number\": \"8952 4252 9094 0120\",\n" +
//                "          \"expiration\": \"2025-10-21T06:30:27.133Z\",\n" +
//                "          \"balance\": 0.1,\n" +
//                "          \"status\": \"ACTIVE\",\n" +
//                "          \"userId\": \"3fa85f64-5717-4562-b3fc-2c963f66afa6\",\n" +
//                "          \"archived\": true\n" +
//                "        }\n" +
//                "      ],\n" +
//                "      \"archived\": true\n" +
//                "    }\n" +
//                "  ],\n" +
//                "  \"number\": 0,\n" +
//                "  \"sort\": {\n" +
//                "    \"empty\": true,\n" +
//                "    \"sorted\": true,\n" +
//                "    \"unsorted\": true\n" +
//                "  },\n" +
//                "  \"first\": true,\n" +
//                "  \"last\": true,\n" +
//                "  \"numberOfElements\": 9,\n" +
//                "  \"pageable\": {\n" +
//                "    \"offset\": 0,\n" +
//                "    \"sort\": {\n" +
//                "      \"empty\": false,\n" +
//                "      \"sorted\": true,\n" +
//                "      \"unsorted\": false\n" +
//                "    },\n" +
//                "    \"paged\": true,\n" +
//                "    \"pageNumber\": 0,\n" +
//                "    \"pageSize\": 10,\n" +
//                "    \"unpaged\": true\n" +
//                "  },\n" +
//                "  \"empty\": true\n" +
//                "}";
//    }
//
//    private UserDto createUserDto() {
//        UserDto userDto = new UserDto();
//        userDto.setArchived(false);
//        userDto.setEmail("aaa@mail.ru");
//        userDto.setId(UUID.fromString("773ba8d9-182a-494c-bdc0-34fd62059f4a"));
//        userDto.setRole(Role.ROLE_ADMIN);
//        userDto.setPassword("123");
//        userDto.setFirstName("Aaa");
//        userDto.setSecondName("Bbb");
//        userDto.setLastName("Ddd");
//        userDto.setUsername("fff");
//        CardDto cardDto = createCardDto();
//        userDto.setCardList(List.of(cardDto));
//        return userDto;
//    }
//
//    private CardDto createCardDto() {
//        CardDto cardDto = new CardDto();
//        cardDto.setId(UUID.fromString("44e002e7-f998-4abb-ae45-989d763976e1"));
//        cardDto.setArchived(false);
//        cardDto.setUserId(UUID.fromString("773ba8d9-182a-494c-bdc0-34fd62059f4a"));
//        cardDto.setStatus(CardStatus.ACTIVE);
//        cardDto.setBalance(100.0);
//        cardDto.setExpiration(LocalDateTime.of(2030, 4,17,10,10));
//        cardDto.setNumber("1234 5678 0101 0101");
//        return cardDto;
//    };
//
//    private Pageable createPageable() {
//        return new Pageable() {
//            @Override
//            public int getPageNumber() {
//                return 0;
//            }
//
//            @Override
//            public int getPageSize() {
//                return 10;
//            }
//
//            @Override
//            public long getOffset() {
//                return 0;
//            }
//
//            @Override
//            public Sort getSort() {
//                return null;
//            }
//
//            @Override
//            public Pageable next() {
//                return null;
//            }
//
//            @Override
//            public Pageable previousOrFirst() {
//                return null;
//            }
//
//            @Override
//            public Pageable first() {
//                return null;
//            }
//
//            @Override
//            public Pageable withPage(int pageNumber) {
//                return null;
//            }
//
//            @Override
//            public boolean hasPrevious() {
//                return false;
//            }
//        };
//    }
//}
