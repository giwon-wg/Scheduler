
## **API**

---
### 게시글 작성 API
Request(요청)
```json
{
    "Name": "이름",
    "Password": "1234",
    "Title": "제목",
    "Contents": "내용",
    "Time": "YYYY-MM-DDTHH:MM:SS",
    "EditTime": null,
    "Del": false,
    "Edit": false
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
    "Time": "YYYY-MM-DDTHH:MM:SS",
    "EditTime": null,
    "Del": false,
    "Edit": false
}
```
---

### 게시글 조회 API

Response(응답)
```json
{
    "Id": 1,
    "Name": "이름",
    "Password": "1234",
    "Title": "제목",
    "Contents": "내용",
    "Time": "YYYY-MM-DDTHH:MM:SS",
    "EditTime": null,
    "Del": false,
    "Edit": false
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
