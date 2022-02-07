# Livraison 6

- [x] En tant que joueur je veux avoir accès à une Strategy plus performante.
- [x] En tant que joueur je veux avoir accès aux effets des cartes 21 à 30.
- [x] En tant qu'utilisateur je veux avoir accès à un jeu fiable et testé.
- [x] En tant qu'utilisateur je veux avoir le moins de dettes technique possible ( <1 jour ).

## Lancer en ligne de commande:

### Lancer le serveur :

`mvn exec:java -pl server -Dexec.mainClass=Server`

### Lancer les parties :

`mvn exec:java -pl game -Dexec.mainClass=App -Dexec.args="-nbGame 1000 -print 0"`

### Arguments :

- **-nbCardsPerYear N** : Number of cards per year (default: 3)
- **-nbGame N** : Number of games (0 < N, default: 1)
- **-nbPlayer N** : Number of player (between 1 < N < 5 excluded, default: 4)
- **-print 0|1** : Print details of games (default: 1(true))
- **-stats 0|1** : Collect statistics about games (default: 0 (false))
- **-years N** : Number of year per game (default: 3)