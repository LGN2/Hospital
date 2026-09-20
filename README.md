# Hospital Management System

Spring Boot REST API for managing hospitals, departments, doctors, patients, appointments, medical records, prescriptions, rooms, admissions, bills, guardians, and staff.

## Stack
- Java 21
- Spring Boot 4
- Spring Web MVC
- Spring Data JPA / Hibernate
- Jakarta Validation
- MySQL
- Lombok

## Setup
Create a MySQL database named `hospital_management` (the configured URL can also create it automatically), then set:

```
DB_USERNAME=<mysql-user>
DB_PASS=<mysql-password>
```

Run with `./mvnw spring-boot:run` (Windows: `mvnw.cmd spring-boot:run`).

## API
All create/update endpoints use validated DTOs. Reads exclude soft-deleted records. Deletes set `isActive=false`.

Main resources: `/hospitals`, `/departments`, `/doctors`, `/patients`, `/appointments`, `/medical-records`, `/prescriptions`, `/rooms`, `/admissions`, `/bills`, `/guardians`, `/staff`.

Business endpoints include hospital statistics, doctor appointments by date/count, patient medical-record history, admitted patients by floor, available rooms, outstanding bills, patient billed totals, and admission discharge.

Import `postman/Hospital_API.postman_collection.json` and `postman/Hospital_Local.postman_environment.json` into Postman for an end-to-end run.
