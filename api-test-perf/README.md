# learn-gRPC-protobuf

Calculate Rest

```
ab -n 1000 -c 100 http://localhost:8080/rest/calculate/1000
```

Calculate Grpc
```
ab -n 1000 -c 100 http://localhost:8080/grpc/calculate/1000
```

Calculate Grpc Stream
```
ab -n 1000 -c 100 http://localhost:8080/grpc/stream/calculate/1000
```

Calculate Rest Posts
```
ab -n 1000 -c 100 http://localhost:8080/rest/posts/100
```

Calculate Grpc Posts
```
ab -n 1000 -c 100 http://localhost:8080/grpc/posts/100
```