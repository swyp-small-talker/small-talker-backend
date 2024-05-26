package com.swygbr.backend.practice.service;

import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import com.swygbr.backend.practice.dto.AcquireKeywordResponseDto;
import com.swygbr.backend.practice.dto.CharacterResponseDto;
import com.swygbr.backend.practice.dto.EpisodeCompleteRequestDto;
import com.swygbr.backend.practice.dto.EpisodeCompleteResponseDto;
import com.swygbr.backend.practice.dto.EpisodeResponseDto;
import com.swygbr.backend.practice.dto.MessageResponseDto;
import com.swygbr.backend.practice.repository.PracticeCharacterRepository;
import com.swygbr.backend.practice.repository.PracticeEpisodeCompleteRepository;
import com.swygbr.backend.practice.repository.PracticeEpisodeRepository;
import com.swygbr.backend.practice.repository.PracticeMessageRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PracticeService {
    private final PracticeCharacterRepository characterRepository;
    private final PracticeEpisodeRepository episodeRepository;
    private final PracticeMessageRepository messageRepository;
    private final PracticeEpisodeCompleteRepository episodeCompleteRepository;

    public List<EntityModel<CharacterResponseDto>> getCharacterList() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCharacterList'");
    }

    public EntityModel<CharacterResponseDto> getCharacter(String characterId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCharacter'");
    }

    public List<EntityModel<EpisodeResponseDto>> getCharacterEpisode(String characterId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCharacterEpisode'");
    }

    public EntityModel<AcquireKeywordResponseDto> getAquireKeywords(String characterId, Long userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAquireKeywords'");
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
