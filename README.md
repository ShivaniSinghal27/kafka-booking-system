# Kafka Booking System

A **Spring Boot** project for a **hotel booking system** using **Apache Kafka**, ensuring **ordered processing**, **idempotent DB writes**, **retry topics**, and **dead-letter handling**.

---

## Features

- Booking events: `NEW_BOOKING`, `CHANGE_BOOKING`, `CANCEL_BOOKING`  
- Kafka partitioned by `bookingId` → preserves message order  
- `@RetryableTopic` with retries and **Dead Letter Topic (DLT)**  
- Idempotent database writes (H2/Postgres demo)  
- REST API with **Swagger UI**  
- Optional OpenAI integration for booking summaries  

---

## Prerequisites

- Java 17+  
- Apache Kafka (`localhost:9092`)  
- Gradle 7+  
- Optional: OpenAI API key  

---

## Setup Instructions

1. Clone the repository:

```bash
git clone https://github.com/ShivaniSinghal27/kafka-booking-system.git
cd kafka-booking-system
```

2. Start Kafka & Zookeeper:
 ```bash
bin/zookeeper-server-start.sh config/zookeeper.properties
bin/kafka-server-start.sh config/server.properties
```
3. Run Spring Boot:
   ```bash
   ./gradlew bootRun
   ```
4. Swagger UI: http://localhost:8089/swagger-ui.html

5. H2 Console: http://localhost:8089/h2-console

  JDBC URL: jdbc:h2:mem:bookingdb

  User: sa

  Password: (leave blank)

