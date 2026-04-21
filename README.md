# backend

Spring Boot 기반 REST API 서버

## 기술 스택
- Java 17
- Spring Boot
- Spring Security + JWT
- JPA & Spring Data JPA
- PostgreSQL (메인 DB)
- Redis (캐시)
- Swagger (API 문서화)

## 주요 기능
- 회원가입 / 로그인 (JWT 인증)
- 마이페이지 (내정보/비밀번호변경/회원탈퇴)
- 축제 목록 / 상세 조회
- 축제 필터 (테마별)
- 축제 후기 등록 / 조회
- 게시판 글 / 댓글 / 좋아요
  
## DB 접속 정보
- host: localhost
- port: 5432
- database: festivaldb
- user: admin
- password: password

## 브랜치 규칙
- feature/기능명 → 기능 개발
- main → 최종 배포 브랜치

### CRUD
users

- GET/me -> 내 정보 조회

## CRUD
### users
- GET/mypage -> 내 정보 조회

- POST/register -> 회원가입

### auth
- POST/login -> 로그인

- **GET**/me -> 내 정보 조회
- **POST**/register -> 회원가입

auth
- **POST**/login -> 로그인




---

- - -




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

