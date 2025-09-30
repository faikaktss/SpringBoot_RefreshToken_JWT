<h1 align="center">🔐 SpringBoot-JWT-Auth-RestAPI 🔐</h1>

<p align="center">
  <img src="https://img.shields.io/badge/Spring%20Boot-3.1.0-green?style=for-the-badge&logo=springboot" />
  <img src="https://img.shields.io/badge/JWT-Authentication-blueviolet?style=for-the-badge&logo=jsonwebtokens" />
  <img src="https://img.shields.io/badge/REST%20API-JSON-yellow?style=for-the-badge&logo=rest" />
  <img src="https://img.shields.io/badge/Security-BCrypt-orange?style=for-the-badge&logo=security" />
  <img src="https://img.shields.io/badge/JPA-Hibernate-brightgreen?style=for-the-badge&logo=hibernate" />
</p>

---

## 🚀 Proje Özeti

Kurumsal uygulamalar için modern kimlik doğrulama altyapısı!  
Spring Boot, JWT, Refresh Token ve REST mimarisi ile  
hem güvenli hem de sürdürülebilir bir "Employee Management API" örneği.

---

## 🧩 Temel Özellikler

- **JWT ile Token Bazlı Authentication**
- **Refresh Token ile Oturum Yenileme**
- **BCrypt ile Şifreleme**
- **Spring Security ile Güvenlik Duvarı**
- **Detaylı Exception ve Hata Yönetimi**
- **Katmanlı Mimari: Controller/Service/Repository/Model/DTO**
- **Veritabanı: JPA & Hibernate ile otomatik tablo yönetimi**

---

## 📁 Proje Yapısı

```text
src/main/java/com/faik/
├── Config/                # Security & AppConfig
├── Controller/
│   └── Impl/              # REST API Controllerlar
│   └── IRestAuthController.java
│   └── IRestEmployeeController.java
├── Dto/                   # Veri transfer objeleri
├── Jwt/                   # JWT işlemleri ve filtreler
├── Model/                 # Entity'ler (User, Employee, Department, RefreshToken)
├── Repository/            # Spring Data JPA Repository'ler
├── Service/
│   └── impl/              # Business logic
│   └── Interface'ler
├── Starter/               # Uygulama giriş noktası (main class)
```

---

## 🛠️ Kullanılan Teknolojiler

- **Java 17+**
- **Spring Boot**
- **Spring Security**
- **JWT (JSON Web Token)**
- **JPA (Hibernate)**
- **BCrypt**
- **Lombok**
- **Maven**

---

## 📦 Kurulum & Çalıştırma

1. **Projeyi Klonlayın**
   ```bash
   git clone https://github.com/faikaktss/SpringBoot-JWT-Auth-RestAPI.git
   cd SpringBoot-JWT-Auth-RestAPI
   ```

2. **Veritabanı Ayarlarını Yapın**
   - `src/main/resources/application.properties` dosyasında DB bilgilerinizi girin:
     ```
     spring.datasource.url=jdbc:mysql://localhost:3306/yourdb
     spring.datasource.username=youruser
     spring.datasource.password=yourpassword
     spring.jpa.hibernate.ddl-auto=update
     ```

3. **Projeyi Başlatın**
   ```bash
   ./mvnw spring-boot:run
   ```
   veya
   ```bash
   mvn spring-boot:run
   ```

---

## 🧑‍💻 API Kullanımı

### 1. Kullanıcı Kaydı (Register)
```
POST /register
Content-Type: application/json

{
  "username": "faik",
  "password": "123456"
}
```
Yanıt:
```json
{
  "username": "faik",
  "password": "$2a$10$..."
}
```

---

### 2. Giriş (Authenticate)
```
POST /authenticate
Content-Type: application/json

{
  "username": "faik",
  "password": "123456"
}
```
Yanıt:
```json
{
  "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI...",
  "refreshToken": "d9c1edb7-6dbe-44ff-ba4b-..."
}
```

---

### 3. Token Yenileme (Refresh Token)
```
POST /refreshToken
Content-Type: application/json

{
  "refreshToken": "d9c1edb7-6dbe-44ff-ba4b-..."
}
```
Yanıt:
```json
{
  "accessToken": "yeni-access-token...",
  "refreshToken": "yeni-refresh-token..."
}
```

---

### 4. Çalışan Bilgisi Sorgulama
```
GET /employee/{id}
Authorization: Bearer <accessToken>
```
Yanıt:
```json
{
  "id": 1,
  "first_name": "Ali",
  "last_name": "Veli",
  "department": {
    "id": 2,
    "name": "IT",
    "location": "Istanbul"
  }
}
```

---

## 🔐 Güvenlik Detayları

- Tüm endpointler JWT ile korunur.  
- Sadece `/register`, `/authenticate` ve `/refreshToken` açık, diğerleri için geçerli `accessToken` gerekir.
- Kullanıcı şifreleri **BCrypt** ile hashlenerek saklanır.
- Refresh tokenlar veritabanında tutulur ve süresi dolunca yenilenir.

---

## 🏅 Neden Bu Proje?

- **Kurumsal altyapılara uygun, scalable yapı**
- Spring Security & JWT entegrasyonu
- Token ve RefreshToken akışını tam anlamıyla gösteren örnek
- Temiz, anlaşılır ve modüler kod mimarisi

---

## 🤝 Katkı Sağlamak

1. Fork'la ve kendi branch'ini oluştur!
2. Yeni özellik eklediysen, test eklemeyi unutma!
3. Pull request aç, birlikte geliştirelim 🚀

---

## 📚 Referanslar

- [Spring Boot Docs](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Spring Security JWT](https://www.baeldung.com/spring-security-oauth-jwt)
- [Best Practices for REST APIs](https://restfulapi.net/)

---

## 👨‍💻 Yazar

**Faik Aktaş**  
[GitHub](https://github.com/faikaktss)

---

<p align="center">
  <b>⭐️ Projeyi faydalı bulduysan yıldızlamayı unutma! ⭐️</b>
</p>
