# profiles

Backend service that serves a RESTful API that filters profiles based on some pre-defined filters.

`/api/profiles?photo=true`

It was built using *Spring Boot* and uses a PostgreSQL database to pull the records from.

Both the Java application and the database have Dockerfiles that can be used to build images.
A `docker-compose.yaml` has also been provided to make it easier to run both the service and the database at the same time.

## Instructions

First, build the images (you can change the image tags):
> $ docker build -t robertofilho/pg-profiles preseeded-database

Now the service image (this will take longer):
> $ docker build -t robertofilho/profiles .

After both images have been built:
> docker-compose up -d

The compose tool will create 2 containers and start the application on port 8081.

Test if everything worked out:
> $ curl http://localhost:8081/api/profiles