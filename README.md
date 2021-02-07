# companies-loader

## setup
- java 11 needed
- docker commands for a local mongo (replace `/var/local/companies-loader-mongo-data` 
  with your location of `companies.json file) :
```
docker run -it -v /var/local/companies-loader-mongo-data:/data/db -p 27017:27017 --name mongodb -d mongo
```
```
docker exec -it mongodb sh -c "cd /data/db && mongoimport --db companiesLoaderDB --collection companies --file companies.json --jsonArray"
```


## reminders
To analyze current data structure
```
git clone https://github.com/variety/variety.git
```
``` 
mongo companiesLoaderDB --eval "var collection = 'companies'" variety/variety.js > variety-analysis-result.ascii
```
