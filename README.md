# Library App MemberCard Service / 会員カード管理サービス

Distributed Library System — MemberCard Service

分散型図書館システム — 会員カード管理サービス

---

## Overview / 概要

The MemberCard Service is an internal event-driven microservice within the distributed library platform.

It is responsible for consuming user registration events through Kafka, generating membership card records, and
maintaining persistent membership state.

The service operates asynchronously and acts as the source of truth for member card lifecycle management.

---

MemberCard Service は分散型図書館システムにおける内部イベント駆動型会員カード管理サービスです。

Kafka 経由でユーザー登録イベントを非同期に受信し、会員カード情報を生成・永続化します。

本サービスは非同期に動作し、会員カードライフサイクル管理の単一責任ソースとして機能します。

---

## Service Boundaries / サービス境界

### Provides

- User registration event consumption
- Member card generation
- Card expiration management
- Membership persistence
- Membership lifecycle consistency
- Internal membership state management

### Does Not Handle

- User authentication
- Credential management
- Client-facing APIs
- Catalog operations
- Borrow transaction execution
- Notification dispatching

---

## Badges

<!-- Code Quality & Tests -->
[![Tests](https://github.com/damouu/library-app-membercard/actions/workflows/run-tests.yml/badge.svg?branch=test)](https://github.com/damouu/library-app-membercard/actions/workflows/run-tests.yml)

[![Merge PR](https://github.com/damouu/library-app-membercard/actions/workflows/merge-pr.yml/badge.svg)](https://github.com/damouu/library-app-membercard/actions/workflows/merge-pr.yml)

[![Prepare](https://github.com/damouu/library-app-membercard/actions/workflows/prepare.yml/badge.svg)](https://github.com/damouu/library-app-membercard/actions/workflows/prepare.yml)

<!-- Coverage -->
[![Codecov](https://codecov.io/gh/damouu/library-app-membercard/branch/test/graph/badge.svg)](https://codecov.io/gh/damouu/library-app-membercard)

<!-- Git / Version -->
[![Git Tag](https://img.shields.io/github/v/tag/damouu/library-app-membercard?logo=github)](https://github.com/damouu/library-app-membercard/tags)

<!-- Observability -->
![Kafka](https://img.shields.io/badge/Kafka-integrated-orange)

![Prometheus](https://img.shields.io/badge/Prometheus-monitored-blue)

---

## Responsibilities / 責務

### English

- Consume registration events
- Generate member cards
- Manage card validity
- Persist membership records
- Maintain lifecycle consistency
- Handle asynchronous membership processing

### 日本語

- 登録イベント処理
- 会員カード生成
- 有効期限管理
- 会員情報永続化
- ライフサイクル整合性維持
- 非同期会員処理

---

## Technology Stack / 技術スタック

| Category    | Technology                             |
|-------------|----------------------------------------|
| Runtime     | Java 9                                 |
| Framework   | Spring Boot 2.7                        |
| Messaging   | Kafka                                  |
| Persistence | Spring Data JPA                        |
| Database    | PostgreSQL / H2                        |
| Validation  | Bean Validation                        |
| Monitoring  | Micrometer / Prometheus / Actuator     |
| Testing     | JUnit 5 / Mockito / JaCoCo / Instancio |
| CI/CD       | GitHub Actions                         |

---

## Event Processing / イベント処理

Consumes Kafka user registration events.

Processes:

- User registration events
- Member card generation
- Membership persistence
- Expiration initialization

---

## Example Event Payload

```json
{
  "userId": "550e8400-e29b-41d4-a716-446655440000",
  "email": "user@example.com",
  "registeredAt": "2026-05-05T10:00:00Z"
}
```

---

## Processing Pipeline / 処理パイプライン

User Registration Event  
↓  
Kafka Consumer  
↓  
MemberCard Generation Layer  
↓  
Database Persistence

---

## Local Development / ローカル開発

### Requirements

- Java 9
- Maven
- Docker
- PostgreSQL
- Kafka

---

### Run

```bash
docker compose up --build
```

---

## Testing / テスト

```bash
./mvnw verify
```

Includes:

- Unit tests
- Integration tests
- Coverage verification

---

## Coverage Policy / カバレッジポリシー

JaCoCo quality gates:

- **Line coverage ≥ 80%**
- **Complexity missed count ≤ 3**

Excluded from coverage:

- DTO classes
- Model classes
- Bootstrap application class

---

## Configuration / 設定

```env
SPRING_DATASOURCE_URL=
SPRING_DATASOURCE_USERNAME=
SPRING_DATASOURCE_PASSWORD=
SPRING_KAFKA_BOOTSTRAP_SERVERS=
```

Environment-driven configuration.

---

## Monitoring / モニタリング

```text
/actuator/health
/actuator/prometheus
/actuator/metrics
```

---

## Build Quality / 品質保証

The build pipeline enforces:

- Automated test execution
- Coverage thresholds
- Pull request validation
- CI verification
- Workflow consistency checks

---

## Future Improvements / 今後の改善

- Membership renewal automation
- Tier-based membership support
- Card reissue workflows
- Event publication after card creation
- Distributed tracing

---

## License / ライセンス

MIT