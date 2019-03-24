# JMS listener with Logstash Integration

## setup logstash

From the `logging` directory, execute;

```
docker run --name logstash -p 4560:4560 -p 5044:5044 -p 9600:9600 -v $(pwd):/usr/share/logstash/pipeline/ -v $(pwd)/data/:/usr/share/logstash/data/ -d docker.elastic.co/logstash/logstash-oss:6.6.2
docker logs logstash -f
```

Log messages will show up in the logstash docker log


## KeyStore setup

keytool -genkey -keyalg RSA -validity 365 -keystore server.keystore -storetype JKS

