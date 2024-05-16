package com.swygbr.backend;

import com.swygbr.backend.controller.PracticeController;
import com.swygbr.backend.entity.CharacterMain;
import com.swygbr.backend.entity.EpisodeMain;
import com.swygbr.backend.service.PracticeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PracticeController.class)
class PracticeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PracticeService practiceService;

    @Test
    void getAllCharacters() throws Exception {
        // given
        List<CharacterMain> characters = Arrays.asList(
                new CharacterMain("CH001", "김부장", "부장"),
                new CharacterMain("CH002", "박대리", "직속후배")
        );
        given(practiceService.getAllCharacters()).willReturn(characters);

        // when & then
        mockMvc.perform(get("/practice/characters"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].characterId", is("CH001")))
                .andExpect(jsonPath("$[0].characterNm", is("김부장")))
                .andExpect(jsonPath("$[1].characterId", is("CH002")))
                .andExpect(jsonPath("$[1].characterNm", is("박대리")));
    }

    @Test
    void getCharacterById() throws Exception {
        // given
        CharacterMain character = new CharacterMain("CH001", "김부장", "부장");
        given(practiceService.getCharacterById("CH001")).willReturn(character);

        // when & then
        mockMvc.perform(get("/practice/characters/CH001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.characterId", is("CH001")))
                .andExpect(jsonPath("$.characterNm", is("김부장")));
    }

    @Test
    void getCharacterById_NotFound() throws Exception {
        // given
        given(practiceService.getCharacterById("CH003")).willReturn(null);

        // when & then
        mockMvc.perform(get("/practice/characters/CH003"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getAllEpisodes() throws Exception {
        // given
        List<EpisodeMain> episodes = Arrays.asList(
                new EpisodeMain("EP001", "CH001", "첫 출근", "N"),
                new EpisodeMain("EP002", "CH002", "점심 메뉴 고르기", "N")
        );
        given(practiceService.getAllEpisodes()).willReturn(episodes);

        // when & then
        mockMvc.perform(get("/practice/episodes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].episodeId", is("EP001")))
                .andExpect(jsonPath("$[0].episodeTitle", is("첫 출근")))
                .andExpect(jsonPath("$[1].episodeId", is("EP002")))
                .andExpect(jsonPath("$[1].episodeTitle", is("점심 메뉴 고르기")));
    }

    // ... (getEpisodeById, getEpisodeDialogById 테스트 추가)
}
