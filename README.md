# profiles

Backend service that serves a RESTful API that filters profiles based on some pre-defined filters.

`/api/profiles?photo=true`

It was built using *Spring Boot* and uses a PostgreSQL database to pull the records from.

Both the Java application and the database have Dockerfiles that can be used to build images.
A `docker-compose.yaml` (located in the folder _docker-compose_) has also been provided to make it easier to run all services (frontend, service, database) at the same time.

## Instructions

First, build the images (you can change the image tags):
> $ docker build -t robertofilho/pg-profiles preseeded-database

Now the service image (this will take longer):
> $ docker build -t robertofilho/profiles .

And also the frontend:
```bash
$ cd ../profiles-frontend
$ docker build -t robertofilho/matches .
```

After both images have been built, it's time to start the docker compose recipe. Run at the root of the project:
> cd docker-compose && docker-compose up -d

The compose tool will create 3 containers and start the application on port 8081.

Test if everything worked out:
> $ curl http://localhost:8081/api/profiles

And then you can just access the frontend on your browser: http://localhost