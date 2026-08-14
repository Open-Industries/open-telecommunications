package openindustries.open_telecommunications.governance

import rego.v1

default allow := false

allow if {
  input.correlation_id != ""
  input.schema_version == "1.0"
  input.tenant_id != ""
  input.data_classification in {"public", "internal", "confidential", "restricted"}
}
