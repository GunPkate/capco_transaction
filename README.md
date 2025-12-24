# Transaction

├── services/
        ├──transaction
            ├──config
            ├──controller
            ├──model
            ├──repo
            ├──schedule
            ├──service
├── README.md
├── docker-compose.yml

## Getting started
```
docker-compose up
```

db postgres
jdbc:postgresql://localhost:5470/postgres


Method	Endpoint	            Description
GET	    /transactions	        List transactions
POST	localhost:8084/transaction	Create a new transaction save to db
POST    http://j0eye.wiremockapi.cloud/payments/submit  external mock -> WireMock





