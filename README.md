# Gas Utility Service API Documentation

## Base URL
```
http://localhost:8080/api/
```

## Endpoints

### 1. Welcome Message
**Endpoint:** `GET /`
- **Description:** Returns a welcome message.
- **Response:**
  - ✅ `200 OK`: "Welcome to Gas Utility Service"

---

### 2. User Registration
**Endpoint:** `POST /user/register`
- **Description:** Registers a new user.
- **Request Body (JSON):**
  ```json
  {
    "username": "string",
    "password": "string"
  }
  ```
- **Responses:**
  - 🎉 `201 CREATED`: User registered successfully
  - ⚠️ `409 CONFLICT`: User already exists or data integrity violation
  - ❗ `400 BAD REQUEST`: Invalid input provided
  - ⚠️ `500 INTERNAL SERVER ERROR`: Unexpected error

---

### 3. User Login
**Endpoint:** `POST /user/login`
- **Description:** Verifies user credentials.
- **Parameters (Form Data):**
  - 🔑 `username` (String) - Required
  - 🔒 `password` (String) - Required
- **Responses:**
  - ✅ `200 OK`: User verified successfully
  - ❌ `401 UNAUTHORIZED`: Invalid credentials
  - ⚠️ `500 INTERNAL SERVER ERROR`: Unexpected error

---

### 4. Create a Service Request
**Endpoint:** `POST /user/create`
- **Description:** Allows a user to create a new service request.
- **Headers:**
  - 🔑 `Authorization: Bearer <token>`
- **Request Body (JSON):**
  ```json
  {
    "requestType": "Gas Leak Repair",
    "description": "Urgent gas leak in kitchen"
  }
  ```
- **Response:**
  - ✅ `200 OK`: Request created successfully.
  - ❌ `400 BAD REQUEST`: Invalid request data.
  - ⚠️ `500 INTERNAL SERVER ERROR`: Something went wrong.

---

### 5. Get User’s Service Requests
**Endpoint:** `GET /user/my-requests`
- **Description:** Retrieves all service requests made by the authenticated user.
- **Headers:**
  - 🔑 `Authorization: Bearer <token>`
- **Response:**
  - ✅ `200 OK`: Returns a list of service requests.
  - ⚠️ `500 INTERNAL SERVER ERROR`: Something went wrong.

---

### 6. Get a Specific Service Request (User-Specific)
**Endpoint:** `GET /user/my-requests/{id}`
- **Description:** Retrieves details of a specific request created by the authenticated user.
- **Headers:**
  - 🔑 `Authorization: Bearer <token>`
- **Path Parameter:**
  - 🔢 `id` (integer) – The ID of the service request.
- **Response:**
  - ✅ `200 OK`: Returns service request details.
  - ❌ `404 NOT FOUND`: Request not found.

---

### 7. Admin Registration
**Endpoint:** `POST /admin/register`
- **Description:** Registers a new admin.
- **Request Body (JSON):**
  ```json
  {
    "username": "string",
    "password": "string"
  }
  ```
- **Responses:**
  - 🎉 `201 CREATED`: Admin registered successfully
  - ⚠️ `409 CONFLICT`: Admin already exists or data integrity violation
  - ❗ `400 BAD REQUEST`: Invalid input provided
  - ⚠️ `500 INTERNAL SERVER ERROR`: Unexpected error

---

### 8. Admin Login
**Endpoint:** `POST /admin/login`
- **Description:** Verifies admin credentials.
- **Parameters (Form Data):**
  - 🔑 `username` (String) - Required
  - 🔒 `password` (String) - Required
- **Responses:**
  - ✅ `200 OK`: Admin verified successfully
  - ❌ `401 UNAUTHORIZED`: Invalid credentials
  - ⚠️ `500 INTERNAL SERVER ERROR`: Unexpected error

---

### 9. Get All Service Requests (Admin Only)
**Endpoint:** `GET /admin/requests`
- **Description:** Retrieves all service requests (Admin only).
- **Headers:**
  - 🔑 `Authorization: Bearer <admin-token>`
- **Response:**
  - ✅ `200 OK`: Returns a list of all service requests.

---

### 10. Get a Specific Service Request (Admin Only)
**Endpoint:** `GET /admin/requests/{id}`
- **Description:** Retrieves a specific service request by ID (Admin only).
- **Headers:**
  - 🔑 `Authorization: Bearer <admin-token>`
- **Path Parameter:**
  - 🔢 `id` (integer) – The ID of the service request.
- **Response:**
  - ✅ `200 OK`: Returns service request details.
  - ❌ `404 NOT FOUND`: Request not found.

---

### 11. Update Request Status (Admin Only)
**Endpoint:** `PUT /admin/requests/update/{id}`
- **Description:** Allows an admin to update the status of a service request.
- **Headers:**
  - 🔑 `Authorization: Bearer <admin-token>`
- **Path Parameter:**
  - 🔢 `id` (integer) – The ID of the service request.
- **Query Parameter:**
  - 🏷️ `status` (string) – The new status of the request (e.g., Pending, Completed, In_Progress).
- **Response:**
  - ✅ `200 OK`: Status updated successfully.
  - ❌ `400 BAD REQUEST`: Invalid request data.
  - ❌ `404 NOT FOUND`: Request not found.
  - ⚠️ `500 INTERNAL SERVER ERROR`: Something went wrong.

---

## Notes
- Ensure that the `Authorization` headers contain valid tokens for authenticated requests.
- Use the appropriate HTTP methods (`GET`, `POST`, `PUT`) based on the action being performed.
- Handle responses properly based on the status codes provided.
- Maintain security best practices while storing and managing passwords and authentication tokens.
