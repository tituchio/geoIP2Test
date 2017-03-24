# geoIP2Test
GeoIP2 Java API implementation, for more documentation please read http://maxmind.github.io/GeoIP2-java/

### GeoIP2 account and license configuration
Put your userId and licenseKey of GeoIP2 account in `webapp/resources/config.properties`
```properties
userId=1234
licenseKey=yourlicensekey
```

### List of IP(s) you want to inspect
Put your list of IP you want to inspect in `data/ipList.csv`.
This source code use 10 selected IP from 5 country in CSV format as follows : 
```csv
   202.179.177.21,naver.com, Naver Corporation
   202.134.0.219,telkom.co.id, Telkom Indonesia
   113.13.101.208,baidu.com, Baidu, Inc.
   216.58.200.14,google.com, Google Inc.
   202.136.9.66,singtel.com, Singtel Singapore
```

### MaxMind GeoLite DB Binary
- Download database from http://dev.maxmind.com/geoip/geoip2/geolite2/
- Copy the downloaded DB (.mmdb) to directory `data`

### How to run the project?
- Open eclipse > import project
- Right click on project >> Run 
