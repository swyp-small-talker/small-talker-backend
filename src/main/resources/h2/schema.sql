CREATE TABLE `user_card` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `picture` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `content` varchar(255) NOT NULL,
  `createdAt` timestamp NOT NULL DEFAULT now(),
  `updatedAt` timestamp
);

CREATE TABLE `tutorial` (
  `id` int NOT NULL,
  `fk_parent` int NULL,
  `fk_user_card` int NULL,
  `character` varchar(255) NOT NULL COMMENT '지니, 회원',
  `type` varchar(255) NOT NULL COMMENT 'common, choice, reaction',
  `title` varchar(255) NOT NULL,
  `content` varchar(255) NOT NULL
);