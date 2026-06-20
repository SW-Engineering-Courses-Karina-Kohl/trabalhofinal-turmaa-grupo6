# Project Conventions

## General Guidelines
- **Default language:** English
- **If not defined:** Fallback to base java guidelines as defined on "Aula 15 Convenções, Documentação e Code Smells"

---

## Code Style

### Functions
```java
void function(void){
    // Process
}
```

```java
int function(int type1, type type2, ...){
    // vars

    /* implementation */

    /* loop */
    
    /* implementation */

    return 0;
}
```

---

### Loops
```java
for(int i = 0 ; i < 10 ; i++ ){
    // loop 10
}
```

---

## Naming Conventions

| Element        | Convention        | Example            |
|----------------|------------------|--------------------|
| Classes        | PascalCase       | `UserController`   |
| Methods/Vars   | camelCase        | `getUserData`      |
| Constants      | UPPER_SNAKE_CASE        | `MAX_SIZE`         |
| Packages       | lowercase, no accent | `com.project.module` |

---

## Class Structure

Standard order inside classes:

```java
public class Example {

    // 1. Constants

    // 2. Attributes

    // 3. Constructors

    // 4. Public Methods

    // 5. Private Methods
}
```