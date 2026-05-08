![Status](https://img.shields.io/badge/Status-In--Development-yellow?style=for-the-badge&logo=github)
# Crypto Portfolio Tracker REST API

RESTful API for tracking cryptocurrency portfolios with real-time price integration, built with **Java** and **Spring Boot**.

## Stack
* **Java 25**
* **Spring Boot 4** (Web, Data JPA, Security, WebFlux)
* **Spring Security 6** (Custom UserDetailsService, Basic Auth)
* **Hibernate / Spring Data JPA** / **MySQL** / **WebClient** (External API integration with `freecryptoapi.com`)
* **Maven**
* **Postman** (Collection for testing included)

## Security & Access
The API uses Basic Authentication with BCrypt password hashing.
* **Public Access:** New user registration (`POST /api/users`) and basic user info.
* **Private Access:** All portfolio and asset management requires a valid user account.
* **Roles:** Currently implementing a flexible role system (USER/ADMIN) to restrict data access so users can only view their own portfolios.

## API Endpoints

### Users Management
| HTTP Method | Endpoint | Action | Status |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/users` | Register a new user | ✅ Functional |
| `GET` | `/api/users/{userId}` | Get user profile info (DTO) | ✅ Functional |
| `GET` | `/api/users` | Get list of all users | ✅ Functional |

### Portfolio (Calculated Data)
| HTTP Method | Endpoint | Action | Status |
| :--- | :--- | :--- | :--- |
| `GET` | `/api/users/{userId}/portfolio` | Get portfolio with live profit/loss | ✅ Functional |
| `GET` | `/api/users/{userId}/summary` | Get aggregate portfolio summary | ✅ Functional |
| `GET` | `/api/users/{userId}/assets` | Get raw assets from DB | ✅ Functional |
| `POST` | `/api/users/{userId}/assets` | Add new coin to portfolio |  ✅ Functional |

### Assets Management (Database CRUD)
| HTTP Method | Endpoint                | Action                 | Status |
| :--- |:------------------------|:-----------------------| :--- |
| `PUT` | `/api/assets`           | Update asset record    | ✅ Functional |
| `PATCH` | `/api/assets/{assetId}` | Partially update asset |  ✅ Functional |
| `DELETE` | `/api/assets/{assetId}` | Delete asset from DB   | ✅ Functional |

## Setup & Run
1. Clone the repository.
2. Ensure you have a running MySQL instance and create a database named `crypto_tracker_db`.
3. Update `src/main/resources/application.properties` with your credentials:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/crypto_tracker_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   spring.jpa.hibernate.ddl-auto=update
   ```
4. Run the application using Maven:
   ```bash
   mvn spring-boot:run
   ```
5. On the first run, `DataSeeder` will automatically create a test user:
    * **Username:** `user`
    * **Password:** `user`

---
