# Android Architecture Rules

An experimental project with **8 Android architectural design rules** and Java source
code examples, for use with the DesignFix tool for rule violation detection and fix testing.

## Rules

| #  | Rule                                           | Difficulty | Violated By            |
|----|------------------------------------------------|------------|------------------------|
| 1  | DTOs must have a no-argument constructor       | Trivial    | `OrderDto.java`        |
| 2  | ViewModels must access data through Repositories | Easy     | `OrderViewModel.java`  |
| 3  | Room @Entity classes must have @PrimaryKey     | Trivial    | `OrderEntity.java`     |
| 4  | ViewModels must not hold Activity/Context refs | Easy       | `ProductViewModel.java`|
| 5  | DAO interfaces must be annotated with @Dao     | Trivial    | `OrderDao.java`        |
| 6  | LiveData must not be exposed as MutableLiveData | Medium    | `OrderViewModel.java`  |
| 7  | Use Cases must have a single execute() method  | Medium     | `GetOrderUseCase.java` |
| 8  | ViewModels must not make direct network calls  | Medium-Hard| `SearchViewModel.java` |

## Project Structure

```
src/com/android/architecture/
├── dto/                  # Data Transfer Objects (Rule 1)
├── repository/           # Repository pattern classes (Rule 2)
├── viewmodel/            # ViewModel classes (Rules 2, 4, 6, 8)
├── data/
│   ├── entity/           # Room Entity classes (Rule 3)
│   └── dao/              # Data Access Object interfaces (Rule 5)
└── domain/
    └── usecase/          # Clean Architecture Use Cases (Rule 7)
```

## Tags

| Tag               | Description                                      |
|-------------------|--------------------------------------------------|
| DTO               | Data Transfer Object serialization rules         |
| Repository        | Repository pattern for data access               |
| Room              | Room Persistence Library annotations             |
| ViewModel         | ViewModel lifecycle and encapsulation             |
| Architecture      | General Android architecture best practices       |
| LiveData          | LiveData encapsulation and exposure               |
| CleanArchitecture | Use Case / Interactor patterns                   |
| Networking        | Network layer separation                          |

## How to Use

1. Open this project as a workspace in VSCode
2. Ensure the DesignFix extension is installed and active
3. Start the React client
4. The extension converts Java files to XML via srcML, sends rules/tags to the client
5. The client evaluates XPath-based rules and reports violations

Each rule has **2 compliant** and **1 non-compliant** code file for testing.
