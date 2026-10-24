# Software Engineering Interview Masterclass

A long-term, company-agnostic **Java 25** learning repository: algorithms → in-memory systems → concurrency → storage → networking → distributed systems → production architecture.

## Prerequisites

- JDK 25 (toolchains enabled)
- Gradle 9.x (Gradle Wrapper can be generated using `gradle wrapper --gradle-version 9.2.1`)
- Git

## First run

```bash
gradle :systems:in-memory:kv-store:test
gradle test
# generate the wrapper once you have Gradle installed:
gradle wrapper --gradle-version 9.2.1
./gradlew test
```

The ZIP contains Gradle build files but **does not contain wrapper binaries**. Generate and commit the Gradle wrapper once using an installed Gradle 9.x. No tests have been executed in the delivery environment (it has JDK 21 and no Gradle).

## Structure

```text
fundamentals/     data-structures, algorithms
systems/          in-memory/kv-store, concurrency, storage, networking, distributed, messaging
architecture/     system-design
engineering/      performance, reliability
docs/             roadmap, progress, interview playbook, ADRs
.github/workflows CI
```

## Rules

1. One independent Gradle subproject per long-running system exercise; one well-named package per small algorithm exercise.
2. Every exercise has a README with requirements, API, invariants, complexity, trade-offs, and interview questions.
3. Build the simplest correct solution first. Only introduce concurrency, persistence or distribution when requirements justify it.
4. Tests live next to each module in `src/test/java`; prefer deterministic testing, including injected clocks.
5. Compare versions using separate packages, e.g. `v1`, `v2`, `v3`; do not erase earlier working solutions.
6. Keep design decisions in `docs/adr` and learning state in `docs/PROGRESS.md`.

Start: [`systems/in-memory/kv-store`](systems/in-memory/kv-store/README.md).
