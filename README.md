# backend

Spring Boot 기반 REST API 서버

## 기술 스택
- Java 17
- Spring Boot 3.x
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
- AI 챗봇 서비스 연동

## DB 접속 정보
- host: localhost
- port: 5432
- database: festivaldb
- user: admin
- password: password

## 브랜치 규칙
- feature/기능명 → 기능 개발
- fix/버그명 → 버그 수정
- main → 최종 배포 브랜치
