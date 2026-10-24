# Lesson 1 — In-Memory Key-Value Store

**Stage:** V1 / single-threaded, no TTL, no durability.

## Interview prompt (English)

Design an in-memory key-value store with `put`, `get`, `delete`, `containsKey`, and `size`. Keys and values are non-null strings. Updates overwrite the prior value. Optimize for average constant-time lookups.

## Clarifying questions

- Can the key or value be null?
- Should overwrites be allowed?
- What do we return when the key does not exist?
- Does the store need to be thread-safe?
- Does the store need TTL or persistence?

## Contract

- Null key/value: `NullPointerException`.
- Unknown key: `Optional.empty()`.
- `delete` returns `true` only if a key existed.
- `size()` counts current entries.
- `HashMap` is not thread-safe.

## Invariants

1. Every stored key is non-null.
2. Every stored value is non-null.
3. At most one value per key.
4. `size()` equals the number of distinct stored keys.

## Complexity

Expected average `put/get/delete/containsKey`: O(1); `size`: O(1); total space: O(n). Individual operations can take longer owing to collisions / resizing.

## Run

```bash
gradle :systems:in-memory:kv-store:test
```

## Follow-ups

- V2: TTL, injected `Clock`, lazy expiration
- V3: concurrent operations and atomicity
- V4: eviction, capacity and background cleanup
- V5: WAL, snapshots, crash recovery
- V6: transactions / MVCC
- V7: replication and sharding

## Interview Q&A

**Q: Why HashMap?** A: Average O(1) updates and lookups for hashable keys.

**Q: Why Optional?** A: It makes absence explicit; null values are disallowed.

**Q: Is it thread-safe?** A: No; concurrent access requires synchronization or a concurrent design.

**Q: Why reject null?** A: It distinguishes missing entries from stored values and simplifies contracts.

**Q: Why not add Redis?** A: There is no distribution, memory-sharing or durability requirement yet.
