# Record Manager REST API Documentation

This documentation provides an overview of the REST API for a dental record management system. The API is designed to manage information related to dentists, patients, and prosthetists.

## Table of Contents

1. [Dentist API](#dentist-api)
2. [Patient API](#patient-api)
3. [Prosthetist API](#prosthetist-api)
4. [Security Configuration](#security-configuration)

---

## Dentist API

### 1. Get All Dentists

**Endpoint:** `/api/dentists`

**Method:** `GET`

**Description:** Retrieve a list of all dentists.

### 2. Get Dentist by ID

**Endpoint:** `/api/dentists/{dentistId}`

**Method:** `GET`

**Description:** Retrieve a specific dentist by their ID.

### 3. Add New Dentist

**Endpoint:** `/api/dentists`

**Method:** `POST`

**Description:** Add a new dentist to the system.

**Request Body:**
```json
{
  "id": 0,
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phoneNumber": "123-456-7890"
}
```

### 4. Update Dentist

**Endpoint:** `/api/dentists`

**Method:** `PUT`

**Description:** Update an existing dentist.

**Request Body:**
```json
{
  "id": 1,
  "firstName": "UpdatedFirstName",
  "lastName": "UpdatedLastName",
  "email": "updated.email@example.com",
  "phoneNumber": "987-654-3210"
}
```

### 5. Delete Dentist

**Endpoint:** `/api/dentists/{dentistId}`

**Method:** `DELETE`

**Description:** Delete a dentist by their ID.

---

## Patient API

### 1. Get All Patients

**Endpoint:** `/api/patients`

**Method:** `GET`

**Description:** Retrieve a list of all patients.

### 2. Get Patient by ID

**Endpoint:** `/api/patients/{patientId}`

**Method:** `GET`

**Description:** Retrieve a specific patient by their ID.

### 3. Add New Patient

**Endpoint:** `/api/patients`

**Method:** `POST`

**Description:** Add a new patient to the system.

**Request Body:**
```json
{
  "id": 0,
  "firstName": "Jane",
  "lastName": "Doe",
  "email": "jane.doe@example.com",
  "phoneNumber": "555-123-4567"
}
```

### 4. Update Patient

**Endpoint:** `/api/patients`

**Method:** `PUT`

**Description:** Update an existing patient.

**Request Body:**
```json
{
  "id": 2,
  "firstName": "UpdatedJane",
  "lastName": "UpdatedDoe",
  "email": "updated.jane@example.com",
  "phoneNumber": "555-987-6543"
}
```

### 5. Delete Patient

**Endpoint:** `/api/patients/{patientId}`

**Method:** `DELETE`

**Description:** Delete a patient by their ID.

---

## Prosthetist API

### 1. Get All Prosthetists

**Endpoint:** `/api/prosthetists`

**Method:** `GET`

**Description:** Retrieve a list of all prosthetists.

### 2. Get Prosthetist by ID

**Endpoint:** `/api/prosthetists/{prosthetistId}`

**Method:** `GET`

**Description:** Retrieve a specific prosthetist by their ID.

### 3. Add New Prosthetist

**Endpoint:** `/api/prosthetists`

**Method:** `POST`

**Description:** Add a new prosthetist to the system.

**Request Body:**
```json
{
  "id": 0,
  "firstName": "Prosthetist",
  "lastName": "Professional",
  "email": "prosthetist@example.com",
  "phoneNumber": "111-222-3333"
}
```

### 4. Update Prosthetist

**Endpoint:** `/api/prosthetists`

**Method:** `PUT`

**Description:** Update an existing prosthetist.

**Request Body:**
```json
{
  "id": 3,
  "firstName": "UpdatedProsthetist",
  "lastName": "UpdatedProfessional",
  "email": "updated.prosthetist@example.com",
  "phoneNumber": "111-444-5555"
}
```

### 5. Delete Prosthetist

**Endpoint:** `/api/prosthetists/{prosthetistId}`

**Method:** `DELETE`

**Description:** Delete a prosthetist by their ID.

---

## Security Configuration

The API endpoints are secured with role-based access control. The roles and corresponding access rights are as follows:

- **PROSTHETIST:** Read access to `/api/prosthetists` and `/api/patients`, as well as their specific endpoints.
- **MANAGER:** Read and write access to `/api/prosthetists`, `/api/patients`, and `/api/dentists`, as well as their specific endpoints.
- **OWNER:** Full access to all API endpoints.

Authentication is implemented using HTTP Basic Authentication, and password encoding is performed using the bcrypt algorithm.

### Note
- All date/time values are represented in ISO 8601 format.
- Error responses include a descriptive message for troubleshooting.

--
