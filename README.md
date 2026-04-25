# CRUD
### users
- GET /mypage -> 내 정보 조회
- POST /register -> 회원가입

### auth
- POST /login -> 로그인

## festivals
- GET /{id} -> 상세정보
- GET / -> 축제 정보페이지(필터,검색,축제 띄우기)

### 랜딩페이지
- GET /recent -> 배너 밑 카드 섹션(현재 날짜 기준으로 곧 시작하는 축제 7개)
- GET /month -> 가운데(오늘 기준 최근 7일 ~ 앞으로 30일 사이 시작하는, 아직 안 끝난 축제를 시작일 빠른순으로 4개 가져온다. -> 이달의 축제 느낌)
- GET /recommend -> 하단(랜덤으로 4개 뽑기)

### api/board
- GET / -> 게시글 목록 조회
- GET /{postId} -> 게시글 상세 조회
- POST / -> 게시글 작성
- PUT /{postId} -> 게시글 수정
- DELETE /{postId} -> 게시글 삭제
- POST /{postId}/like -> 좋아요
- GET /{postId}/comments -> 댓글 목록 조회
- POST /{postId}/comments -> 댓글 작성
- PUT /comments/{commentId} -> 댓글 수정
- DELETE /comments/{commentId} -> 댓글 삭제
---

#### 회원가입
- 아이디(이메일)
  - 중복확인
  - 필수입력
  - 이메일 형식 입력
- 패스워드
  - 필수입력
  - 비밀번호 확인(일치/불일치)
- 닉네임
  - 중복확인
  - 필수입력
- 이미지
  - 기본 이미지 -> default 이미지 변경 해야 됨(지금은 임의로 넣어둠)
  - 이미지 변경

#### 내 정보 조회
- 이미지
- 닉네임
- 이메일
