# Library App MemberCard Service

Distributed Library System — MemberCard Service

---

## Overview

The MemberCard Service is an internal event-driven microservice within the distributed library platform.

It is responsible for consuming user registration events through Kafka, generating membership card records, and maintaining persistent membership state.

The service operates asynchronously and acts as the source of truth for member card lifecycle management.

---

## Service Boundaries

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
- Member card transaction processing
- Notification dispatching

---

## Badges

<!-- Code Quality & Tests -->

[![Tests](https://github.com/damouu/library-app-membercard/actions/workflows/run-tests.yml/badge.svg?branch=test)](https://github.com/damouu/library-app-membercard/actions/workflows/run-tests.yml) [![Merge PR](https://github.com/damouu/library-app-membercard/actions/workflows/merge-pr.yml/badge.svg)](https://github.com/damouu/library-app-membercard/actions/workflows/merge-pr.yml) [![Prepare](https://github.com/damouu/library-app-membercard/actions/workflows/prepare.yml/badge.svg)](https://github.com/damouu/library-app-membercard/actions/workflows/prepare.yml) [![Codecov](https://codecov.io/gh/damouu/library-app-membercard/branch/test/graph/badge.svg)](https://codecov.io/gh/damouu/library-app-membercard) [![Git Tag](https://img.shields.io/github/v/tag/damouu/library-app-membercard?logo=github)](https://github.com/damouu/library-app-membercard/tags)

<!-- Observability -->

![Kafka](https://img.shields.io/badge/Kafka-integrated-orange) ![Prometheus](https://img.shields.io/badge/Prometheus-monitored-blue)

---

## Responsibilities

- Consume registration events
- Generate member cards
- Manage card validity
- Persist membership records
- Maintain lifecycle consistency
- Handle asynchronous membership processing

---

## Technology Stack

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

## Event Processing

The service consumes user registration events published through Kafka.

### Processing Operations

- User registration event consumption
- Member card generation
- Membership persistence
- Card expiration initialization

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

## Processing Pipeline

User Registration Event

↓

Kafka Consumer

↓

MemberCard Generation Layer

↓

Database Persistence

---

## Coverage Policy

JaCoCo quality gates:

- **Line coverage ≥ 80%**
- **Missed complexity count ≤ 3**

### Coverage Exclusions

- DTO classes
- Model classes
- Bootstrap application class

---

## Configuration

The service uses environment-driven configuration.

```env
SPRING_DATASOURCE_URL=
SPRING_DATASOURCE_USERNAME=
SPRING_DATASOURCE_PASSWORD=
SPRING_KAFKA_BOOTSTRAP_SERVERS=
```

---

## Monitoring

```text
/actuator/health
/actuator/prometheus
/actuator/metrics
```

---

## Build Quality

The build pipeline enforces:

- Automated test execution
- Coverage thresholds
- Pull request validation
- CI verification
- Workflow consistency checks

---

## Future Improvements

- Membership renewal automation
- Tier-based membership support
- Card reissue workflows
- Event publication after card creation
- Distributed tracing

---

## License

MIT
