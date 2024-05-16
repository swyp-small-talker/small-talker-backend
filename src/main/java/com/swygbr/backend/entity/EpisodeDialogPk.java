package com.swygbr.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

public class EpisodeDialogPk implements Serializable {
    private String dialogId;

    private String episodeId;

    private String characterId;

    public EpisodeDialogPk() {
    }

    public EpisodeDialogPk(String dialogId, String episodeId, String characterId) {
        this.dialogId = dialogId;
        this.episodeId = episodeId;
        this.characterId = characterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EpisodeDialogPk that = (EpisodeDialogPk) o;
        return Objects.equals(dialogId, that.dialogId) &&
                Objects.equals(episodeId, that.episodeId)
                &&Objects.equals(characterId, that.characterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dialogId, episodeId, characterId);
    }
}
