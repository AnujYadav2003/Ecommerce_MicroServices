# E-commerce Microservices Application

This project is a microservices-based e-commerce application built using Java, Spring Boot, MySQL, Swagger UI for documentation, JPA, and Maven. The application consists of five main services: API Gateway, Product Service, Cart Service, Inventory Service, and Order Service.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Setup and Installation](#setup-and-installation)
- [API Documentation](#api-documentation)
- [Usage](#usage)
- [License](#license)

## Features

### Product Service
- **Create a product**: `POST /create`
- **Get all products**: `GET /getAllProducts`
- **Get product by ID**: `GET /getById/{productId}`
- **Update product**: `PUT /updateById/{productId}`
- **Delete product**: `DELETE /deleteById/{productId}`
- **Group products by category**: `GET /groupByCategory`
- **Get products by category**: `GET /getProductsByCategory/{category}`
- **Search products by name**: `GET /searchByName/{name}`
- **Search products by category**: `GET /searchByCategory/{category}`

### Cart Service
- **Add product to cart**: `POST /addCart/{productId}`
- **Get all products in cart**: `GET /getProductsFromCart`
- **Get cart by ID**: `GET /getCartById/{cartId}`
- **Delete product from cart**: `DELETE /deleteProductsFromCart/{cartId}`
- **Clear all products from cart**: `DELETE /deleteAllProductsFromCart`

### Inventory Service
- **Add stock**: `POST /add`
- **Reduce stock**: `PUT /reduce`
- **Get all inventory products**: `GET /all`
- **Get stock by product ID**: `GET /stock/{productId}`

### Order Service
- **Place an order**: `POST /placeOrder`
- **Get all orders**: `GET /allOrders`
- **Get order details by ID**: `GET /getOrderById/{orderId}`
- **Cancel order**: `DELETE /cancel/{orderId}`

## Technologies Used
- **Spring Boot**: REST APIs, Microservices Architecture
- **Spring Cloud OpenFeign**: Service-to-Service Communication
- **MySQL**: Database Management
- **Swagger UI**: API Documentation
- **JPA & Hibernate**: ORM for database interaction
- **Maven**: Dependency Management
- **JWT Authentication**: Secure Endpoints

## Setup and Installation

### Prerequisites
- Java 11 or higher
- Maven
- MySQL Server

### Steps
1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/ecommerce-microservices.git
   cd ecommerce-microservices
