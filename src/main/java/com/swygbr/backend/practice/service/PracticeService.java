package com.swygbr.backend.practice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.swygbr.backend.practice.domain.PracticeCharacter;
import com.swygbr.backend.practice.domain.PracticeEpisode;
import com.swygbr.backend.practice.domain.PracticeKeyword;
import com.swygbr.backend.practice.domain.PracticeMessage;
import com.swygbr.backend.practice.dto.CharacterKeywordResponseDto;
import com.swygbr.backend.practice.dto.CharacterResponseDto;
import com.swygbr.backend.practice.dto.EpisodeCompleteRequestDto;
import com.swygbr.backend.practice.dto.EpisodeCompleteResponseDto;
import com.swygbr.backend.practice.dto.EpisodeResponseDto;
import com.swygbr.backend.practice.dto.MessageResponseDto;
import com.swygbr.backend.practice.repository.PracticeCharacterRepository;
import com.swygbr.backend.practice.repository.PracticeEpisodeCompleteRepository;
import com.swygbr.backend.practice.repository.PracticeEpisodeRepository;
import com.swygbr.backend.practice.repository.PracticeKeywordRepository;
import com.swygbr.backend.practice.repository.PracticeMessageRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PracticeService {
    private final PracticeCharacterRepository characterRepository;
    private final PracticeEpisodeRepository episodeRepository;
    private final PracticeMessageRepository messageRepository;
    private final PracticeKeywordRepository keywordRepository;

    public List<EntityModel<CharacterResponseDto>> getCharacterList(Long userId) {
        List<PracticeCharacter> entities = characterRepository.findAll();

        List<EntityModel<CharacterResponseDto>> result = new ArrayList<>();
        for (PracticeCharacter entity : entities) {
            boolean complete = characterRepository.isCharacterCompleted(entity.getId(), userId);
            EntityModel<CharacterResponseDto> model = CharacterResponseDto.fromEntity(entity, complete);
            result.add(model);
        }
        return result;
    }

    public EntityModel<CharacterResponseDto> getCharacter(String characterId, Long userId) {
        PracticeCharacter entity = characterRepository.findById(characterId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "캐릭터를 찾을 수 없습니다.."));

        boolean complete = characterRepository.isCharacterCompleted(entity.getId(), userId);
        EntityModel<CharacterResponseDto> model = CharacterResponseDto.fromEntity(entity, complete);
        return model;
    }

    public List<EntityModel<EpisodeResponseDto>> getCharacterEpisode(String characterId, Long userId) {
        List<PracticeEpisode> entities = episodeRepository.findByCharacter_Id(characterId);

        List<EntityModel<EpisodeResponseDto>> result = new ArrayList<>();
        for (PracticeEpisode entity : entities) {
            PracticeMessage startMessageEntity = messageRepository.findByParentIsNullAndEpisode_Id(entity.getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "시작 message를 찾을 수 없습니다."));

            boolean complete = episodeRepository.isEpisodeCompleted(entity.getId(), userId);
            String startMessageId = startMessageEntity.getId();
            EntityModel<EpisodeResponseDto> model = EpisodeResponseDto.fromEntity(entity, complete, startMessageId);
            result.add(model);
        }
        return result;
    }

    public EntityModel<CharacterKeywordResponseDto> getCharacterKeywords(String characterId, Long userId) {
        int totalKeywordCount = keywordRepository.countTotalKeywordsByCharacterId(characterId);
        List<PracticeKeyword> keywords = keywordRepository.findAcquiredKeywordsByUserIdAndCharacterId(userId,
                characterId);
        int acquireKeywordCount = keywords.size();
        EntityModel<CharacterKeywordResponseDto> model = CharacterKeywordResponseDto.fromEntities(characterId,
                totalKeywordCount, acquireKeywordCount, keywords);
        return model;
    }

    public EntityModel<EpisodeResponseDto> getEpisode(String episodeId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEpisode'");
    }

    public EntityModel<EpisodeCompleteResponseDto> postEpisodeComplete(String episodeId,
            EpisodeCompleteRequestDto request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'postEpisodeComplete'");
    }

    public EntityModel<MessageResponseDto> getMessage(String messageId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMessage'");
    }

}
