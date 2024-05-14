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
