# Pet Project: Spring Boot + OAuth2/OIDC Login with Google

This project was built as part of my advanced study of modern authentication and authorization mechanisms: **OAuth 2.0 + OpenID Connect + JWT**.

### Project Goal
Gain hands-on experience with integrating an external Identity Provider (Google) into a Spring Boot application.  
Understand the real-world Authorization Code Flow, work with ID Tokens (JWT), session management, and basic resource protection.

### Features Implemented
- Spring Boot 3.x MVC application with Thymeleaf
- Authentication via Google (OAuth2 + OpenID Connect)
- Custom login page with "Sign in with Google" button
- After successful login → redirect to protected `/dashboard` page
- Display user data from Google:
    - Full name
    - Email address
    - Email verification status (`verified_email`)
    - Profile picture
- Route protection: only authenticated users can access `/dashboard`
- Sessions stored in Redis (via Spring Session)
- In-memory H2 database (ready for future extensions)
- Logout with redirect to home page

### Technologies Used
- Spring Boot 3.x
- Spring Security (oauth2Login + oauth2Client)
- Spring Session + Redis
- Thymeleaf
- H2 Database (in-memory)
- Docker (for Redis)

### How to Run

#### 1. Get Google OAuth Credentials
1. Go to https://console.cloud.google.com/apis/credentials
2. Create an **OAuth 2.0 Client ID** of type **Web application**
3. Add Authorized redirect URI:  
   `http://localhost:8080/login/oauth2/code/google`
4. Copy the **Client ID** and **Client Secret**

#### 2. Configure application.yml
```yaml
spring:
  security:
    oauth2:
      client:
        registration:
          google:
            client-id: YOUR_CLIENT_ID
            client-secret: YOUR_CLIENT_SECRET
            scope: openid,profile,email