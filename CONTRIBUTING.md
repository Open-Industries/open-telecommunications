# Contributing

This project follows the life cycle in `docs/lifecycle/software-lifecycle.md`
(ISO/IEC/IEEE 15288 & 12207) and the architecture in
`docs/architecture/architecture-description.md` (ISO/IEC/IEEE 42010).

Rules for every change:

1. **Contract-first**: update `contracts/` with the code.
2. **Decisions as ADRs**: architecture-significant changes add or supersede an ADR.
3. **Tests**: `mvn verify` must pass; new behavior ships with tests (QS-09).
4. **Twelve-Factor**: no config in code, no secrets in the repo, logs to stdout only.
5. **Telecom invariants**: prepaid balances never go negative; permanent identifiers
   never in clear; lifecycle transitions take no body; all example data is synthetic.
6. **Honest standards language**: everything is *aligned to* the cited standards —
   never claim certification, 3GPP conformance, or regulatory compliance.

By contributing you agree your work is licensed under Apache-2.0.
