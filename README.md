# Recipe App Backend

A Spring Boot application that provides a RESTful API for managing recipes. The application fetches recipe data from an external API and stores it in a database for quick access.

## Features

- Fetch recipes from external API (dummyjson.com)
- Store recipes in a database
- RESTful API endpoints for recipe management
- Automatic data loading on application startup
- Comprehensive test coverage
- Full-text search capabilities using Hibernate Search
- Detailed logging for improved observability and debugging

## Tech Stack

- Java 17
- Spring Boot 2.7.9
- Spring Data JPA
- H2 Database (for development)
- Hibernate Search 6.2.4.Final
- Maven
- JUnit 5
- Mockito
- SLF4J for logging

## Project Structure

```
recipe-app-backend/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/
│   │   │       └── example/
│   │   │           ├── RecipeApplication.java
│   │   │           ├── controller/
│   │   │           ├── domain/
│   │   │           ├── repository/
│   │   │           └── service/
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│           └── org/
│               └── example/
│                   ├── controller/
│                   │   └── RecipeControllerTest.java
│                   ├── service/
│                   │   ├── LoadRecipeServiceTest.java
│                   │   ├── RecipeServiceTest.java
│                   │   └── SearchIndexInitializerTest.java
│                   └── RecipeApplicationTests.java
└── pom.xml
```

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

### Installation

1. Clone the repository:
   ```
   git clone https://github.com/yourusername/recipe-app-backend.git
   cd recipe-app-backend
   ```

2. Build the project:
   ```
   mvn clean install
   ```

3. Run the application:
   ```
   mvn spring-boot:run
   ```

The application will start on `http://localhost:8080` by default.

## API Endpoints

### Recipes

- `GET /api/recipes/{id}` - Get a recipe by ID
- `GET /api/recipes/findAll` - Get all recipes with pagination
- `GET /api/recipes/search?query={query}` - Search recipes by name or cuisine

## Data Model

### Recipe

Each recipe has the following properties:

- `id` (required): Unique identifier for the recipe
- `name`: Name of the recipe
- `ingredients`: List of ingredients
- `instructions`: Step-by-step cooking instructions
- `cuisine`: Type of cuisine (e.g., Italian, Mexican)
- `prepTimeMinutes`: Preparation time in minutes
- `cookTimeMinutes`: Cooking time in minutes
- `servings`: Number of servings
- `difficulty`: Difficulty level (e.g., Easy, Medium, Hard)
- `caloriesPerServing`: Calories per serving
- `tags`: List of tags for categorizing recipes
- `image`: URL to the recipe image
- `rating`: Average rating
- `reviewCount`: Number of reviews
- `mealType`: Type of meal (e.g., breakfast, lunch, dinner)

## Configuration

The application can be configured through the `application.properties` file:

```properties
# Server configuration
server.port=8080

# Database configuration
spring.datasource.url=jdbc:h2:mem:recipedb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true

# JPA configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Logging configuration
logging.level.org.example=INFO
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n
```

## Testing

Run the tests with:

```
mvn test
```

The project includes comprehensive test coverage for:
- Controllers (RecipeControllerTest)
- Services (LoadRecipeServiceTest, RecipeServiceTest, SearchIndexInitializerTest)
- Application startup (RecipeApplicationTests)

## Logging

The application uses SLF4J for logging with the following features:
- INFO level logs for method entry points
- DEBUG level logs for successful operations
- ERROR level logs for exceptions with stack traces
- Parameterized logging to avoid string concatenation
- Contextual information in log messages