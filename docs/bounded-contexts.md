# Bounded Contexts de CareLink

La arquitectura del proyecto sigue un modelo de **52 Bounded Contexts**, donde **cada tabla de la base de datos se corresponde exactamente con un Bounded Context (paquete) independiente**.

Este diseño estricto de segregación por tabla sigue el patrón requerido para lograr máxima modularidad y aislamiento en el dominio.

Paquete base: `com.carelink.{domain|application|infrastructure}.<contexto>`

## Lista de Contextos Generados

A continuación se listan las 52 clases de dominio generadas, que representan las raíces de agregado para sus respectivos Bounded Contexts:

1. `Country` (paquete: `country`)
2. `Department` (paquete: `department`)
3. `Municipality` (paquete: `municipality`)
4. `ContactRelationshipType` (paquete: `contactrelationshiptype`)
5. `BiologicalSex` (paquete: `biologicalsex`)
6. `IdDocumentType` (paquete: `iddocumenttype`)
7. `SpecialistRole` (paquete: `specialistrole`)
8. `AcademicProgram` (paquete: `academicprogram`)
9. `Client` (paquete: `client`)
10. `ClientAllergy` (paquete: `clientallergy`)
11. `TrustedContact` (paquete: `trustedcontact`)
12. `ClientTrustedContact` (paquete: `clienttrustedcontact`)
13. `ContactPhoneNumber` (paquete: `contactphonenumber`)
14. `ContactEmailAddress` (paquete: `contactemailaddress`)
15. `Specialist` (paquete: `specialist`)
16. `SpecialistCredential` (paquete: `specialistcredential`)
17. `AppointmentType` (paquete: `appointmenttype`)
18. `AppointmentModality` (paquete: `appointmentmodality`)
19. `AppointmentStatus` (paquete: `appointmentstatus`)
20. `Appointment` (paquete: `appointment`)
21. `HealthRecordStatus` (paquete: `healthrecordstatus`)
22. `HealthRecord` (paquete: `healthrecord`)
23. `SessionNote` (paquete: `sessionnote`)
24. `MentalStateEvaluation` (paquete: `mentalstateevaluation`)
25. `DangerLevel` (paquete: `dangerlevel`)
26. `DangerAssessment` (paquete: `dangerassessment`)
27. `EvaluationInstrumentType` (paquete: `evaluationinstrumenttype`)
28. `InformedConsentType` (paquete: `informedconsenttype`)
29. `ClassificationSystem` (paquete: `classificationsystem`)
30. `CarePlanStatus` (paquete: `careplanstatus`)
31. `CarePlan` (paquete: `careplan`)
32. `CareGoalStatus` (paquete: `caregoalstatus`)
33. `CareGoal` (paquete: `caregoal`)
34. `DrugAdministrationRoute` (paquete: `drugadministrationroute`)
35. `SenderRole` (paquete: `senderrole`)
36. `MessageFormat` (paquete: `messageformat`)
37. `UrgencyLevel` (paquete: `urgencylevel`)
38. `SupportConversationStatus` (paquete: `supportconversationstatus`)
39. `SupportConversation` (paquete: `supportconversation`)
40. `ConversationMember` (paquete: `conversationmember`)
41. `SupportMessage` (paquete: `supportmessage`)
42. `LlmVendor` (paquete: `llmvendor`)
43. `LlmModel` (paquete: `llmmodel`)
44. `ConversationAiConfig` (paquete: `conversationaiconfig`)
45. `LlmExecutionStatus` (paquete: `llmexecutionstatus`)
46. `LlmExecution` (paquete: `llmexecution`)
47. `LlmExecutionMetric` (paquete: `llmexecutionmetric`)
48. `LlmExecutionError` (paquete: `llmexecutionerror`)
49. `HandoffStatus` (paquete: `handoffstatus`)
50. `Handoff` (paquete: `handoff`)
51. `HandoffAssignment` (paquete: `handoffassignment`)
52. `HandoffStatusAudit` (paquete: `handoffstatusaudit`)

## Estructura de Cada Contexto

Cada uno de estos 52 contextos está implementado a través de las tres capas principales:

1. **Domain (`domain/.../<contexto>/`)**:
   - `model/aggregate/`: Entidad principal del contexto.
   - `model/valueobject/`: Objetos de valor (ej. `Id`).
   - `event/`: Eventos de dominio generados.
   - `port/repository/`: Puertos para acceder a los datos.

2. **Application (`application/.../<contexto>/`)**:
   - `command/`: Comandos de entrada.
   - `dto/`: Objetos de transferencia de respuesta.
   - `exception/`: Excepciones de negocio.
   - `usecase/`: Casos de uso (`Register`, `Update`, `GetById`, `List`, `Delete`).

3. **Infrastructure (`infrastructure/.../<contexto>/`)**:
   - `adapters/in/rest/`: Controladores Spring REST.
   - `adapters/in/rest/dtos/`: DTOs para Request HTTP.
   - `adapters/out/persistence/jpa/`: Entidad JPA y adaptador de repositorio Spring Data.
   - `configuration/`: Beans para enlazar la capa de Aplicación con Infraestructura.

## Reglas Arquitectónicas

- **Sin dependencias cíclicas:** Cada paquete es independiente.
- **Comunicación por Ids:** Las relaciones entre contextos (ej. Foreign Keys en base de datos) se modelan utilizando tipos fuertemente tipados (`UUID`) encapsulados en Value Objects de Id.
- **Inyección de dependencias:** Configuración explícita en `BeansConfig` por paquete, sin usar escaneo de componentes global que mezcle contextos.
