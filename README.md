# companies-loader

## setup
- java 11 needed

Change spring profile in `application.yaml`

**dev** = with real companies.json file and mocked ipstack calls (random countries)

**prod** = with tiny companies_light.json file and real ipstack calls

launch with `mvn spring-boot:run`
  
## process
- streaming `companies.json file and outputting a result


## reminders
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
