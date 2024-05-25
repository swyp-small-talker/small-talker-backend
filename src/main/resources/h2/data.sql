-- TB_CHARACTER_MAIN
INSERT INTO TB_CHARACTER_MAIN (character_id, character_nm, character_type)
VALUES ('CH001', '김부장', '부장');
INSERT INTO TB_CHARACTER_MAIN (character_id, character_nm, character_type)
VALUES ('CH002', '박대리', '직속후배');
INSERT INTO TB_CHARACTER_MAIN (character_id, character_nm, character_type)
VALUES ('CH003', '최사원', '동기');
-- TB_EPISODE_MAIN
INSERT INTO TB_EPISODE_MAIN (
        episode_id,
        character_id,
        user_id,
        episode_title,
        episode_complete_yn
    )
VALUES ('EP001', 'CH001', 1, '첫 출근', 'N');
INSERT INTO TB_EPISODE_MAIN (
        episode_id,
        character_id,
        user_id,
        episode_title,
        episode_complete_yn
    )
VALUES ('EP002', 'CH001', 1, '점심 메뉴 고르기', 'N');
INSERT INTO TB_EPISODE_MAIN (
        episode_id,
        character_id,
        user_id,
        episode_title,
        episode_complete_yn
    )
VALUES ('EP003', 'CH001', 1, '퇴근 후 한 잔', 'N');
-- TB_EPISODE_DIALOG
INSERT INTO TB_EPISODE_DIALOG (
        dialog_id,
        episode_id,
        character_id,
        parent_dialog_id,
        left_right,
        user_choose,
        dialog_detail,
        correct_answer_yn
    )
VALUES (
        'DLG000',
        'EP000',
        'CH000',
        NULL,
        NULL,
        NULL,
        '최상위대화',
        NULL
    );
INSERT INTO TB_EPISODE_DIALOG (
        dialog_id,
        episode_id,
        character_id,
        parent_dialog_id,
        left_right,
        user_choose,
        dialog_detail,
        correct_answer_yn
    )
VALUES (
        'DLG001',
        'EP001',
        'CH001',
        'DLG000',
        'L',
        NULL,
        '안녕하세요, 신입사원?',
        NULL
    );
INSERT INTO TB_EPISODE_DIALOG (
        dialog_id,
        episode_id,
        character_id,
        parent_dialog_id,
        left_right,
        user_choose,
        dialog_detail,
        correct_answer_yn
    )
VALUES (
        'DLG002',
        'EP001',
        'CH001',
        'DLG001',
        'R',
        '인사를 한다',
        '네, 안녕하세요! 김부장님!',
        'Y'
    );
INSERT INTO TB_EPISODE_DIALOG (
        dialog_id,
        episode_id,
        character_id,
        parent_dialog_id,
        left_right,
        user_choose,
        dialog_detail,
        correct_answer_yn
    )
VALUES (
        'DLG003',
        'EP001',
        'CH001',
        'DLG001',
        'R',
        '고개를 끄덕인다',
        '... (꾸벅)',
        'N'
    );
-- TB_EPISODE_REWARD
INSERT INTO TB_EPISODE_REWARD (reward_id, episode_id, character_id, reward_nm)
VALUES ('RWD001', 'EP001', 'CH001', '김부장의 명함');
INSERT INTO TB_EPISODE_REWARD (reward_id, episode_id, character_id, reward_nm)
VALUES ('RWD002', 'EP002', 'CH002', '박대리의 커피 쿠폰');
INSERT INTO TB_EPISODE_REWARD (reward_id, episode_id, character_id, reward_nm)
VALUES ('RWD003', 'EP003', 'CH003', '최사원의 맛집 리스트');
-- TB_CHARACTER_INFO
INSERT INTO TB_CHARACTER_INFO (info_id, character_id, info_category_nm)
VALUES ('INFO001', 'CH001', '성격');
INSERT INTO TB_CHARACTER_INFO (info_id, character_id, info_category_nm)
VALUES ('INFO002', 'CH002', '관심사');
INSERT INTO TB_CHARACTER_INFO (info_id, character_id, info_category_nm)
VALUES ('INFO003', 'CH003', '취미');
-- TB_CHARACTER_INFO_DETAIL
INSERT INTO TB_CHARACTER_INFO_DETAIL (info_detail_id, info_id, info_detail_nm)
VALUES ('DETAIL001', 'INFO001', '엄격함');
INSERT INTO TB_CHARACTER_INFO_DETAIL (info_detail_id, info_id, info_detail_nm)
VALUES ('DETAIL002', 'INFO001', '낙천적');
INSERT INTO TB_CHARACTER_INFO_DETAIL (info_detail_id, info_id, info_detail_nm)
VALUES ('DETAIL003', 'INFO002', '골프');
INSERT INTO TB_CHARACTER_INFO_DETAIL (info_detail_id, info_id, info_detail_nm)
VALUES ('DETAIL004', 'INFO003', '등산');
INSERT INTO Users (email, name)
VALUES ('test@gmail.com', 'myname');
INSERT INTO UserCard (picture, title, content)
VALUES (
        'http://example.com/picture.png',
        '리액션봇',
        '상대방 말에 공감만 해주다가 정작 내 이야기는 못하고 끝나버리죠.'
    );
INSERT INTO UserCard (picture, title, content)
VALUES (
        'http://example.com/picture.png',
        '투머치토커',
        '하고 싶은 말이 너무 많아서 돌고 돌아 내 이야기만 하고 있는 경우가 많죠.'
    );
INSERT INTO UserCard (picture, title, content)
VALUES (
        'http://example.com/picture.png',
        '질문폭격기',
        '궁금한 게 너무 많아서 꼬리에 꼬리를 무는 질문을 하곤 하죠.'
    );
INSERT INTO UserCard (picture, title, content)
VALUES (
        'http://example.com/picture.png',
        '모범생',
        '적절히 치고 빠질 줄 아는 모범생 ! 사람들과 대화를 \"잘\" 하는 데 관심이 많죠.'
    );
INSERT INTO UserCard (picture, title, content)
VALUES (
        'http://example.com/picture.png',
        '스몰토커',
        '완성형 토크 플레이어 말이 필요 없는 센스를 지니고 계시군요!'
    );
-- UserCard Tutorial
-- 자스민과의 대화 시작
--
-- question 3
INSERT INTO Tutorial (id, tutorialType, isStart, nextTutorialFk)
VALUES (30, 'USER_CARD', false, NULL);
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (300, 30, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (
        300,
        'JASMINE',
        'COMMON',
        '앗, 회사에 미팅이 있던 걸 깜빡했어요..!'
    );
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (301, 30, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (
        301,
        'JASMINE',
        'COMMON',
        '급하게 들어가봐야 할 것 같아요...!'
    );
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (302, 30, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (302, 'JASMINE', 'COMMON', '{userName}님 미안해요');
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (303, 30, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (303, 'JASMINE', 'COMMON', '나중에 시간 되면 한번 봬요!!');
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (304, 30, 'CHOICE');
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (305, 30, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (305, 'JASMINE', 'COMMON', '그럼 다음에 또 연락드릴게요!!');
-- question 3 reaction 1
INSERT INTO Tutorial (id, tutorialType, isStart, nextTutorialFk)
VALUES (31, 'USER_CARD', false, NULL);
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (310, 31, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (310, 'USER', 'COMMON', '네!! 좋아요!! 언제든지요!');
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (311, 31, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (311, 'USER', 'COMMON', '네 감사합니다!');
-- question 3 reaction 2
INSERT INTO Tutorial (id, tutorialType, isStart, nextTutorialFk)
VALUES (32, 'USER_CARD', false, 30);
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (320, 32, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (320, 'USER', 'COMMON', '네 좋아요! 편하게 불러주세요~');
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (321, 32, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (321, 'USER', 'COMMON', '저희 회사는 아직 한가하네요ㅎㅎ');
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (322, 32, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (322, 'JASMINE', 'COMMON', '00님이라도 한가하시니 다행이에요');
-- question 3 reaction 3
INSERT INTO Tutorial (id, tutorialType, isStart, nextTutorialFk)
VALUES (33, 'USER_CARD', false, 30);
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (330, 33, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (330, 'USER', 'COMMON', '에고ㅠㅠ 아쉽네요');
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (331, 33, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (331, 'USER', 'COMMON', '나중에 카페에서 차 한잔 해요!');
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (332, 33, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (332, 'JASMINE', 'COMMON', '저 커피 좋아한답니다ㅎㅎ');
-- question 3 choice 1
INSERT INTO TutorialMessageChoice (
        messageFk,
        reactionTutorialFk,
        choiceType,
        content
    )
VALUES (304, 31, 'REACTION', '좋아요! 저희 회사는 아직 한가하네요ㅎㅎ');
-- question 3 choice 2
INSERT INTO TutorialMessageChoice (
        messageFk,
        reactionTutorialFk,
        choiceType,
        content
    )
VALUES (304, 32, 'MY_STORY', '에고 아쉽네요 ㅠㅠ 혹시 커피 드시나요?');
-- question 3 choice 3
INSERT INTO TutorialMessageChoice (
        messageFk,
        reactionTutorialFk,
        choiceType,
        content
    )
VALUES (304, 33, 'QUESTION', '네! 좋아요 언제든지요!');
-- question 2
INSERT INTO Tutorial (id, tutorialType, isStart, nextTutorialFk)
VALUES (12, 'USER_CARD', false, 30);
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (200, 12, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (
        200,
        'JASMINE',
        'COMMON',
        '지니 님이 서로 소개해준 이유가 있는 것 같아요..!'
    );
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (201, 12, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (201, 'JASMINE', 'COMMON', '저희...왠지 잘 통할 것 같네요 !');
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (202, 12, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (
        202,
        'JASMINE',
        'COMMON',
        '{userName} 님은 좋아하는 거 있으세요 ?'
    );
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (203, 12, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (203, 'JASMINE', 'COMMON', '저는 요새 운동 시작했어요 !');
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (204, 12, 'CHOICE');
-- question 2 reaction 1
INSERT INTO Tutorial (id, tutorialType, isStart, nextTutorialFk)
VALUES (20, 'USER_CARD', false, 30);
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (210, 20, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (210, 'USER', 'COMMON', '오, 운동이라니 !');
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (211, 20, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (211, 'USER', 'COMMON', '멋지시네요 !');
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (212, 20, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (212, 'USER', 'COMMON', '감사합니다 ㅎㅎ');
-- question 2 reaction 2
INSERT INTO Tutorial (id, tutorialType, isStart, nextTutorialFk)
VALUES (21, 'USER_CARD', false, 30);
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (220, 21, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (220, 'USER', 'COMMON', '저도 운동 좋아해요!');
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (221, 21, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (221, 'USER', 'COMMON', '어렸을 땐 축구를 많이 했죠ㅎㅎ');
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (222, 21, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (222, 'JASMINE', 'COMMON', '오 축구 좋아하셨나봐요! 멋지시네요!');
-- question 2 reaction 3
INSERT INTO Tutorial (id, tutorialType, isStart, nextTutorialFk)
VALUES (22, 'USER_CARD', false, 30);
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (230, 22, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (230, 'USER', 'COMMON', '멋지시네요! 어떤 운동 하세요?');
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (231, 22, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (231, 'JASMINE', 'COMMON', '저는 필라테스 다녀요. ㅎㅎ');
-- question 2 choice 1
INSERT INTO TutorialMessageChoice (
        messageFk,
        reactionTutorialFk,
        choiceType,
        content
    )
VALUES (204, 20, 'REACTION', '어떤 운동 하세요 ?');
-- question 2 choice 2
INSERT INTO TutorialMessageChoice (
        messageFk,
        reactionTutorialFk,
        choiceType,
        content
    )
VALUES (204, 21, 'MY_STORY', '저도 운동 좋아해요 !');
-- question 2 choice 3
INSERT INTO TutorialMessageChoice (
        messageFk,
        reactionTutorialFk,
        choiceType,
        content
    )
VALUES (204, 22, 'QUESTION', '오, 운동이라니 멋지시네요 !');
-- question 1
INSERT INTO Tutorial (id, tutorialType, isStart, nextTutorialFk)
VALUES (4, 'USER_CARD', TRUE, 12);
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (100, 4, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (100, 'JASMINE', 'COMMON', '안녕하세요!');
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (101, 4, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (101, 'JASMINE', 'COMMON', '지니님 소개 받고 연락드려요!');
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (102, 4, 'CHOICE');
-- question 1 reaction 1
INSERT INTO Tutorial (id, tutorialType, isStart, nextTutorialFk)
VALUES (10, 'USER_CARD', false, 12);
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (111, 10, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (111, 'USER', 'COMMON', '아 네 ! 안녕하세요 !');
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (112, 10, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (
        112,
        'JASMINE',
        'COMMON',
        '지니님 덕에 좋은 분 알게 되었네요ㅎㅎ'
    );
-- question 1 reaction 2
INSERT INTO Tutorial (id, tutorialType, isStart, nextTutorialFk)
VALUES (11, 'USER_CARD', false, 12);
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (121, 11, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (121, 'USER', 'COMMON', '아 네 ! 안녕하세요 !');
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (122, 11, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (122, 'USER', 'COMMON', '저는 지니님 방금 알게 됐는데');
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (123, 11, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (123, 'USER', 'COMMON', '덕분에 좋은 인연 알게 되었네요ㅎㅎ');
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (124, 11, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (124, 'JASMINE', 'COMMON', '그러게요ㅎㅎ');
-- question 1 reaction 3
INSERT INTO Tutorial (id, tutorialType, isStart, nextTutorialFk)
VALUES (13, 'USER_CARD', false, 12);
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (131, 13, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (131, 'USER', 'COMMON', '아 네 ! 안녕하세요 !');
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (132, 13, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (132, 'USER', 'COMMON', '지니님은 어떻게 아시게 된건가요 ?');
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (133, 13, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (133, 'JASMINE', 'COMMON', '지니 님이 회사 선배예요 ! ㅎㅎ');
-- question 1 choice 1
INSERT INTO TutorialMessageChoice (
        messageFk,
        reactionTutorialFk,
        choiceType,
        content
    )
VALUES (102, 10, 'REACTION', '아 네 안녕하세요 !');
-- question 1 choice 2
INSERT INTO TutorialMessageChoice (
        messageFk,
        reactionTutorialFk,
        choiceType,
        content
    )
VALUES (102, 11, 'MY_STORY', '덕분에 좋은 인연 알게되었네요ㅎㅎ');
-- question 1 choice 3
INSERT INTO TutorialMessageChoice (
        messageFk,
        reactionTutorialFk,
        choiceType,
        content
    )
VALUES (102, 12, 'QUESTION', '지니님은 어떻게 아시게 된건가요 ?');
--Profile Tutorial
-- 지니와의 대화 시작
INSERT INTO Tutorial (id, tutorialType, isStart)
VALUES (1, 'PROFILE', TRUE);
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (1, 1, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (1, 'GENIE', 'COMMON', '안녕하세요 !');
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (2, 1, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (2, 'GENIE', 'COMMON', '저는 당신의 고민을 들어줄 지니예요 !');
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (3, 1, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (3, 'GENIE', 'COMMON', '앞으로 당신을 뭐라고 부르면 될까요 ?');
-- INPUT
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (4, 1, 'INPUT');
INSERT INTO TutorialMessageInput (messageFk, inputType)
VALUES (4, 'USER_NAME');
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (5, 1, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (
        5,
        'GENIE',
        'COMMON',
        '{ userName } 님 ! 멋진 이름이에요 !'
    );
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (6, 1, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (
        6,
        'GENIE',
        'COMMON',
        '음, 우선 { userName } 님이 어떤 대화 스타일을 가지고 있는지 알아보고 싶어요 !'
    );
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (7, 1, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (
        7,
        'GENIE',
        'COMMON',
        '원하신다면 제 친구 자스민을 소개시켜드릴게요 !'
    );
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (8, 1, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (
        8,
        'GENIE',
        'COMMON',
        '자스민과의 대화가 시작되면 선택지 중 원하는 답안을 골라주세요'
    );
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (9, 1, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (9, 'GENIE', 'COMMON', '준비 되셨나요 ?');
--준비 됐어요 reaction
INSERT INTO Tutorial (id, tutorialType, isStart, nextTutorialFk)
VALUES (2, 'PROFILE', false, 4);
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (10, 2, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (10, 'USER', 'COMMON', '네, 준비 됐어요 !');
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (11, 2, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (11, 'GENIE', 'COMMON', '좋아요 !');
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (12, 2, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (12, 'GENIE', 'COMMON', '그럼 바로 자스민과 연결해드릴게요 !');
-- 아직 마음의 준비가..... reaction
INSERT INTO Tutorial (id, tutorialType, isStart, nextTutorialFk)
VALUES (3, 'PROFILE', false, NULL);
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (20, 2, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (20, 'USER', 'COMMON', '아직 마음의 준비가.....');
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (21, 2, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (21, 'GENIE', 'COMMON', '괜찮아요 ! 그럴 수 있어요..!');
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (22, 2, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (22, 'GENIE', 'COMMON', '아쉽지만 마음의 준비가 됐을 때');
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (23, 2, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (23, 'GENIE', 'COMMON', '다시 만나요 !');
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (24, 2, 'TEXT');
INSERT INTO TutorialMessageText (messageFk, actorType, textType, content)
VALUES (24, 'GENIE', 'NARRATION', '지니와의 대화가 종료되었습니다.');
-- 준비 됐어요 선택지
INSERT INTO TutorialMessage (id, tutorialFk, messageType)
VALUES (30, 1, 'CHOICE');
INSERT INTO TutorialMessageChoice (
        messageFk,
        reactionTutorialFk,
        choiceType,
        content
    )
VALUES (30, 2, 'COMMON', '네, 준비 됐어요 !');
-- 아직 마음의 준비가 선택지
INSERT INTO TutorialMessageChoice (
        messageFk,
        reactionTutorialFk,
        choiceType,
        content
    )
VALUES (30, 3, 'COMMON', '아직 마음의 준비가.....');