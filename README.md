
## **API**

---
### 게시글 작성 API
Request(요청)
```json
{
  "password": "1234",
  "name": "이름",
  "title": "제목1",
  "contents": "내용1"
}
```

Response(응답)
```json
{
  "id": 1,
  "password": "1234",
  "name": "이름",
  "title": "제목1",
  "contents": "내용1",
  "time": "2025-03-21T13:17:44.5047075"
}
```
---

### 게시글 단건 조회 API

Response(응답)
```json
{
  "id": 6,
  "password": "1234",
  "name": "이름",
  "title": "제목1",
  "contents": "내용1",
  "time": "2025-03-21T13:17:45"
}
```

### 게시글 전체 조회 API

Response(응답)
```json
[
  {
    "id": 1,
    "password": "1234",
    "name": "이름",
    "title": "제목1",
    "contents": "내용1",
    "time": "2025-03-21T13:17:45"
  },
  
  {
    "id": 2,
    "password": "1234",
    "name": "이름",
    "title": "제목2",
    "contents": "내용2",
    "time": "2025-03-21T13:17:45"
  }
]
```
---

### 게시글 수정 API

Request(요청)
```json
{
  "password": "1234",
  "title": "수정 제목1",
  "contents": "수정 내용1"
}
```

Response(응답)
```json
{
  "id": 1,
  "password": "1234",
  "name": "이름",
  "title": "수정 제목1",
  "contents": "수정 내용1",
  "time": "2025-03-21T13:19:36"
}
```

---

### 게시글 삭제 API

Request(요청)
```json
{
  "password": "1234"
}
```

<br>
<br>

## **ERD**

---
