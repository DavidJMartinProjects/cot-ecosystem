# COT Web App

- build the frontend image
- ```>> docker build -t davidjmartin/cot-report-frontend:latest```


- mvn clean install

  - ```cd deployment```
  - ```helm install cot-app```


- update host file (C:\Windows\System32\drivers\etc\hosts)
  - ```127.0.0.1 cot.com```


- access front end via
- ```cot.com:3000```

# Deploy
export KUBECONFIG=/etc/rancher/k3s/k3s.yaml
