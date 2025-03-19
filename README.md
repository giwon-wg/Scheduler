
## **API**

---
### 게시글 작성 API
Request(요청)
```json
{
  "password": "1234abc",
  "name": "이름",
  "title": "제목1",
  "contents": "내용1"
}
```

Response(응답)
```json
{
  "id": 1,
  "password": "1234abc",
  "name": "이름",
  "title": "제목1",
  "contents": "내용1",
  "time": "YYYY-MM-DDTHH:MM:SS"
}
```
---

### 게시글 조회 API

Response(응답)
```json
{
  "id": 1,
  "password": "1234abc",
  "name": "이름",
  "title": "제목1",
  "contents": "내용1",
  "time": "YYYY-MM-DDTHH:MM:SS"
}
```
---

### 게시글 수정 API

Request(요청)
```json
{
  "title": "수정 제목",
  "contents": "수정 내용"
}
```

Response(응답)
```json
{
  "id": 1,
  "password": "1234abc",
  "name": "giwon",
  "title": "수정 제목ㅁㄴㅇ",
  "contents": "수정 내용ㅁㄴㅇ",
  "time": "YYYY-MM-DDTHH:MM:SS"
}
```

---

### 게시글 삭제 API

Response(응답)
```json
{
    "Message": "삭제문구"
}
```

<br>
<br>

## **ERD**

---
