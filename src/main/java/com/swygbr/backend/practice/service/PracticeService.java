package com.swygbr.backend.practice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.swygbr.backend.practice.domain.PracticeCharacter;
import com.swygbr.backend.practice.domain.PracticeEpisode;
import com.swygbr.backend.practice.domain.PracticeEpisodeComplete;
import com.swygbr.backend.practice.domain.PracticeKeyword;
import com.swygbr.backend.practice.domain.PracticeMessage;
import com.swygbr.backend.practice.domain.PracticeSkill;
import com.swygbr.backend.practice.dto.CharacterKeywordResponseDto;
import com.swygbr.backend.practice.dto.CharacterResponseDto;
import com.swygbr.backend.practice.dto.CharacterSkillResponseDto;
import com.swygbr.backend.practice.dto.EpisodeCompleteRequestDto;
import com.swygbr.backend.practice.dto.EpisodeCompleteResponseDto;
import com.swygbr.backend.practice.dto.EpisodeResponseDto;
import com.swygbr.backend.practice.dto.MessageResponseDto;
import com.swygbr.backend.practice.repository.PracticeCharacterRepository;
import com.swygbr.backend.practice.repository.PracticeEpisodeCompleteRepository;
import com.swygbr.backend.practice.repository.PracticeEpisodeRepository;
import com.swygbr.backend.practice.repository.PracticeKeywordRepository;
import com.swygbr.backend.practice.repository.PracticeMessageRepository;
import com.swygbr.backend.practice.repository.PracticeSkillRepository;
import com.swygbr.backend.user.domain.UserEntity;
import com.swygbr.backend.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PracticeService {
    private final UserRepository userRepository;
    private final PracticeCharacterRepository characterRepository;
    private final PracticeEpisodeRepository episodeRepository;
    private final PracticeEpisodeCompleteRepository episodeCompleteRepository;
    private final PracticeMessageRepository messageRepository;
    private final PracticeKeywordRepository keywordRepository;
    private final PracticeSkillRepository skillRepository;

    public CollectionModel<EntityModel<CharacterResponseDto>> getCharacterList(Long userId) {
        List<PracticeCharacter> entities = characterRepository.findAll();

        List<EntityModel<CharacterResponseDto>> modelList = new ArrayList<>();
        for (PracticeCharacter entity : entities) {
            boolean complete = characterRepository.isCharacterCompleted(entity.getId(), userId);
            EntityModel<CharacterResponseDto> model = CharacterResponseDto.fromEntity(entity, complete);
            modelList.add(model);
        }

        CollectionModel<EntityModel<CharacterResponseDto>> result = CollectionModel.of(modelList);

        return result;
    }

    public EntityModel<CharacterResponseDto> getCharacter(String characterId, Long userId) {
        PracticeCharacter entity = characterRepository.findById(characterId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "캐릭터를 찾을 수 없습니다."));

        boolean complete = characterRepository.isCharacterCompleted(entity.getId(), userId);
        EntityModel<CharacterResponseDto> model = CharacterResponseDto.fromEntity(entity, complete);
        return model;
    }

    public CollectionModel<EntityModel<EpisodeResponseDto>> getCharacterEpisode(String characterId, Long userId) {
        List<PracticeEpisode> entities = episodeRepository.findByCharacter_Id(characterId);

        List<EntityModel<EpisodeResponseDto>> modelList = new ArrayList<>();
        for (PracticeEpisode entity : entities) {
            PracticeMessage startMessageEntity = messageRepository.findByParentIsNullAndEpisode_Id(entity.getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "메시지를 찾을 수 없습니다."));

            boolean complete = episodeRepository.isEpisodeCompleted(entity.getId(), userId);
            String startMessageId = startMessageEntity.getId();
            EntityModel<EpisodeResponseDto> model = EpisodeResponseDto.fromEntity(entity, complete, startMessageId,
                    characterId);
            modelList.add(model);
        }
        return CollectionModel.of(modelList);
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

    public EntityModel<CharacterSkillResponseDto> getCharacterSkills(String characterId, Long userId) {
        int totalSkillCount = skillRepository.countTotalSkillsByCharacterId(characterId);
        List<PracticeSkill> skills = skillRepository.findAcquiredSkillsByUserIdAndCharacterId(userId,
                characterId);
        int acquireSkillCount = skills.size();
        EntityModel<CharacterSkillResponseDto> model = CharacterSkillResponseDto.fromEntities(characterId,
                totalSkillCount, acquireSkillCount, skills);
        return model;
    }

    public EntityModel<EpisodeCompleteResponseDto> postEpisodeComplete(String episodeId, Long userId,
            EpisodeCompleteRequestDto request) {
        PracticeMessage message = messageRepository.findById(request.lastMessageId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "메시지를 찾을 수 없습니다."));
        if (!message.getMessageType().equals("LAST_MESSAGE")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "마지막 메시지가 아닙니다.");
        }

        if (!message.getEpisode().getId().equals(episodeId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "마지막 메시지와 episodeId가 일치하지 않습니다.");
        }

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "에피소드를 찾을 수 없습니다."));
        PracticeEpisode episode = episodeRepository.findById(episodeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "에피소드를 찾을 수 없습니다."));

        PracticeEpisodeComplete entity = new PracticeEpisodeComplete(user, episode);
        episodeCompleteRepository.save(entity);

        return EpisodeCompleteResponseDto.fromEntity(entity);
    }

    public EntityModel<MessageResponseDto> getMessage(String messageId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMessage'");
    }

}
