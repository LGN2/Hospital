# Debugging Log

This file records notable implementation/debugging issues and their fixes.

| Error / symptom | HTTP / build status | Cause | Fix |
|---|---|---|---|
| Recursive/nested JSON from JPA relationships | 500 / serialization risk | Bidirectional entity relationships were exposed by controllers | API boundary was changed to DTO-only responses and DTO request bodies |
| Missing/inactive resource produced generic server error | 500 | Services used generic RuntimeException | Services use ResourceNotFoundException and global handling returns 404 |
| Invalid business operation produced inconsistent error | 400/500 | Business rules used generic exceptions | BadRequestException is used for invalid appointment, room-capacity, discharge and relationship rules |
| Invalid DTO fields reached service layer | 400 | Request validation was incomplete | DTO constraints plus @Valid enforce field/size/date/email/positive rules |
| Past appointment could be created | 400 | Appointment date was not consistently guarded | @Future plus service-level future-date check |
| Room over-capacity admission | 400 | Occupancy must account for active, non-discharged admissions | Admission service checks current room occupancy before save |
| Discharge date before admission date | 400 | Cross-field date ordering | Admission service rejects discharge dates before admit date |
| Soft-deleted records visible | 404/read filtering | Reads used unrestricted repository access | Active-only repository methods are used for API reads |
| Database credentials risk in source | Configuration/security | Credentials should not be committed | application.properties reads DB_USERNAME and DB_PASS environment variables |
