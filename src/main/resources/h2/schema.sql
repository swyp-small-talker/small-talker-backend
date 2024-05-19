CREATE TABLE UserCard (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  picture VARCHAR(255) NOT NULL,
  title VARCHAR(20) NOT NULL,
  content VARCHAR(255) NOT NULL
);
CREATE TABLE Users (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  userCardFk BIGINT DEFAULT NULL,
  completeProfileTypeTutorial BOOLEAN DEFAULT false NOT NULL,
  completeUserCardTypeTutorial BOOLEAN DEFAULT false NOT NULL,
  email VARCHAR(255) UNIQUE NOT NULL,
  name VARCHAR(100) NOT NULL,
  createdAt DATETIME DEFAULT NOW () NOT NULL,
  updatedAt DATETIME DEFAULT NOW () ON UPDATE CURRENT_TIMESTAMP NOT NULL,
  FOREIGN KEY (userCardFk) REFERENCES UserCard (id)
);
CREATE TABLE Tutorial (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  tutorialType VARCHAR(20) NOT NULL,
  isStart BOOLEAN DEFAULT false NOT NULL,
  nextTutorialFk BIGINT,
  FOREIGN KEY (nextTutorialFk) REFERENCES Tutorial (id)
);
CREATE TABLE TutorialMessage (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  tutorialFk BIGINT,
  messageType VARCHAR(20),
  FOREIGN KEY (tutorialFk) REFERENCES Tutorial (id)
);
CREATE TABLE TutorialMessageText (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  messageFk BIGINT UNIQUE,
  actorType VARCHAR(20) NOT NULL,
  textType VARCHAR(20),
  content VARCHAR(255) NOT NULL,
  FOREIGN KEY (messageFk) REFERENCES TutorialMessage (id)
);
CREATE TABLE TutorialMessageInput (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  messageFk BIGINT UNIQUE,
  inputType VARCHAR(20),
  FOREIGN KEY (messageFk) REFERENCES TutorialMessage (id)
);
CREATE TABLE TutorialMessageChoice (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  messageFk BIGINT NOT NULL,
  reactionTutorialFk BIGINT UNIQUE NOT NULL,
  choiceType VARCHAR(20) NOT NULL,
  content VARCHAR(255) NOT NULL,
  FOREIGN KEY (messageFk) REFERENCES TutorialMessage (id),
  FOREIGN KEY (reactionTutorialFk) REFERENCES Tutorial (id)
);