Clone the repository: \
``` git clone <repository-url>``` \
``` cd <repository-directory> ```

Build the Docker images:\
```docker-compose build```

Start the application:\
```docker-compose up -d```

Access the services:
ManagementService: http://localhost:8080\
PostgreSQL: accessible within the network at service-db:5432\
MongoDB: accessible within the network at mongodb:27017\
pgAdmin: http://localhost:5051

To stop the application, run:\
```docker-compose down```

View logs for debugging:\
```docker-compose logs -f```