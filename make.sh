cd eureka-service-final/
docker build . -t eureka-service-final
cd ..

cd config-service-final/
docker build . -t config-service-final
cd ..

cd movie-service-final/
docker build . -t movie-service-final
cd ..

cd catalog-service-final/
docker build . -t catalog-service-final
cd ..

cd gateway-service-final/
docker build . -t gateway-service-final
cd ..

cd serie-service-final/
docker build . -t serie-service-final
cd ..

docker-compose up