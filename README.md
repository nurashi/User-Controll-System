# User-Controll-System

![image](https://github.com/user-attachments/assets/0971f48a-da43-4d3d-be0f-cdbac7ebdd88)

This project created for "Object-Oriented Programming. | Khaimuldin Askar" course. Focused on controlling Users from Database which is Postgres...


##  Required Tools Installation

1. **Download and install Spring Boot**:
   - Visit the official Spring Boot website: [https://spring.io/projects/spring-boot](https://spring.io/projects/spring-boot)
   - Ensure that Java JDK version 17 or higher is installed.
   - Install Maven (if not already installed). You can follow the [official instructions](https://maven.apache.org/install.html).

2. **Download and install PostgreSQL**:
   - Visit the PostgreSQL website: [https://www.postgresql.org/download/](https://www.postgresql.org/download/).
   - Install the PostgreSQL server and pgAdmin (for easier management).


##  Project Setup

1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-repo-name.git
   cd your-repo-name
   ```

2. **Configure the `application.properties` file**:
   Locate the `application.properties` file in the `src/main/resources` folder and set your PostgreSQL configuration:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/your_database_name
   spring.datasource.username=your_username
   spring.datasource.password=your_password




