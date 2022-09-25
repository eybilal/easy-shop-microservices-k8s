# easy-shop-microservices

Decisions:
- Validation must be done on each layer: dto validation and Entity validation

Rules:
- communication between layer: controller -> service -> repository (we must not violate it)
- communication between microservices: 
  - Asynchronous: cas bla bla
  - synchronous: cas bla bla
  
Ingress:
- We will be using ingress-nginx as the Ingress Controller.
- Installation with YAML manifest:
`kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.3.1/deploy/static/provider/cloud/deploy.yaml`
- Check installation:
`kubectl get pods --namespace=ingress-nginx`

Notes:
- Run the following to add the database passwords as secrets:
`kubectl create secret generic easy-shop-db-passwords 
 --from-literal=auth-db=your_auth_db_password 
 --from-literal=customer-db=your_customer_db_password
 --from-literal=inventory-db=your_inventory_db_password
 --from-literal=order-db=your_inventory_db_password`

Service Discovery:
- Kubernetes itself is capable of (server side) service discovery (see: kubernetes.io/docs/concepts/services-networking/service/#discovering-services). Using native kubernetes service discovery ensures compatibility with additional tooling, such as Istio (istio.io), a service mesh that is capable of load balancing, circuit breaker, failover, and much more.

