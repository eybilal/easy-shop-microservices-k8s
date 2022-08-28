# easy-shop

Decisions:
- Validation must be done on each layer: dto validation and Entity validation

Rules:
- communication between layer: controller -> service -> repository (we must not violate it)
- communication between microservices: 
  - Asynchronous: cas bla bla
  - synchronous: cas bla bla
