# Exercice 4 - Migration vers Microservices

## Architecture

- API Gateway: `http://localhost:8080`
- Book Service: `http://localhost:8081`
- User Service: `http://localhost:8082`
- Loan Service: `http://localhost:8083`

## Build et tests

Depuis la racine du projet:
```bash
mvn clean package
mvn test
```

Build d'un seul module:
```bash
mvn -pl loan-service -am clean package
```

## Démarrer les microservices avec Maven

Terminal 1:
```bash
mvn -pl book-service spring-boot:run
```

Terminal 2:
```bash
mvn -pl user-service spring-boot:run
```

Terminal 3:
```bash
mvn -pl loan-service spring-boot:run
```

Terminal 4:
```bash
mvn -pl api-gateway spring-boot:run
```

## Arrêter les services

Si lancés en avant-plan: `Ctrl + C` dans chaque terminal.

Si lancés en arrière-plan:
```bash
pkill -f "book-service-1.0.0.jar"
pkill -f "user-service-1.0.0.jar"
pkill -f "loan-service-1.0.0.jar"
pkill -f "api-gateway-1.0.0.jar"
```

## Commandes utiles - Book Service (`8081`)

Lister les livres:
```bash
curl http://localhost:8081/api/books
```

Détail d'un livre:
```bash
curl http://localhost:8081/api/books/1
```

Créer un livre:
```bash
curl -X POST http://localhost:8081/api/books \
  -H "Content-Type: application/json" \
  -d '{"title":"Clean Code","author":"Robert C. Martin","isbn":"9780132350884"}'
```

Modifier un livre:
```bash
curl -X PUT http://localhost:8081/api/books/1 \
  -H "Content-Type: application/json" \
  -d '{"title":"Book 1 updated","author":"Author 1","isbn":"99R8937R39784734","available":true}'
```

Modifier la disponibilité:
```bash
curl -X PUT "http://localhost:8081/api/books/1/availability?available=false"
curl -X PUT "http://localhost:8081/api/books/1/availability?available=true"
```

Supprimer un livre:
```bash
curl -X DELETE http://localhost:8081/api/books/3
```

## Commandes utiles - User Service (`8082`)

Lister les utilisateurs:
```bash
curl http://localhost:8082/api/users
```

Détail d'un utilisateur:
```bash
curl http://localhost:8082/api/users/1
```

Créer un utilisateur:
```bash
curl -X POST http://localhost:8082/api/users \
  -H "Content-Type: application/json" \
  -d '{"name":"Charlie C","email":"charlie@example.com"}'
```

Modifier un utilisateur:
```bash
curl -X PUT http://localhost:8082/api/users/1 \
  -H "Content-Type: application/json" \
  -d '{"name":"Alice Updated","email":"alice.updated@example.com"}'
```

Supprimer un utilisateur:
```bash
curl -X DELETE http://localhost:8082/api/users/3
```

## Commandes utiles - Loan Service (`8083`)

Lister les prêts:
```bash
curl http://localhost:8083/api/loans
```

Détail d'un prêt:
```bash
curl http://localhost:8083/api/loans/1
```

Créer un prêt (appels inter-services vers `book-service` et `user-service`):
```bash
curl -X POST http://localhost:8083/api/loans \
  -H "Content-Type: application/json" \
  -d '{"bookId":1,"userId":1}'
```

Retourner un livre:
```bash
curl -X POST http://localhost:8083/api/loans/1/return
```

Supprimer un prêt:
```bash
curl -X DELETE http://localhost:8083/api/loans/1
```

## Vérifier les appels inter-services

Créer un prêt:
```bash
curl -X POST http://localhost:8083/api/loans \
  -H "Content-Type: application/json" \
  -d '{"bookId":1,"userId":1}'
```

Le livre devient indisponible:
```bash
curl http://localhost:8081/api/books/1
```

Retourner le prêt:
```bash
curl -X POST http://localhost:8083/api/loans/1/return
```

Le livre redevient disponible:
```bash
curl http://localhost:8081/api/books/1
```

## Utiliser l'API Gateway (`8080`)

Lister via gateway:
```bash
curl http://localhost:8080/api/books
curl http://localhost:8080/api/users
curl http://localhost:8080/api/loans
```

Créer un prêt via gateway:
```bash
curl -X POST http://localhost:8080/api/loans \
  -H "Content-Type: application/json" \
  -d '{"bookId":2,"userId":2}'
```

Retourner un prêt via gateway:
```bash
curl -X POST http://localhost:8080/api/loans/2/return
```