
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
  "time": "2025-03-19T18:20:02.3769366"
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
  "time": "2025-03-19T18:28:43.0722007"
}
```
---

### 게시글 수정 API

Request(요청)
```json
{
    "Name": "이름",
    "Password": "1234",
    "Title": "수정 제목",
    "Contents": "수정 내용",
    "EditTime": "YYYY-MM-DDTHH:MM:SS",
    "Edit": true
}
```

Response(응답)
```json
{
    "Id": 1,
    "Name": "이름",
    "Password": "1234",
    "Title": "제목",
    "Contents": "내용",
    "EditTime": "YYYY-MM-DDTHH:MM:SS",
    "Edit": true
}
```

---

### 게시글 삭제 API

Request(요청)
```json
{
    "Id": 1,
    "Password": "1234",
    "Del": true
}
```
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
