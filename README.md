# Kotlin project

Spring Boot project with the following specs:

- RESTful API
   - http://locahost:9100/api/v1/books
- Hexagonal architecture
   - application
     - configuration
   - domain
      - model
         - gateway
      - shared      
      - use_case
   - infrastructure
      - adapter
         - database
           - book_repository
      - entry_point
        - receiver
          - book
            - dto
      - schedulers    
- MySql 5.7
- ShedLock                

