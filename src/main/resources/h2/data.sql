insert    into Users (email, name)
values    ('test@gmail.com', 'myname');

insert    into UserCard (picture, title, content)
values    ('http://example.com/picture.png', '리액션봇', '상대방 말에 공감만 해주다가 정작 내 이야기는 못하고 끝나버리죠.');

insert    into UserCard (picture, title, content)
values    ('http://example.com/picture.png', '투머치토커', '하고 싶은 말이 너무 많아서 돌고 돌아 내 이야기만 하고 있는 경우가 많죠.');

insert    into UserCard (picture, title, content)
values    ('http://example.com/picture.png', '질문폭격기', '궁금한 게 너무 많아서 꼬리에 꼬리를 무는 질문을 하곤 하죠.');

insert    into UserCard (picture, title, content)
values    ('http://example.com/picture.png', '모범생', '적절히 치고 빠질 줄 아는 모범생 ! 사람들과 대화를 \"잘\" 하는 데 관심이 많죠.');

insert    into UserCard (picture, title, content)
values    ('http://example.com/picture.png', '스몰토커', '완성형 토크 플레이어 말이 필요 없는 센스를 지니고 계시군요!');

-- UserCard Tutorial
-- 자스민과의 대화 시작
--
-- question 3
insert    into Tutorial (id, tutorialType, isStart, nextTutorialFk)
values    (30, 'USER_CARD', false, null);

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (300, 30, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (300, 'JASMINE', 'COMMON', '앗, 회사에 미팅이 있던 걸 깜빡했어요..!');

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (301, 30, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (301, 'JASMINE', 'COMMON', '급하게 들어가봐야 할 것 같아요...!');

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (302, 30, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (302, 'JASMINE', 'COMMON', '{userName}님 미안해요');

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (303, 30, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (303, 'JASMINE', 'COMMON', '나중에 시간 되면 한번 봬요!!');

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (304, 30, 'CHOICE');

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (305, 30, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (305, 'JASMINE', 'COMMON', '그럼 다음에 또 연락드릴게요!!');

-- question 3 reaction 1
insert    into Tutorial (id, tutorialType, isStart, nextTutorialFk)
values    (31, 'USER_CARD', false, null);

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (310, 31, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (310, 'USER', 'COMMON', '네!! 좋아요!! 언제든지요!');

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (311, 31, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (311, 'USER', 'COMMON', '네 감사합니다!');

-- question 3 reaction 2
insert    into Tutorial (id, tutorialType, isStart, nextTutorialFk)
values    (32, 'USER_CARD', false, 30);

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (320, 32, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (320, 'USER', 'COMMON', '네 좋아요! 편하게 불러주세요~');

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (321, 32, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (321, 'USER', 'COMMON', '저희 회사는 아직 한가하네요ㅎㅎ');

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (322, 32, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (322, 'JASMINE', 'COMMON', '00님이라도 한가하시니 다행이에요');

-- question 3 reaction 3
insert    into Tutorial (id, tutorialType, isStart, nextTutorialFk)
values    (33, 'USER_CARD', false, 30);

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (330, 33, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (330, 'USER', 'COMMON', '에고ㅠㅠ 아쉽네요');

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (331, 33, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (331, 'USER', 'COMMON', '나중에 카페에서 차 한잔 해요!');

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (332, 33, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (332, 'JASMINE', 'COMMON', '저 커피 좋아한답니다ㅎㅎ');

-- question 3 choice 1
insert    into TutorialMessageChoice (messageFk, reactionTutorialFk, choiceType, content)
values    (304, 31, 'REACTION', '좋아요! 저희 회사는 아직 한가하네요ㅎㅎ');

-- question 3 choice 2
insert    into TutorialMessageChoice (messageFk, reactionTutorialFk, choiceType, content)
values    (304, 32, 'MY_STORY', '에고 아쉽네요 ㅠㅠ 혹시 커피 드시나요?');

-- question 3 choice 3
insert    into TutorialMessageChoice (messageFk, reactionTutorialFk, choiceType, content)
values    (304, 33, 'QUESTION', '네! 좋아요 언제든지요!');

-- question 2
insert    into Tutorial (id, tutorialType, isStart, nextTutorialFk)
values    (12, 'USER_CARD', false, 30);

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (200, 12, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (200, 'JASMINE', 'COMMON', '지니 님이 서로 소개해준 이유가 있는 것 같아요..!');

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (201, 12, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (201, 'JASMINE', 'COMMON', '저희...왠지 잘 통할 것 같네요 !');

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (202, 12, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (202, 'JASMINE', 'COMMON', '{userName} 님은 좋아하는 거 있으세요 ?');

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (203, 12, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (203, 'JASMINE', 'COMMON', '저는 요새 운동 시작했어요 !');

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (204, 12, 'CHOICE');

-- question 2 reaction 1
insert    into Tutorial (id, tutorialType, isStart, nextTutorialFk)
values    (20, 'USER_CARD', false, 30);

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (210, 20, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (210, 'USER', 'COMMON', '오, 운동이라니 !');

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (211, 20, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (211, 'USER', 'COMMON', '멋지시네요 !');

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (212, 20, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (212, 'USER', 'COMMON', '감사합니다 ㅎㅎ');

-- question 2 reaction 2
insert    into Tutorial (id, tutorialType, isStart, nextTutorialFk)
values    (21, 'USER_CARD', false, 30);

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (220, 21, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (220, 'USER', 'COMMON', '저도 운동 좋아해요!');

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (221, 21, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (221, 'USER', 'COMMON', '어렸을 땐 축구를 많이 했죠ㅎㅎ');

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (222, 21, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (222, 'JASMINE', 'COMMON', '오 축구 좋아하셨나봐요! 멋지시네요!');

-- question 2 reaction 3
insert    into Tutorial (id, tutorialType, isStart, nextTutorialFk)
values    (22, 'USER_CARD', false, 30);

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (230, 22, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (230, 'USER', 'COMMON', '멋지시네요! 어떤 운동 하세요?');

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (231, 22, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (231, 'JASMINE', 'COMMON', '저는 필라테스 다녀요. ㅎㅎ');

-- question 2 choice 1
insert    into TutorialMessageChoice (messageFk, reactionTutorialFk, choiceType, content)
values    (204, 20, 'REACTION', '어떤 운동 하세요 ?');

-- question 2 choice 2
insert    into TutorialMessageChoice (messageFk, reactionTutorialFk, choiceType, content)
values    (204, 21, 'MY_STORY', '저도 운동 좋아해요 !');

-- question 2 choice 3
insert    into TutorialMessageChoice (messageFk, reactionTutorialFk, choiceType, content)
values    (204, 22, 'QUESTION', '오, 운동이라니 멋지시네요 !');

-- question 1
insert    into Tutorial (id, tutorialType, isStart, nextTutorialFk)
values    (4, 'USER_CARD', true, 12);

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (100, 4, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (100, 'JASMINE', 'COMMON', '안녕하세요!');

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (101, 4, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (101, 'JASMINE', 'COMMON', '지니님 소개 받고 연락드려요!');

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (102, 4, 'CHOICE');

-- question 1 reaction 1
insert    into Tutorial (id, tutorialType, isStart, nextTutorialFk)
values    (10, 'USER_CARD', false, 12);

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (111, 10, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (111, 'USER', 'COMMON', '아 네 ! 안녕하세요 !');

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (112, 10, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (112, 'JASMINE', 'COMMON', '지니님 덕에 좋은 분 알게 되었네요ㅎㅎ');

-- question 1 reaction 2
insert    into Tutorial (id, tutorialType, isStart, nextTutorialFk)
values    (11, 'USER_CARD', false, 12);

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (121, 11, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (121, 'USER', 'COMMON', '아 네 ! 안녕하세요 !');

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (122, 11, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (122, 'USER', 'COMMON', '저는 지니님 방금 알게 됐는데');

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (123, 11, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (123, 'USER', 'COMMON', '덕분에 좋은 인연 알게 되었네요ㅎㅎ');

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (124, 11, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (124, 'JASMINE', 'COMMON', '그러게요ㅎㅎ');

-- question 1 reaction 3
insert    into Tutorial (id, tutorialType, isStart, nextTutorialFk)
values    (13, 'USER_CARD', false, 12);

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (131, 13, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (131, 'USER', 'COMMON', '아 네 ! 안녕하세요 !');

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (132, 13, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (132, 'USER', 'COMMON', '지니님은 어떻게 아시게 된건가요 ?');

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (133, 13, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (133, 'JASMINE', 'COMMON', '지니 님이 회사 선배예요 ! ㅎㅎ');

-- question 1 choice 1
insert    into TutorialMessageChoice (messageFk, reactionTutorialFk, choiceType, content)
values    (102, 10, 'REACTION', '아 네 안녕하세요 !');

-- question 1 choice 2
insert    into TutorialMessageChoice (messageFk, reactionTutorialFk, choiceType, content)
values    (102, 11, 'MY_STORY', '덕분에 좋은 인연 알게되었네요ㅎㅎ');

-- question 1 choice 3
insert    into TutorialMessageChoice (messageFk, reactionTutorialFk, choiceType, content)
values    (102, 12, 'QUESTION', '지니님은 어떻게 아시게 된건가요 ?');

--Profile Tutorial
-- 지니와의 대화 시작
insert    into Tutorial (id, tutorialType, isStart)
values    (1, 'PROFILE', true);

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (1, 1, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (1, 'GENIE', 'COMMON', '안녕하세요 !');

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (2, 1, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (2, 'GENIE', 'COMMON', '저는 당신의 고민을 들어줄 지니예요 !');

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (3, 1, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (3, 'GENIE', 'COMMON', '앞으로 당신을 뭐라고 부르면 될까요 ?');

-- INPUT
insert    into TutorialMessage (id, tutorialFk, messageType)
values    (4, 1, 'INPUT');

insert    into TutorialMessageInput (messageFk, inputType)
values    (4, 'USER_NAME');

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (5, 1, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (5, 'GENIE', 'COMMON', '{ userName } 님 ! 멋진 이름이에요 !');

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (6, 1, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (6, 'GENIE', 'COMMON', '음, 우선 { userName } 님이 어떤 대화 스타일을 가지고 있는지 알아보고 싶어요 !');

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (7, 1, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (7, 'GENIE', 'COMMON', '원하신다면 제 친구 자스민을 소개시켜드릴게요 !');

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (8, 1, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (8, 'GENIE', 'COMMON', '자스민과의 대화가 시작되면 선택지 중 원하는 답안을 골라주세요');

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (9, 1, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (9, 'GENIE', 'COMMON', '준비 되셨나요 ?');

--준비 됐어요 reaction
insert    into Tutorial (id, tutorialType, isStart, nextTutorialFk)
values    (2, 'PROFILE', false, 4);

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (10, 2, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (10, 'USER', 'COMMON', '네, 준비 됐어요 !');

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (11, 2, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (11, 'GENIE', 'COMMON', '좋아요 !');

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (12, 2, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (12, 'GENIE', 'COMMON', '그럼 바로 자스민과 연결해드릴게요 !');

-- 아직 마음의 준비가..... reaction
insert    into Tutorial (id, tutorialType, isStart, nextTutorialFk)
values    (3, 'PROFILE', false, null);

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (20, 2, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (20, 'USER', 'COMMON', '아직 마음의 준비가.....');

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (21, 2, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (21, 'GENIE', 'COMMON', '괜찮아요 ! 그럴 수 있어요..!');

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (22, 2, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (22, 'GENIE', 'COMMON', '아쉽지만 마음의 준비가 됐을 때');

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (23, 2, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (23, 'GENIE', 'COMMON', '다시 만나요 !');

insert    into TutorialMessage (id, tutorialFk, messageType)
values    (24, 2, 'TEXT');

insert    into TutorialMessageText (messageFk, actorType, textType, content)
values    (24, 'GENIE', 'NARRATION', '지니와의 대화가 종료되었습니다.');

-- 준비 됐어요 선택지
insert    into TutorialMessage (id, tutorialFk, messageType)
values    (30, 1, 'CHOICE');

insert    into TutorialMessageChoice (messageFk, reactionTutorialFk, choiceType, content)
values    (30, 2, 'COMMON', '네, 준비 됐어요 !');

-- 아직 마음의 준비가 선택지
insert    into TutorialMessageChoice (messageFk, reactionTutorialFk, choiceType, content)
values    (30, 3, 'COMMON', '아직 마음의 준비가.....');