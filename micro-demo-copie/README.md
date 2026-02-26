# Exercice 1 :

## Pattern 1 : BUILDER PATTERN

**Question : Quel est l'avantage du Builder par rapport à un constructeur avec 10 paramètres ?**

Le Builder permet de construire un objet de manière plus lisible et maintenable, surtout lorsque l'objet a de nombreux
paramètres. Il évite les erreurs liées à l'ordre des paramètres et rend le code plus clair en utilisant des méthodes
nommées pour chaque paramètre.

## Pattern 2 : FACTORY PATTERN

**Question : Quelle approche est plus flexible pour ajouter un nouveau type de notification ?**

L'approche Multiple Factory Classes est la plus flexible pour ajouter un nouveau type de notification, car elle permet
d'étendre le système de notifications sans modifier le code existant.
Le respect de l'Open/Closed Principle est donc meilleur.

## Pattern 3 : SINGLETON PATTERN

**Question : Pourquoi l'Enum Singleton est-il supérieur au Singleton classique ?**

L'Enum Singleton est meilleur, car il permet de garantir nativement que l'instance de l'Enum Singleton est unique avec
une implémentation plus simple et robuste.

## Pattern 3 : SINGLETON PATTERN

**Question : Pourquoi une deep copy est-elle importante ici ?**

La deep copy est importante pour que les objets du clone soient indépendants de l'original et donc que toutes
modifications du clone n'affectent pas l'original.

# Exercice 2 :

## PRINCIPLES 1 : SINGLE RESPONSIBILITY PRINCIPLE (SRP)

**Question : Pourquoi c'est plus facile de tester maintenant ?**

C'est plus facile, car chaque classe possède maintenant une responsabilité unique donc on peut tester chaque
comportement de manière indépendante.

## PRINCIPLE 2 : OPEN/CLOSED PRINCIPLE (OCP)

**Question : Comment les stratégies rendent la classe ouverte/fermée ?**

Les stratégies rendent la classe ouverte/fermée, car la classe BookRater ne change plus lorsqu'un nouveau type est créé
et elle est ouverte à l'extension, parce qu'il suffit d'ajouter une nouvelle implémentation de RatingStrategy et de
l'enregistrer.

## PRINCIPLE 3 : LISKOV SUBSTITUTION PRINCIPLE (LSP)

**Question : Comment les interfaces résolvent le problème ?**

Elles résolvent le problème en séparant les contrats, ce qui évite d'imposer la fonction fly() aux oiseaux qui ne volent
pas et cela garantit que chaque implémentation est substituable sans comportement invalide.

## PRINCIPLE 4 : INTERFACE SEGREGATION PRINCIPLE (ISP)

**Question : Pourquoi les interfaces spécialisées sont-elles meilleures ?**

Elles sont meilleures, car elles réduisent le couplage entre les classes et les interfaces ce qui rend le code plus
clair et plus facile à tester.

## PRINCIPLE 5 : DEPENDENCY INVERSION PRINCIPLE (DIP)

**Question : Comment l'injection facilite-t-elle les tests ?**

L'injection facilite les tests, car elle permet de remplacer facilement les dépendances réelles par des implémentations
de test, cela isole OrderService et rend les tests plus simples et rapides.

# Exercice 3 : Architecture Monolithique

**Question : Pourquoi la séparation en couches est-elle importante ?**

La séparation en couches est importante, car elle clarifie les responsabilités, réduit le couplage, facilite les tests
et la maintenance du service.

# Exercice 4 :  Migration vers Microservices

**Question : Quels sont les avantages et inconvénients des microservices ?**

| Avantages                                   | Inconvénients                                                            |
|---------------------------------------------|--------------------------------------------------------------------------|
| Scalabilité ciblée par service              | Complexité opérationnelle (CI/CD, monitoring, orchestration)             |
| Déploiements indépendants et plus fréquents | Complexité réseau (latence, timeouts, retries)                           |
| Meilleure isolation des pannes              | Gestion des données plus difficile (transactions distribuées, cohérence) |
| Autonomie des équipes par domaine           | Tests d’intégration/end-to-end plus complexes                            |
| Flexibilité technologique                   | Coûts d’infrastructure et de maintenance plus élevés                     |