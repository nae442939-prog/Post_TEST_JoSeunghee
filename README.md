# Post_TEST - 스프링 부트 MVC 게시판

Spring Boot + MyBatis + Thymeleaf + MySQL 로 만든 게시판입니다.

## 기능
- 게시글 등록, 목록(페이징), 조회, 수정, 삭제

## 구조
```
src/main/java/com/example/board
 ├─ BoardApplication.java
 ├─ controller/PostController.java
 ├─ dto/Post.java, Page.java
 ├─ mapper/PostMapper.java
 └─ service/PostService.java, PostServiceImpl.java
src/main/resources
 ├─ mapper/PostMapper.xml
 ├─ templates/index.html
 ├─ templates/Post/list.html, read.html, insert.html, update.html
 ├─ schema.sql
 └─ application.properties
```

## 실행
1. MySQL에 `board` 데이터베이스 생성 : `CREATE DATABASE board;`
2. `application.properties`의 DB 계정/비밀번호 수정
3. `BoardApplication` 실행 후 http://localhost:8080 접속
