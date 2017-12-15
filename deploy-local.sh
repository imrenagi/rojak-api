#!/bin/bash

docker-compose stop backend
docker-compose -f docker-compose.yml -f docker-compose.dev.yml up -d --build backend