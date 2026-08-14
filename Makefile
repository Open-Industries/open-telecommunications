.PHONY: validate test

validate:
	@python3 -c 'import json,pathlib; [json.loads(p.read_text()) for p in pathlib.Path(".").rglob("*.json")]; print("JSON valid")'

test: validate
	@echo "Add executable contract, integration, resilience and performance tests under tests/."
