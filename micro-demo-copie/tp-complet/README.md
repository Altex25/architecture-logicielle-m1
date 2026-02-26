# Exercice 1 : 

## Pattern 1 : BUILDER PATTERN
**Question : Quel est l'avantage du Builder par rapport à un constructeur avec 10 paramètres ?**

Le Builder permet de construire un objet de manière plus lisible et maintenable, surtout lorsque l'objet a de nombreux paramètres. Il évite les erreurs liées à l'ordre des paramètres et rend le code plus clair en utilisant des méthodes nommées pour chaque paramètre.

## Pattern 2 : FACTORY PATTERN
**Question : Quelle approche est plus flexible pour ajouter un nouveau type de notification ?**
 
L'approche Multiple Factory Classes est la plus flexible pour ajouter un nouveau type de notification car elle permet d'étendre le système de notifications sans modifier le code existant.
Le respect de l'Open/Closed Principle est donc meilleur.

## Pattern 3 : SINGLETON PATTERN
**Question : Pourquoi l'Enum Singleton est-il supérieur au Singleton classique ?**

L'Enum Singleton est meilleur car il permet de garantir nativement que l'instance de l'Enum Singleton est unique avec un implémentation plus simple et robuste.

## Pattern 3 : SINGLETON PATTERN
**Question : Pourquoi une deep copy est-elle importante ici ?**

La deep copy est importante pour que les objets du clone soit indépendants de l'original et donc que toutes modifications du clone n'affecte pas l'original.



