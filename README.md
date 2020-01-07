# musicbrainzsearch

This application was generated using JHipster 6.6.0, you can find documentation and help at [https://www.jhipster.tech/documentation-archive/v6.6.0](https://www.jhipster.tech/documentation-archive/v6.6.0).

## Prerequrists 

* JDK 11
* Docker

## Using Docker to start elastic search container

To start a postgresql database in a docker container, run:

    docker-compose -f src/main/docker/elasticsearch.yml up -d

To stop it and remove the container, run:

    docker-compose -f src/main/docker/elasticsearch.yml down


## Development

To start your application in the dev profile, run:

    ./gradlew

For further instructions on how to develop with JHipster, have a look at [Using JHipster in development][].



