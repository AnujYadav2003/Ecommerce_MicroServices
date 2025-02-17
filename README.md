# E-commerce Microservices Application

## Overview

The E-commerce Microservices Application is a web application designed to facilitate online shopping. It allows users to manage products, carts, orders, and inventory through a microservices architecture. The system is built using Spring Boot for the backend and MySQL as the database.

## Features

### API Gateway
- [ ] **Route Requests**: The API Gateway routes incoming requests to the appropriate microservices.
- [ ] **Authentication**: Handles JWT authentication for secure access to services.
- [ ] **Service Discovery**: Automatically discovers and connects to available services.

### Product Service
- [ ] **Create a Product**: Admin can add new products to the catalog.
- [ ] **Get All Products**: Users can view all available products.
- [ ] **Get Product by ID**: Users can retrieve details of a specific product using its ID.
- [ ] **Update Product**: Admin can update the details of existing products.
- [ ] **Delete Product**: Admin can remove products from the catalog.
- [ ] **Group Products by Category**: Users can view products grouped by their category.
- [ ] **Get Products by Category**: Users can filter products based on category.
- [ ] **Search Products by Name**: Users can search for products by name.
- [ ] **Search Products by Category**: Users can search for products by category.
  
![Product Service](https://github.com/user-attachments/assets/a8226502-0437-44b5-ba66-3cdbed930019)

### Cart Service
- [ ] **Add Product to Cart**: Users can add products to their shopping cart.
- [ ] **Get All Products in Cart**: Users can view all products currently in their cart.
- [ ] **Get Cart by ID**: Users can retrieve their cart using its ID.
- [ ] **Delete Product from Cart**: Users can remove specific products from their cart.
- [ ] **Clear All Products from Cart**: Users can empty their cart.

![Cart Service](https://github.com/user-attachments/assets/7394a30f-cb6f-4496-b9e7-371f39358ea6)

### Inventory Service
- [ ] **Add Stock**: Admin can add stock for products.
- [ ] **Reduce Stock**: Admin can reduce stock when products are sold.
- [ ] **Get All Inventory Products**: Admin can view all products in inventory.
- [ ] **Get Stock by Product ID**: Users can check the stock level of a specific product.

![Inventory Service](https://github.com/user-attachments/assets/483bb688-098d-418d-91d5-1e3a56c993e3)

### Order Service
- [ ] **Place an Order**: Users can place orders for products.
- [ ] **Get All Orders**: Admin can view all orders placed by users.
- [ ] **Get Order Details by ID**: Users can retrieve details of a specific order.
- [ ] **Cancel Order**: Users can cancel their orders.

![Order Service](https://github.com/user-attachments/assets/bb93127a-b424-47d9-a0bc-35ed297f11ff)

## Technologies Used
- [ ] **Java**: Core backend development.
- [ ] **Spring Boot**: Framework for building RESTful APIs.
- [ ] **MySQL**: Database for storing user and application data.
- [ ] **JPA (Java Persistence API)**: ORM for interacting with the database.
- [ ] **Maven**: Dependency and project management.
- [ ] **Spring Cloud OpenFeign**: Service-to-Service Communication.
- [ ] **Swagger UI**: API Documentation.
- [ ] **JWT Authentication**: Secure Endpoints.

## Setup and Installation

### Prerequisites
- Java 11 or higher
- Maven
- MySQL Server
