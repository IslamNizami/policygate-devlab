# PolicyGate - Role and Permission Management API

PolicyGate is a production-ready backend system for managing roles, permissions, and protected operations. Instead of hardcoding simple `USER` and `ADMIN` checks, the system stores highly granular permissions in the database and evaluates access dynamically based on assigned roles using Spring Security's method-level authorization.

## 🎯 Learning Objectives

- **Role-Based Access Control (RBAC):** Understand and implement permission-based authorization architectures.
- **Granular Security:** Learn the technical differences between broad roles and fine-grained permissions.
- **Database Architecture:** Design and implement Many-to-Many entity relationships for security mapping.
- **Method-Level Security:** Practice securing endpoints dynamically using Spring Security's `@PreAuthorize`.

## ✨ Features

### Core Capabilities

- **Role Management:** Admins can create, update, and list roles dynamically.
- **Permission Management:** Define granular permissions (e.g., `TASK_WRITE`, `REPORT_READ`) directly in the database.
- **Dynamic Assignment:** Attach permissions to specific roles and assign roles to users via relational mapping.

### Production-Grade Enhancements

- **Redis Caching:** Reduces database load by caching user permissions and role lists in memory. Utilizes `@Cacheable` and `@CacheEvict` for automated cache invalidation.
- **Audit Logging:** Automatically tracks permission and role changes, securely logging the performer, action, target entity, and timestamp to a dedicated PostgreSQL table.
- **Data Seeding:** Automatically seeds default admin/user accounts with securely hashed passwords and initial role hierarchies using Flyway migrations.

## 🛠️ Tech Stack

| Component | Technology |
| :--- | :--- |
| **Language** | Java 21 |
| **Framework** | Spring Boot 3.x |
| **Security** | Spring Security (Basic Auth, Method-Level) |
| **Database** | PostgreSQL |
| **Caching** | Redis (Lettuce Client) |
| **Migrations** | Flyway |
| **Documentation** | OpenAPI 3.0 / Swagger UI (`springdoc`) |
| **Deployment** | Docker & Docker Compose |

## 🚀 Getting Started

### Prerequisites

- Java 21 JDK
- Docker & Docker Compose
- Gradle

### Installation & Setup

1. **Clone the repository:**

```bash
git clone https://github.com/islamnizami/policygate-devlab.git
cd policygate-devlab

docker-compose up -d

./gradlew bootRun

Flyway will automatically execute migrations, build the schema, and seed the initial administrative data.

📚 API Documentation

Once the application is running, the interactive OpenAPI/Swagger UI documentation is available at:

http://localhost:5050/swagger-ui/index.html

🔑 Key Endpoints
Method	Endpoint	Description	Required Permission
GET	/api/roles	List all roles	ROLE_READ
POST	/api/roles	Create a new role	ROLE_WRITE
POST	/api/permissions	Create a new permission	PERMISSION_WRITE
POST	/api/roles/{role}/permissions/{perm}	Assign permission to a role	ROLE_ASSIGN
POST	/api/users/{user}/roles/{role}	Assign role to a user	USER_ASSIGN
👨‍💻 Author

Islam Nizami

Developed as part of the Devlab Backend Development Internship program.
