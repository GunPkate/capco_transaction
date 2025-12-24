# Transaction
<pre> 
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
</pre>


## Getting started
```
docker-compose up
```
<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/6313e6fa-7edb-46c0-86c0-ee393f6ac9fa" />

db postgres
jdbc:postgresql://localhost:5470/postgres


Method	Endpoint	            Description
GET	    /transactions	        List transactions
POST	localhost:8084/transaction	Create a new transaction save to db
POST    http://j0eye.wiremockapi.cloud/payments/submit  external mock -> WireMock

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/02d917c7-3259-4272-b941-0e8524f6a822" />

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/bb0c51ac-17b1-4071-8341-6404cad135e8" />

<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/ecaf77cd-02ba-40aa-891f-02d0f163a5a4" />





