- # COT Cloud Application

<img width="416" alt="image" src="https://user-images.githubusercontent.com/13181300/169669139-d2ffcc02-fd11-4789-8dda-f532b267a883.png">


- build the frontend image
- ```>> docker build -t davidjmartin/cot-report-frontend:latest```


- mvn clean install

  - ```cd deployment```
  - ```helm install cot-app```


- update host file (C:\Windows\System32\drivers\etc\hosts)
  - ```127.0.0.1 cot.com```


- access front end via
- ```cot.com:3000```


## example screenshot
![Screenshot](readme/Capture.PNG)

# Confluent Kafka
https://docs.confluent.io/operator/current/co-quickstart.html
https://stackoverflow.com/questions/59565537/how-to-query-directly-from-a-kafka-topic

# Deploy
export KUBECONFIG=/etc/rancher/k3s/k3s.yaml
