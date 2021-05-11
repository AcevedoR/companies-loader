# companies-loader

## intro
Homework for a job interview, demonstrating streaming a JSON file in Java.

## setup
- java 11 needed

Change spring profile in `src/main/resources/application.yaml` :

- **dev** = with real companies.json file and mocked ipstack calls (random countries)

- **prod** = with tiny companies_light.json file and real ipstack calls

launch with `./mvnw spring-boot:run` or `mvn spring-boot:run`
  
## state
Streaming `companies.json` file with Jackson and outputting a result.

`funding_rounds` was used instead of `total_money_raised`, and the result (average funding by country) excludes missing/invalid data and companies founded with "0" amount.

Calling Ipstack API in batches (current version can't handle more than a few companies) and currency conversion were not realized, because of the restricted available time I have. 

2 versions were realized (cited above) to demonstrate the project, while taking into account that Ipstack API calls were not realized.


## personal notes
To analyze current data structure
```
docker run -it -v /var/local/companies-loader-mongo-data:/data/db -p 27017:27017 --name mongodb -d mongo
```
```
docker exec -it mongodb sh -c "cd /data/db && mongoimport --db companiesLoaderDB --collection companies --file companies.json --jsonArray"
```
```
git clone https://github.com/variety/variety.git
```
``` 
mongo companiesLoaderDB --eval "var collection = 'companies'" variety/variety.js > variety-analysis-result.ascii
```

maybe see [java Money API](https://github.com/JavaMoney/jsr354-ri), that will fetch currency exchange rates remotely
