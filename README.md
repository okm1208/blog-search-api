#### 블로그 검색 서비스
- 오픈 API를 활용하여 블로그 검색 API 제공

#### 기능
- 블로그 검색 
    - 카카오, 네이버 블로그 검색 API를 활용한 블로그 검색 기능
- 인기 검색어 제공 
    - 많이 검색된 키워드 TOP10 리스트 제공  

#### DB 설계
* 키워드 이력 테이블 ( keyword_history )
  * 키워드 검색시 ApplicationEventPublisher 를 통한 검색 쿼리를 저장
* 키워드 검색 통계 테이블 ( keyword_history_statistics )
  * 한시간 마다 키워드 이력 테이블을 조회 하여 키워드별 검색 카운트 집계 수행

#### build 
```bash
$ ./gradlew clean build
```

#### Run
```bash
$ java -jar build/libs/kakao-blog-search-assignement-0.0.1.jar
```
#### DB (h2) 접속
```bash
http://localhost:8080/h2
```
- id : admin
- password: admin1234

#### Swaager UI 
```bash
http://localhost:8080/swagger-ui/index.html
```

#### 개발 환경
- JDK 11
- gradle-8.4
- Spring boot 2.7.17
- H2 Database

#### Open Source
- [Spring Framework](https://spring.io/)
- [Junit](https://junit.org/junit5/)
- [Lombok](https://projectlombok.org/)
- [H2 Database](https://www.h2database.com/html/main.html)
- [Swagger](https://swagger.io/)
    