Developed a Spring Boot-based ProductService module exposing RESTful APIs to manage product lifecycle operations, including creation, retrieval, and searching of product entities after authenticating the user. Integrated with a relational database (MySQL) using Spring Data JPA for persistence. The service supported dynamic search filters, pagination, and followed RESTful best practices with proper HTTP status codes and input validation.
Key features:
  1. POST /products – Create a new product (requires authentication and input validation).
  2. GET /products/{id} – Fetch product by ID for authenticated users.
  3. GET /products?name=&category=...&page=&size= – Search, filter, and paginate product results.
     
Implemented layered architecture (Controller → Service → Repository) and used DTOs for clean separation of concerns. Included JWT-based authentication.
