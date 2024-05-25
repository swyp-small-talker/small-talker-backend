package com.swygbr.backend.practice.entity;

import com.swygbr.backend.practice.idclass.EpisodeDialogPk;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// TB_CHARACTER_MAIN
@Entity
@Table(name = "TB_EPISODE_DIALOG")
@Getter
@Setter
@IdClass(EpisodeDialogPk.class)
public class EpisodeDialog {
    @Id
    @Column(name = "dialog_id", length = 255)
    private String dialogId;

    @Id
    @Column(name = "episode_id")
    private String episodeId;

    @Id
    @Column(name = "character_id")
    private String characterId;

    @Column(name = "parent_dialog_id")
    private String parentDialogId;

    @Column(name = "left_right")
    private String leftRight;

    @Column(name = "user_choose")
    private String userChoose;

    @Column(name = "dialog_detail")
    private String dialogDetail;

    @Column(name = "correct_answer_yn")
    private String correctAnswerYn;


    public EpisodeDialog() {}


    public EpisodeDialog(String dialogId, String episodeId, String characterId, String parentDialogId, String leftRight, String userChoose, String dialogDetail, String correctAnswerYn) {
        this.dialogId = dialogId;
        this.episodeId = episodeId;
        this.characterId = characterId;
        this.parentDialogId = parentDialogId;
        this.leftRight = leftRight;
        this.userChoose = userChoose;
        this.dialogDetail = dialogDetail;
        this.correctAnswerYn = correctAnswerYn;
    }


    // Getter/Setter 메소드
    // ...
}
