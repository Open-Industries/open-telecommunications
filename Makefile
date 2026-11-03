# Open Telecommunications — developer entry points
.PHONY: build test verify dev-subscriber dev-session dev-charging compose-up compose-down

build:
	mvn -q package -DskipTests

test:
	mvn test

verify:           ## Full verification (life-cycle gate G3)
	mvn verify

dev-subscriber:
	mvn -pl services/subscriber-service quarkus:dev

dev-session:
	mvn -pl services/session-service quarkus:dev

dev-charging:
	mvn -pl services/charging-service quarkus:dev

compose-up:
	docker compose up -d

compose-down:
	docker compose down
