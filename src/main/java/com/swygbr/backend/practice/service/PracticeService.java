package com.swygbr.backend.practice.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.swygbr.backend.practice.controller.PracticeController;
import com.swygbr.backend.practice.dto.CharacterResponseDto;
import com.swygbr.backend.practice.entity.CharacterInfo;
import com.swygbr.backend.practice.entity.CharacterMain;
import com.swygbr.backend.practice.entity.EpisodeDialog;
import com.swygbr.backend.practice.entity.EpisodeMain;
import com.swygbr.backend.practice.repository.CharacterInfoRepository;
import com.swygbr.backend.practice.repository.CharacterMainRepository;
import com.swygbr.backend.practice.repository.EpisodeDialogRepository;
import com.swygbr.backend.practice.repository.EpisodeMainRepository;
import com.swygbr.backend.practice.repository.EpisodeRewardRepository;
import com.swygbr.backend.tutorial.controller.UserCardController;

@Service
public class PracticeService {

    @Autowired
    private CharacterMainRepository characterMainRepository;
    @Autowired
    private EpisodeMainRepository episodeMainRepository;
    @Autowired
    private CharacterInfoRepository characterInfoRepository;
    @Autowired
    private EpisodeDialogRepository episodeDialogRepository;
    @Autowired
    private EpisodeRewardRepository episodeRewardRepository;

    // 캐릭터 관련 로직
    public List<EntityModel<CharacterResponseDto>> getCharacterList() {
        List<CharacterMain> entities = characterMainRepository.findAll();

        List<EntityModel<CharacterResponseDto>> characterResponseDtoList = new ArrayList<>();
        for (CharacterMain entity : entities) {
            characterResponseDtoList.add(characterToDto(entity));
        }
        return characterResponseDtoList;
    }

    public EntityModel<CharacterResponseDto> getCharacterById(String characterId) {
        CharacterMain entity = characterMainRepository.findById(characterId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "케릭터를 찾을 수 없습니다."));
        return characterToDto(entity);
    }

    public List<Object[]> getCharacterKeywords(String characterId) {
        return characterInfoRepository.findCharacterInfoWithDetailsByCharacterId(characterId);
    }

    // 에피소드 관련 로직
    public List<EpisodeMain> getAllEpisodes() {
        return episodeMainRepository.findAll();
    }

    public EpisodeMain getEpisodeById(String episodeId) {
        return episodeMainRepository.findByEpisodeId(episodeId).orElse(null);
    }

    public List<EpisodeMain> getEpisodesByCharacterId(String characterId, Long userId) {
        return episodeMainRepository.findByCharacterIdAndUserId(characterId, userId);
    }

    public Optional<EpisodeDialog> findInitialDialogByEpisodeId(String episodeId) {
        return episodeDialogRepository.findByEpisodeIdAndParentDialogIdIsNull(episodeId);
    }

    public List<EpisodeDialog> findChildrenByParentDialogId(String chatId) {
        return episodeDialogRepository.findChildrenByParentDialogId(chatId);
    }

    private EntityModel<CharacterResponseDto> characterToDto(CharacterMain entity) {
        String characterId = entity.getCharacterId();
        String name = entity.getCharacterNm();
        String type = entity.getCharacterType();
        boolean complete = episodeMainRepository.isCompleteByCharacterId(characterId);
        CharacterResponseDto dto = new CharacterResponseDto(characterId, name, type, complete);

        EntityModel<CharacterResponseDto> model = EntityModel.of(dto);
        Link episodeLink = linkTo(methodOn(PracticeController.class).getCharacterEpisodes(characterId, null))
                .withRel("episode");
        model.add(episodeLink);
        Link keywordLink = linkTo(methodOn(PracticeController.class).getCharacterKeywords(characterId))
                .withRel("keyword");
        model.add(keywordLink);
        return model;
    }
}
