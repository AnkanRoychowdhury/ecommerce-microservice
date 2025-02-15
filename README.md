# eCommerce Microservices

## 📌 Overview
This repository contains a microservices-based eCommerce platform built with Spring Boot and Java. Each service is independent, communicates via REST and Kafka, and follows a clean architecture.

## 🏗️ **Architecture**
- **API Gateway**: Handles client requests and routes them to respective microservices.
- **Service Discovery**: Uses Netflix Eureka for service registration and discovery.
- **Centralized Configuration**: Managed using Spring Cloud Config Server.
- **Inter-service Communication**: REST and Kafka for event-driven communication.
- **Authentication & Security**: Secured using Keycloak and JWT.

## 📂 **Project Structure**
```
├── gateway
├── config-server
├── discovery-server
├── auth-service
├── product-service
├── order-service
├── inventory-service
├── common-library
└── README.md
```

## 🛠️ **Installation & Setup**
### Prerequisites
- Java 17+
- Docker & Docker Compose
- PostgreSQL
- Kafka
- Keycloak (for authentication)

### 🚀 **Steps to Run**
1. Clone the repository:
   ```sh
   git clone https://github.com/your-repo/ecommerce-microservices.git
   cd ecommerce-microservices
   ```
2. Start the infrastructure services:
   ```sh
   docker-compose up -d
   ```
3. Build and start all services:
   ```sh
   mvn clean install
   mvn spring-boot:run -pl api-gateway
   ```
4. Access services:
   - API Gateway: `http://localhost:8080`
   - Eureka Server: `http://localhost:8761`
   - Keycloak: `http://localhost:8180`

## 📜 **Microservices Overview**
### **1️⃣ API Gateway** (`/api-gateway`)
- Routes requests to microservices.
- Secured with JWT authentication.

### **2️⃣ Authentication Service** (`/auth-service`)
- Manages user authentication & authorization using Keycloak.
- Provides JWT tokens.

### **3️⃣ Product Service** (`/product-service`)
- Handles CRUD operations for products.
- Database: PostgreSQL
- REST Endpoints:
  - `GET /products` - Get all products
  - `POST /products` - Add a product

### **4️⃣ Order Service** (`/order-service`)
- Manages customer orders.
- Communicates with Inventory Service.
- REST Endpoints:
  - `POST /orders` - Create an order
  - `GET /orders/{id}` - Get order details

### **5️⃣ Inventory Service** (`/inventory-service`)
- Tracks product stock levels.
- Uses Kafka to update inventory on order placement.

### **6️⃣ Config Server** (`/config-server`)
- Centralized configuration management.
- Loads config from Git repository.

### **7️⃣ Discovery Server** (`/discovery-server`)
- Eureka-based service discovery.

## 🔗 **API Documentation**
Swagger UI available at:
```
http://localhost:8080/swagger-ui.html
```

## 📊 **Event-Driven Communication**
- Kafka Topics:
  - `order-placed` → Inventory Service
  - `inventory-updated` → Order Service

## 🔍 **Monitoring & Logging**
- Tracing are managed using Zipkin.
- Metrics exposed via Actuator (`/actuator/metrics`).

## 🤝 **Contributing**
1. Fork the repository
2. Create a feature branch (`git checkout -b feature-branch`)
3. Commit your changes (`git commit -m 'Add feature'`)
4. Push to your branch (`git push origin feature-branch`)
5. Open a Pull Request

## 📜 **License**
This project is licensed under the MIT License.

---
🚀 Happy Coding!
