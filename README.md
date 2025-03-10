# Banking Application (Banking API)

This project is a RESTful API developed for managing bank accounts. It is built using Spring Boot and provides basic account operations.

## Technologies

- Java 17+
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven

## API Documentation

### 1. Create Account

**Endpoint:** `POST /createAccount`

- Creates a new account.
- **Request Body:**
  ```json
  {
    "accountHolderName": "John Doe",
    "amount": 1000.0,
    "balance": 1000.0
  }
  ```
- **Response:** 201 CREATED

### 2. Get All Accounts

**Endpoint:** `GET /getAllAccounts`

- Retrieves all accounts in the system.
- **Response:** 200 OK

### 3. Get Account by ID

**Endpoint:** `GET /getByIdAccount/{id}`

- Retrieves an account with the specified `id`.
- **Response:** 200 OK

### 4. Update Account

**Endpoint:** `PUT /updateAccount/{id}`

- Updates an account with the specified `id`.
- **Request Body:**
  ```json
  {
    "accountHolderName": "Jane Doe",
    "amount": 2000.0,
    "balance": 2000.0
  }
  ```
- **Response:** 200 OK

### 5. Delete Account

**Endpoint:** `DELETE /deleteAccount/{id}`

- Deletes an account with the specified `id`.
- **Response:** 200 OK (`"Account is deleted Successfully"`)

### 6. Deposit Money

**Endpoint:** `PUT /{id}/deposit`

- Deposits money into the account with the specified `id`.
- **Request Body:**
  ```json
  500.0
  ```
- **Response:** 200 OK

### 7. Withdraw Money

**Endpoint:** `PUT /{id}/withdraw`

- Withdraws money from the account with the specified `id`.
- **Request Body:**
  ```json
  300.0
  ```
- **Response:** 200 OK

## Installation

1. **Clone the Repository:**
   ```sh
   git clone https://github.com/username/banking-api.git
   ```
2. **Install Dependencies:**
   ```sh
   mvn clean install
   ```
3. **Run the Application:**
   ```sh
   mvn spring-boot:run
   ```
4. **Test the API:**
   - You can test API calls using Postman or cURL.

## License

This project is licensed under the MIT License.

