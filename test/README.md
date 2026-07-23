# Compile gate

Checks that the codebase still **builds** after a design-rule violation is fixed — the
compile half of the fix-validation criterion (the other half, "the violation is resolved",
is checked separately by the DesignFix rule evaluation).

```bash
node test/compile-check.mjs --strict   # non-zero exit = the change broke the build
node test/compile-check.mjs            # same compile, report-only (always exit 0)
```

## Why `stubs/` exists

The `src/` fixtures reference Android / Room / Retrofit / OkHttp types that aren't in this
repo, so plain `javac src/**` fails with ~50 `cannot find symbol` errors on an untouched
checkout — no usable baseline. [`stubs/`](../stubs) supplies 14 minimal declarations in
their **real** package names (`androidx.lifecycle`, `androidx.room`, `android.content`,
`retrofit2`, `okhttp3`) plus the `ApiService` interface `SearchViewModel` references but
which never existed. The gate compiles `src/` together with `stubs/`, so everything
type-checks with a stock **JDK 8** — no Gradle, no Android SDK, no network.

Real package names are deliberate: a fix written with realistic imports
(`import androidx.lifecycle.LiveData;`) resolves instead of failing spuriously.

## Why `--strict` alone is enough

Every seeded violation is *legal Java* — a missing no-arg constructor, an exposed
`MutableLiveData`, an absent `execute()`. None is a compile error, so the **baseline
compiles with 0 errors**. A non-zero `--strict` exit therefore means exactly *"this change
broke the build"*, with no before/after baseline diff required.

## Scope

Catches **type and structure** breakage — the realistic failure mode of a fix: typos, wrong
types, bad signatures, a helper that no longer resolves, a reference to a class that doesn't
exist. It does **not** run Room's annotation processor or any Android runtime; verifying
something like "@Entity has a @PrimaryKey" is the design-rule checker's job, not the
compiler's.

**If a fix legitimately uses a third-party API that isn't stubbed yet**, add a one-line
declaration under `stubs/` in the matching package. Stubs only need enough surface to
type-check.

## Notes

- `javac` is auto-detected: `JAVAC` → `JAVA_HOME` → Eclipse Adoptium `jdk-8*` → `PATH`.
- Compiled classes go to `build/` (git-ignored).
- The `import` lines added to the entity/DAO/ViewModel fixtures are what let them resolve
  against the stubs. They don't affect rule matching — DesignFix strips `<package>` and
  `<import>` before comparing snippets.
