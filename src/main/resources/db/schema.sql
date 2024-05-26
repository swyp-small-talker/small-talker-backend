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
CREATE TABLE PracticeCharacter (
    id VARCHAR(255) NOT NULL,
    name VARCHAR(100),
    characterType VARCHAR(255),
    PRIMARY KEY (id)
);
CREATE TABLE PracticeEpisode (
    id VARCHAR(255) NOT NULL,
    title VARCHAR(255),
    characterFk VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY (characterFk) REFERENCES PracticeCharacter(id)
);
CREATE TABLE PracticeEpisodeComplete (
    id BIGINT AUTO_INCREMENT NOT NULL,
    userFk BIGINT,
    episodeFk VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY (userFk) REFERENCES Users(id),
    FOREIGN KEY (episodeFk) REFERENCES PracticeEpisode(id)
);
CREATE TABLE PracticeKeyword (
    id VARCHAR(255) NOT NULL,
    category VARCHAR(255),
    content VARCHAR(255),
    episodeFk VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY (episodeFk) REFERENCES PracticeEpisode(id)
);
CREATE TABLE PracticeSkill (
    id VARCHAR(255) NOT NULL,
    title VARCHAR(255),
    episodeFk VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY (episodeFk) REFERENCES PracticeEpisode(id)
);
CREATE TABLE PracticeMessage (
    id VARCHAR(255) NOT NULL,
    actor VARCHAR(255),
    messageType VARCHAR(255),
    correct VARCHAR(255),
    content VARCHAR(255),
    episodeFk VARCHAR(255),
    parentFk VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY (episodeFk) REFERENCES PracticeEpisode(id),
    FOREIGN KEY (parentFk) REFERENCES PracticeMessage(id)
);