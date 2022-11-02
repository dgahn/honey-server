# Honey 서버

멤버십 서비스를 구현합니다.

## 서비스 특징

- 사용자는 하나의 `멤버십 바코드`를 가질 수 있다.
- 멤버십을 활용하여 상점에서 `포인트 적립` 또는 `사용`이 가능하다.
- 상점은 `상점명`과 하나의 `업종 정보`를 가지고 있다.
- 같은 업종의 가맹점들은 `적립된 포인트`를 `공유`할 수 있다.
- 업종의 종류
    - `식품`
    - `화장품`
    - `식당`
- 한 상점은 `하나의 업종 정보`만 가지고 있고 변경될 수 없다.
- 사용자 탈퇴는 없고 발급된 바코드 계속 유효하다.
- 발급된 멤버십 바코드는 `가족이나 친구끼리 공유`할 수 있다.

## 요구사항

- API 목록
    - 통합 바코드 발급 API
    - 포인트 적립 API
    - 포인트 사용 API
    - 기간별 내역 조회 API
- 다수의 서버에 다수의 인스턴스에 동작해도 문제가 없도록 구현
- 멤버십 바코드는 공유되기 때문에 사용, 적립이 동시에 들어올 수도 있음
- 각 기능 및 제약사항에 대해서 단위 테스트 작성

## API 상세

### 통합 바코드 발급 API

```
POST /api/v1/memberships
```

- 발급될 바코드는 다른 사람과 중복될 수 없음.
- 다음번에 발급될 멤버십 바코드가 예측가능하면 안됨.
- 이미 발급한 사용자의 멤버십 바코드인 경우 기존 멤버십 바코드를 전달해야 함.

#### Request Body Param

| Name   | Type | Description                               | 
|--------|------|-------------------------------------------|
| userId | Int  | 멤버 바코드를 발급 받을 사용자의 식별자, 9자리 숫자형 스트링으로 구성 |

#### Response Body Param

| Name           | Type   | Description                 | 
|----------------|--------|-----------------------------|
| membershipCode | String | 멤버십 바코드, 10자리 숫자형 스트링으로 구성  |

### 포인트 적립 API

- 포인트 적립은 상점의 업종별로 통합하여 적립
- 등록된 상점이 아닌 경우 상점 오류 에러를 반환한다.
- 등록된 바코드가 아닌 경우 바코드 오류 에러를 반환한다.

#### Request Body Param

| Name           | Type   | Description                | 
|----------------|--------|----------------------------|
| category         | Int    | 업종                      |
| membershipCode | String | 멤버십 바코드, 10자리 숫자형 스트링으로 구성 |
| point          | Int    | 적립할 포인트                    |

#### Response Body Param

| Name             | Type | Description                |
|------------------|------|----------------------------|
| membershipCode   | String | 멤버십 바코드, 10자리 숫자형 스트링으로 구성 |
| category | Int    | 업종                         |
| totalPoint       | Int    | 해당 업종의 총 Point             |

### 포인트 사용 API

- 포인트 적립은 상점의 업종별로 통합하여 사용
- 적립 포인트를 초과하는 포인트 푸족 에러 반환
- 등록된 상점이 아닌 경우 상점 오류 에러를 반환한다.
- 등록된 바코드가 아닌 경우 바코드 오류 에러를 반환한다.

#### Request Body Param

| Name           | Type   | Description                | 
|----------------|--------|----------------------------|
| category         | Int    | 업종                      |
| membershipCode | String | 멤버십 바코드, 10자리 숫자형 스트링으로 구성 |
| point          | Int    | 적립할 포인트                    |

#### Response Body Param

| Name             | Type | Description                |
|------------------|------|----------------------------|
| membershipCode   | String | 멤버십 바코드, 10자리 숫자형 스트링으로 구성 |
| category | Int    | 업종                         |
| totalPoint       | Int    | 해당 업종의 총 Point             |

### 내역 조회 API

#### Request Param

- 등록된 바코드가 아닌 경우 바코드 오류 에러를 반환한다.

| Param Type     | Name    | Type   | Description              | 
|----------------|---------|--------|--------------------------|
| query          | startAt | Int    | 조회할 포인트를 적립/사용한 시기의 시작기간 |
| query          | endAt   | String | 조회할 포인트를 적립/사용한 시기의 끝기간  |
| membershipCode | point   | Int    | 멤버십 코드               |

#### Response Param

| Name        | Type | Description    |
|-------------|------|----------------|
| approvedAt  | Date | 포인트를 적립/사용한 시기 |
| type        | Int  | 적립/사용한지에 대한 값  |
| category    | Int  | 업종             |
| partnerName | Int  | 상점 이름          |