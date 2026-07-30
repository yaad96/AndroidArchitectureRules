# Android Architecture Rules

An experimental project with **7 Android architectural design rules** and Java source
code examples, for use with the DesignFix tool for rule violation detection and fix testing.

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

stubs/                    # Compile-only stubs (not rule-checked)
test/compile-check.mjs    # The compilation test
```

## Running the compilation test

A fix to a design rule violation has to satisfy two criteria: the violation is
resolved, **and** the code still compiles. The rule check covers the first; this
compilation test covers the second.

```bash
npm run compile-strict     # exit 0 = builds cleanly, non-zero = the change broke the build
npm run compile            # same compile, report-only (always exits 0)
```

No `npm install` is needed — there are no dependencies. The equivalent direct calls are:

```bash
node test/compile-check.mjs --strict
node test/compile-check.mjs
```

**Requirement:** a JDK 8 `javac`, auto-detected in this order — `JAVAC` → `JAVA_HOME` →
Eclipse Adoptium `jdk-8*` → `javac` on `PATH`. Override with:

```bash
JAVAC="C:/Program Files/Eclipse Adoptium/jdk-8.0.x-hotspot/bin/javac.exe" npm run compile-strict
```

### What it does

The `src/` files reference Android, Room, Retrofit and OkHttp types that are not
vendored here, so `javac src/**` alone fails with ~50 `cannot find symbol` errors on an
untouched checkout. The test compiles `src/` **together with** `stubs/` — minimal
declarations under the real package names (`androidx.lifecycle`, `androidx.room`,
`android.content`, `android.app`, `retrofit2`, `okhttp3`) — so everything type-checks with
a stock JDK 8: no Gradle, no Android SDK, no network. Classes are written to `build/`
(git-ignored).

Every seeded violation is legal Java — a missing no-arg constructor, an exposed
`MutableLiveData`, an absent `execute()`. None is a compile error, so **the baseline
compiles with zero errors**. A non-zero `--strict` exit therefore means exactly "this
change broke the build", with no before/after comparison needed.

### Reading a failure

```
✗ 1 compile error(s):
  src\com\android\architecture\viewmodel\OrderViewModel.java:23: error: cannot find symbol

FAIL (strict): the change does not compile — a fix must not break the build.
```

Run `npm run compile` for the same list without failing the exit code. Typical causes seen
when applying a fix:

- a type introduced by the fix was never imported (e.g. adding `LiveData` getters without
  `import androidx.lifecycle.LiveData;`)
- a field was replaced but an old use site still references it
- the fix delegates to a collaborator that does not exist yet (e.g. a repository class or
  method that must be authored as part of the fix)

### Scope

This catches type and structure breakage — the realistic failure mode of a fix. It does not
run Room's annotation processor or any Android runtime; checking something like "`@Entity`
has a `@PrimaryKey`" is the design rule checker's job, not the compiler's.

If a fix legitimately uses a third-party API that is not stubbed yet, add a one-line
declaration under `stubs/` in the matching package. Stubs only need enough surface to
type-check.

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


