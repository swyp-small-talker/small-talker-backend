CREATE TABLE UserCard(
    id bigint AUTO_INCREMENT PRIMARY KEY,
    picture varchar(255) NOT NULL,
    title varchar(20) NOT NULL,
    content varchar(255) NOT NULL
);
CREATE TABLE Users(
    id bigint AUTO_INCREMENT PRIMARY KEY,
    userCardFk bigint DEFAULT NULL,
    completeProfileTypeTutorial boolean DEFAULT false NOT NULL,
    completeUserCardTypeTutorial boolean DEFAULT false NOT NULL,
    email varchar(255) UNIQUE NOT NULL,
    name varchar(100) NOT NULL,
    profile varchar(255) NOT NULL,
    role varchar(255) NOT NULL,
    createdAt DATETIME DEFAULT NOW() NOT NULL,
    updatedAt DATETIME DEFAULT NOW() ON UPDATE CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY(userCardFk) REFERENCES UserCard(id)
);
CREATE TABLE Tutorial(
    id bigint AUTO_INCREMENT PRIMARY KEY,
    tutorialType varchar(20) NOT NULL,
    isStart boolean DEFAULT false NOT NULL,
    nextTutorialFk bigint,
    FOREIGN KEY(nextTutorialFk) REFERENCES Tutorial(id)
);
CREATE TABLE TutorialMessage(
    id bigint AUTO_INCREMENT PRIMARY KEY,
    tutorialFk bigint,
    messageType varchar(20),
    FOREIGN KEY(tutorialFk) REFERENCES Tutorial(id)
);
CREATE TABLE TutorialMessageText(
    id bigint AUTO_INCREMENT PRIMARY KEY,
    messageFk bigint UNIQUE,
    actorType varchar(20) NOT NULL,
    textType varchar(20),
    content varchar(255) NOT NULL,
    FOREIGN KEY(messageFk) REFERENCES TutorialMessage(id)
);
CREATE TABLE TutorialMessageInput(
    id bigint AUTO_INCREMENT PRIMARY KEY,
    messageFk bigint UNIQUE,
    inputType varchar(20),
    FOREIGN KEY(messageFk) REFERENCES TutorialMessage(id)
);
CREATE TABLE TutorialMessageChoice(
    id bigint AUTO_INCREMENT PRIMARY KEY,
    messageFk bigint NOT NULL,
    reactionTutorialFk bigint UNIQUE NOT NULL,
    choiceType varchar(20) NOT NULL,
    content varchar(255) NOT NULL,
    FOREIGN KEY(messageFk) REFERENCES TutorialMessage(id),
    FOREIGN KEY(reactionTutorialFk) REFERENCES Tutorial(id)
);
CREATE TABLE `TB_CHARACTER_MAIN`
(
    `character_id`   VARCHAR(255) NOT NULL,
    `character_nm`   VARCHAR(255) NULL,
    `character_type` VARCHAR(255) NULL COMMENT '부장,직속선배 등 화자와의 관계'
);

CREATE TABLE `TB_EPISODE_MAIN`
(
    `episode_id`          VARCHAR(255) NULL,
    `character_id`        VARCHAR(255) NULL,
    `episode_title`       VARCHAR(255) NULL,
    `episode_complete_yn` VARCHAR(255) NULL
);

CREATE TABLE `TB_EPISODE_DIALOG`
(
    `dialog_id`         VARCHAR(255) NOT NULL,
    `episode_id`        VARCHAR(255) NULL,
    `character_id`      VARCHAR(255) NULL,
    `parent_dialog_id`  VARCHAR(255) NULL,
    `left_right`        VARCHAR(255) NULL COMMENT '대화창에서 좌/우 표기 구분자 좌-캐릭터 우-유저',
    `user_choose`       VARCHAR(255) NULL COMMENT '유저가 보는 선택지(~~~를 한다)',
    `dialog_detail`     VARCHAR(255) NULL COMMENT '실제 출력되는 대사',
    `correct_answer_yn` VARCHAR(255) NULL
);

CREATE TABLE `TB_EPISODE_REWARD`
(
    `reward_id`    VARCHAR(255) NULL,
    `episode_id`   VARCHAR(255) NULL,
    `character_id` VARCHAR(255) NULL,
    `reward_nm`    VARCHAR(255) NULL
);

CREATE TABLE `TB_CHARACTER_INFO`
(
    `info_id`           VARCHAR(255) NOT NULL COMMENT '성격/관심사 등',
    `character_id` VARCHAR(255) NULL,
    `info_category_nm`  VARCHAR(255) NULL
);

CREATE TABLE `TB_CHARACTER_INFO_DETAIL`
(
    `info_detail_id` VARCHAR(255) NOT NULL,
    `info_id`        VARCHAR(255) NOT NULL COMMENT '성격/관심사 등',
    `info_detail_nm` VARCHAR(255) NULL COMMENT '한식 등'
);
