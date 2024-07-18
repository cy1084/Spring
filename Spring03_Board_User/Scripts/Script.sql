--전체조회
SELECT SEQ, ID, TITLE, "DEPTH", STEP , "REF" , DELFLAG ,REGDATE 
	FROM ANSWERBOARD a ;
	
--삭제
UPDATE ANSWERBOARD SET DELFLAG = 'Y'
WHERE DELFLAG = 'N'
AND SEQ IN('1', '2', '3');

--글 입력
INSERT INTO ANSWERBOARD
(SEQ, ID, TITLE, CONTENT, "REF", STEP, "DEPTH", REGDATE)
VALUES(ANSWERBOARD_SEQ.NEXTVAL, 'A001', '월요일 좋아', '미쳤나봄', (SELECT NVL(MAX(REF),0)+1 FROM ANSWERBOARD a), 0, 0, CURRENT_DATE);

--
SELECT SEQ, ID, TITLE, CONTENT, "REF", STEP, "DEPTH", DELFLAG, TO_CHAR(REGDATE, 'YYYY/MM/DD HH:mi:ss') AS REGDATE 
	FROM ANSWERBOARD a
	WHERE SEQ = 30;
	
--답글 업데이트
UPDATE ANSWERBOARD SET STEP = STEP + 1
	WHERE "REF" = (SELECT "REF" FROM ANSWERBOARD a WHERE SEQ = '30')
	AND STEP > (SELECT STEP FROM ANSWERBOARD a2 WHERE SEQ = '30');

--답글 입력
INSERT INTO ANSWERBOARD
(SEQ, ID, TITLE, CONTENT, "REF", STEP, "DEPTH", REGDATE)
VALUES(ANSWERBOARD_SEQ.NEXTVAL, 'A001', '월요일 답글', '답글 미쳤나봄',
(SELECT REF FROM ANSWERBOARD a WHERE SEQ = '30'),
(SELECT STEP+1 FROM ANSWERBOARD a2 WHERE SEQ = '30'),
(SELECT "DEPTH"+1 FROM ANSWERBOARD a3 WHERE SEQ = '30') , CURRENT_DATE);


--로그인
SELECT ID, NAME, EMAIL, AUTH, JOININDATE 
	FROM USERINFO u
	WHERE ID = 'A001' AND PASSWORD = 'A001';

--중복검사
SELECT COUNT(*)
	FROM USERINFO u
	WHERE ID = 'A005';

--회원가입
INSERT INTO USERINFO
(ID, NAME, PASSWORD, EMAIL, AUTH, ENABLE, JOININDATE)
VALUES('A008', 'A008', 'A008', 'A008@gmai.com', 'U', 'Y', CURRENT_DATE);

--회원 전체 조회
SELECT ID, NAME 
	FROM USERINFO u; 

--회원찾기(아이디검색)
SELECT ID, NAME 
	FROM USERINFO u
	WHERE AUTH = 'U'
	WHERE ID = 'A001';

--회원찾기(닉네임검색)
SELECT ID, NANE
	FROM USERINFO u
	WHERE AUTH = 'U'
	AND NAME = 'A008';

--아이디 찾기
SELECT ID
	FROM USERINFO u
	WHERE NAME = 'A008'
	AND EMAIL = 'A008@gmai.com';
	
--삭제게시글 정보보기
SELECT SEQ, ID, TITLE, CONTENT, "REF", STEP, "DEPTH", DELFLAG, TO_CHAR(REGDATE, 'YYYY/MM/DD HH:mi:ss') AS REGDATE 
	FROM ANSWERBOARD a 
	WHERE DELFLAG = 'Y';

--삭제게시글 복구
UPDATE ANSWERBOARD SET DELFLAG = 'N'
WHERE SEQ IN ('1', '2', '3');

--
SELECT 'A' || LPAD(MAX(SUBSTR(ID, 4, 1))+1, 4, '0')
	FROM USERINFO u;

UPDATE USERINFO SET AUTH='U'
WHERE ID IN ('a','b');

SELECT SEQ, ID,  LPAD(' ', 5*"DEPTH" ,'~') ||
			CASE
			WHEN "DEPTH">0 THEN '<img src=''./img/reply.png''>'
			END ||
			TITLE AS TITLE, "DEPTH"
			"DEPTH" , STEP , "REF" , DELFLAG , REGDATE
		FROM ANSWERBOARD a
		WHERE DELFLAG='N'
		ORDER BY REF DESC;

