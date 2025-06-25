# **Smart Parking Management System (SPMS)**  
### **A Microservice-Based Cloud-Native Parking Solution**  

---

## **📌 Table of Contents**  
1. [Project Overview](#-project-overview)  
2. [Key Features](#-key-features)  
3. [System Architecture](#-system-architecture)  
4. [Technologies Used](#-technologies-used)  
5. [Microservices Breakdown](#-microservices-breakdown)  
6. [Setup & Deployment](#-setup--deployment)  
7. [API Documentation](#-api-documentation)  
8. [Testing & Validation](#-testing--validation)  
9. [Screenshots & Demo](#-screenshots--demo)  
10. [Future Enhancements](#-future-enhancements)  
11. [Contributors](#-contributors)  
12. [License](#-license)  

---

## **🚀 Project Overview**  
The **Smart Parking Management System (SPMS)** is a **cloud-native, microservice-based** platform designed to solve urban parking challenges by providing **real-time parking space management, reservation, and payment processing**.  

### **💡 Business Objectives**  
✔ **Reduce traffic congestion** by optimizing parking space utilization  
✔ **Enhance user experience** with real-time parking availability & digital payments  
✔ **Improve city mobility** by minimizing time wasted searching for parking  
✔ **Support smart city initiatives** with IoT integration capabilities  

---

## **✨ Key Features**  
✅ **Real-time Parking Availability** – Dynamic updates on parking spot status  
✅ **Seamless Reservations** – Book & release parking spots instantly  
✅ **Digital Payments** – Secure mock payment processing with receipts  
✅ **User & Vehicle Management** – Register users & track vehicle entries/exits  
✅ **Scalable Microservices** – Independent, modular services for easy maintenance  
✅ **Centralized Configuration** – Easy updates via Spring Cloud Config  

---

## **🏗 System Architecture**  
The system follows a **microservices architecture** with:  
🔹 **Service Registry (Eureka)** – Dynamic service discovery  
🔹 **API Gateway** – Single entry point for routing requests  
🔹 **Config Server** – Centralized configuration management  
🔹 **Four Core Microservices**:  
   - **Parking Space Service**  
   - **Vehicle Service**  
   - **User Service**  
   - **Payment Service**  

---

## **🛠 Technologies Used**  

| **Category**       | **Technologies**                          |
|--------------------|------------------------------------------|
| **Backend**        | Spring Boot, Spring Cloud (Eureka, Config, Gateway) |
| **API Development**| Spring Web (REST)|
| **Database**       | My sql |
| **Testing**        | Postman (API Testing)                     |
| **DevOps**         | GitHub (Version Control)                  |

**Note:** The system primarily uses **Spring Boot**, with optional Node.js/Python for specific services.  

---

## **🔍 Microservices Breakdown**  

### **1. Parking Space Service**  
- Manages parking spot availability (occupied/free)  
- Supports filtering by location, zone, and owner  
- Simulates IoT-based status updates  

### **2. Vehicle Service**  
- Registers & tracks vehicle details  
- Simulates entry/exit events  

### **3. User Service**  
- Handles authentication & profile management  
- Stores booking history & logs  

### **4. Payment Service**  
- Processes mock transactions  
- Generates digital receipts  

---

## **⚙ Setup & Deployment**  

### **Prerequisites**  
- Java 21+  
- Maven  
- Postman (for API testing)  

### **Steps to Run**  
1. **Clone the repositories:**  
   ```bash
   git clone https://github.com/shaanzx/AD2-Final-Smart-Parking-System.git
   git clone https://github.com/shaanzx/Smart-Parking-config-server.git
   ```
2. **Start the Config Server first**  
3. **Launch Eureka Server** (Service Registry)  
4. **Run all microservices** (Parking, Vehicle, User, Payment)  
5. **Access the API Gateway** at `http://localhost:8080`  

---

## **📚 API Documentation**  
All API endpoints are tested and documented in the **Postman Collection**.  

🔗 **[Postman Collection]**  
[[vehicle.json](https://github.com/user-attachments/files/20895348/vehicle.json)
[Payment.json](https://github.com/user-attachments/files/20895350/Payment.json)
[Parking-space.json](https://github.com/user-attachments/files/20895349/Parking-space.json)
[User.json](https://github.com/user-attachments/files/20895437/User.json)

---

## **🧪 Testing & Validation**  
✔ **Postman** – Full API test suite covering all microservices  
✔ **Edge Case Testing** – Invalid inputs, failed payments, etc.  
✔ **Eureka Dashboard** – Confirms all services are registered  

![Eureka](https://github.com/user-attachments/assets/6b38c7af-81d5-4540-a369-7aa2a21a1ff1)
  

---

## **📸 Screenshots & Demo**  
📂 **See `/docs/screenshots` for:**  
- Eureka Dashboard  
- API Request/Response Samples  
- Postman Test Results  

---

## **🚀 Future Enhancements**  
🔹 **Mobile App Integration** (Android/iOS)  
🔹 **IoT Sensors** – Real-time parking spot detection  
🔹 **Analytics Dashboard** – Usage trends & reports  
🔹 **Multi-language Support**  

---

## **👥 Contributors**  
- [https://github.com/shaanzx/]  

---

## **📜 License**  
This project is licensed under **[MIT License](LICENSE.md)**.  

---

### **🔗 Repository Links**  
📌 **Main Project:** [AD2-Final-Smart-Parking-System](https://github.com/shaanzx/AD2-Final-Smart-Parking-System.git)  
📌 **Config Server:** [Smart-Parking-config-server](https://github.com/shaanzx/Smart-Parking-config-server.git)  

---

**🌟 Thank you for checking out SPMS!**  
For questions, contact: **[shaanz11.11@gmail.com]**
