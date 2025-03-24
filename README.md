
## **API**

---
### 일정 작성 API
URL: POST /schedules

설명: 일정 등록

- Request Body
```json
{
  "title": "제목1",
  "contents": "내용1",
  "startTime": "2025-03-26T10:00:00",
  "endTime": "2025-03-26T12:00:00",
  "password": "1234",
  "name": "이름1"
}
```

- Response
```json
{
  "id": 1,
  "title": "제목1",
  "contents": "내용1",
  "startTime": "2025-03-26T10:00:00",
  "endTime": "2025-03-26T12:00:00",
  "createdAt": "2025-03-24T15:26:21.0372115",
  "name": "이름1"
}
```
---

### 일정 ID 기반 단건 조회 API
URL: GET /schedules/{id}

설명: ID기반 일정 단건 조회

- Response
```json
{
  "id": 1,
  "title": "제목1",
  "contents": "내용1",
  "startTime": "2025-03-26T10:00:00",
  "endTime": "2025-03-26T12:00:00",
  "createdAt": "2025-03-24T15:26:21",
  "name": "이름1"
}
```
---

### 일정 전체 조회 API
URL: GET /schedules

설명: 일정 전건 조회 및 최신순 정렬

- Response
```json
[
  {
    "id": 1,
    "title": "제목1",
    "contents": "내용1",
    "startTime": "2025-03-26T10:00:00",
    "endTime": "2025-03-26T12:00:00",
    "createdAt": "2025-03-24T15:26:21",
    "name": "이름1"
  },
  {
    "id": 2,
    "title": "제목2",
    "contents": "내용2",
    "startTime": "2025-03-27T09:00:00",
    "endTime": "2025-03-27T11:00:00",
    "createdAt": "2025-03-24T15:30:00",
    "name": "이름2"
  }
]
```
---

### 일정 수정 API
URL: PUT /schedules/{id}

설명: 일정 수정 및 비밀번호 확인

- Request Body
```json
{
  "password": "1234",
  "title": "수정된 제목",
  "contents": "수정된 내용"
}
```

- Response
```json
{
  "id": 1,
  "title": "수정된 제목",
  "contents": "수정된 내용",
  "startTime": "2025-03-26T10:00:00",
  "endTime": "2025-03-26T12:00:00",
  "createdAt": "2025-03-24T16:00:00",
  "name": "이름1"
}
```
- 예외
    1. 비밀번호 불일치 -> `401 Unauthorized`
  2. ID가 존재하지 않음 -> `404 Not Found`

---

### 일정 삭제 API
URL: DELETE /schedules/{id}

설명: 비밀번호 인증 후 일정 삭제

- Request Body
```json
{
  "password": "1234"
}
```
- 예외
    1. 비밀번호 불일치 -> `401 Unauthorized`
    2. ID가 존재하지 않음 -> `404 Not Found`

<br>
<br>

## **ERD**

---
