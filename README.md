# Javalin Demo App 
This demo app is for the **Lightweight REST APIs With Javalin** Workshop given by the palosc
between 2020-07-09 and 2020-07-12 . This is the final code from the instrcutor demo. 

Technologies Used
- Javalin for REST APIs
- JDBI for SQL data access
- Apache Commons for utilities

## Running
In order to run this app locally, you either need to change the database parameters
in the Main.java class or create the schema and credentials as they are. Additionally,
you will need to run the tables.sql to run schema generation. 

## Deployment
for deployment, you will need to modify the todoapi.service and start.sh scripts 
in the src/scripts directory to match your needs
