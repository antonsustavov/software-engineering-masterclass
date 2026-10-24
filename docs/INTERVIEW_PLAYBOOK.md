# Interview Playbook

## Opening (English)

- "Let me clarify the functional requirements and the expected constraints."
- "Should this component support concurrent access?"
- "Are there any durability or expiration requirements?"
- "I'll start with a simple correct implementation and then optimize if needed."
- "Let's establish the invariants and identify the linearization points."
- "What failure modes or scale constraints should we plan for?"

## Answer format

1. Restate problem & clarify requirements (EN)
2. State assumptions, API and edge cases
3. Simple correct solution + time/space complexity
4. Tests including invalid input
5. Follow-up changes and new invariants
6. Thread safety, durability, distribution only if relevant
7. Trade-offs and concise verbal recap (EN)

## Review checklist

Correctness · Time/space · Concurrent safety · Crash safety · Scaling · Failure paths · Observability · Evolution.
