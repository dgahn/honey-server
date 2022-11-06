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

```
POST /api/v1/point/earn
```

- 포인트 적립은 상점의 업종별로 통합하여 적립
- 등록된 상점이 아닌 경우 상점 오류 에러를 반환한다.
- 등록된 바코드가 아닌 경우 바코드 오류 에러를 반환한다.

#### Request Body Param

| Name           | Type   | Description                | 
|----------------|--------|----------------------------|
| membershipCode | String | 멤버십 바코드, 10자리 숫자형 스트링으로 구성 |
| point          | Long   | 적립할 포인트                    |
| partnerId      | Long   | 상점 ID                      |

#### Response Body Param

| Name    | Type | Description      |
|---------|------|------------------|
| pointId | Long | 멤버십과 업종별 포인트 식별자 |

### 포인트 사용 API

```
POST /api/v1/point/use
```

- 포인트 적립은 상점의 업종별로 통합하여 사용
- 적립 포인트를 초과하는 포인트 사용으로 인한 부족 에러 반환
- 등록된 상점이 아닌 경우 상점 오류 에러를 반환한다.
- 등록된 바코드가 아닌 경우 바코드 오류 에러를 반환한다.

#### Request Body Param

| Name           | Type   | Description                | 
|----------------|--------|----------------------------|
| membershipCode | String | 멤버십 바코드, 10자리 숫자형 스트링으로 구성 |
| point          | Long   | 적립할 포인트                    |
| partnerId      | Long   | 상점 ID                      |

#### Response Body Param

| Name    | Type | Description      |
|---------|------|------------------|
| pointId | Long | 멤버십과 업종별 포인트 식별자 |

### 내역 조회 API

```
GET /api/v1/point-histories
```

- 등록된 바코드가 아닌 경우 바코드 오류 에러를 반환한다.

#### Request Query Param

| Name           | Type   | Description                    | 
|----------------|--------|--------------------------------|
| startAt        | String | 조회할 포인트를 적립/사용한 시기의 시작기간       |
| endAt          | String | 조회할 포인트를 적립/사용한 시기의 끝기간        |
| membershipCode | String | 멤버십 바코드, 10자리 숫자형 스트링으로 구성     |
| page           | Int    | 페이지 번호(기본 값: 1)                |
| size           | Int    | 페이지에 보이는 목록 갯수(기본 값: 10)       |

#### Response Body Param

| Name           | Type   | Description                |
|----------------|--------|----------------------------|
| approvedAt     | Date   | 포인트를 적립/사용한 시기             |
| type           | Int    | 적립/사용한지에 대한 값              |
| membershipCode | String | 멤버십 바코드, 10자리 숫자형 스트링으로 구성 |
| category       | Int    | 업종                         |
| partnerName    | Int    | 상점 이름                      |

### 해결 전략

#### 멤버십 바코드 생성 전략

- 숫자형 스트링을 랜던으로 생성하도록 함
- 중복된 멤버십 바코드가 생성되면 안되기 때문에 DB에서 확인함
- 중복된 멤버십 바코드를 생성할 것을 대비하여 5번의 재생성 시도를 진행함.

#### 테스트 코드 범위

- 주로 단위 테스트로 해당 클래스(함수)내의 기능인 경우에만 단위 테스트를 작성
- 통합테스트가 필요할 때(예, 실제 데이터베이스와 통합해서 테스트해야하는 경우)에만 통합테스트 작성

#### 인덱스 전략

- DDL은 `database/ddl.sql`에 존재함
- `membership` 테이블
    - id가 code이지만 멤버십 바코드를 생성할 때마다 userId로 만들어진 멤버십 코드가 있는지 확인하기 때문에 인덱스를 추가함.
- `point` 테이블
    - `membership_code`와 `category` 조회하는 경우가 많기 때문에 인덱스 추가
    - 추후 다른 쿼리가 추가될 때도 `membership_code`로 한번에 조회하는 경우가 더 많을 것으로 예측 되어 `membership_code`를 순서 앞으로 둠
    - 추가적으로 카디널리티도 `membership_code`가 더 높을 것으로 예상됨.
- `point_history` 테이블
    - `membership_code`와 `approved_at`로 조회하여 인덱스를 추가함
    - 사용자가 많은 경우 시간에 의해 필터링되는 것보다 `membership_code`에 의해 필터링되는 요소가 더 많을 것으로 생각됨.