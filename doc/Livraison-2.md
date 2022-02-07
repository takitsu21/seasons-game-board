# Livraison 2

- [x] En tant qu'énergie je veux avoir un type.
- [x] En tant que joueur je veux avoir la possibilité de cristalliser grâce au dé.
- [x] En tant que joueur je veux avoir la possibilité de piocher une carte.
- [x] En tant que joueur je veux changer de saison grâce à l'avancée du pion.
- [x] En tant que joueur je veux changer d'année grâce à l'avancée du pion.
- [x] En tant qu’utilisateur je veux avoir accès à des statistiques grâce à un serveur.
- [x] En tant qu'utilisateur je veux accéder à l'étude des patterns.

## Étude des patterns:

### GRASP:

### Information expert:

Nous avons assigné autant que possible les responsabilités des classes en fonctions des informations qu'elles
contenaient. Par exemple la classe Deck contenant toutes les cartes de la pioche, c'est à elle que revient la
responsabilité de designer quelle carte un joueur obtient lorsqu'il doit piocher.

### Créateur:

Notre classe Board peut être considérée comme une classe créatrice. En effet elle permet notamment d'initialiser la
pioche, les joueurs, et une grande partie du jeu. Player peut aussi être citée, puisqu'elle instancie son inventaire qu'
elle appellera régulièrement pour récuperer différentes informations.

### Polymorphisme:

Un exemple de polymorphisme peut être notre gestion des cartes. Bien qu'une seule carte existe pour le moment,
l'interface Card et la classe abstraite AbstractCard nous permet de réunir un maximum de propriétés communes entre les
cartes afin d'en tirer le plus gros avantage possible.

### Variation protégée:

De part leur implémentation mettant en avant un polymorphisme prononcé, les cartes permettent également de créer des
variantes sans risques majeurs.

### Forte cohésion:

La classe Deck ne sert qu'à tirer une carte lorsque le joueur le décide et le peut. Sa simplicité met en avant une forte
cohésion.

**Globalement pour cette livraison nous avons voulu mieux respecter les objectifs de GRASP en séparant les rôles des
objets de sorte à définir les responsabilités de façon plus efficace. Cependant, il une partie du jeu reste trop
globale, comme les gameloop qui servent de moteurs au jeu.**

### SOLID:

### SRP:

Comme dit plus haut, notre classe Deck suit le principe SRP: piocher une carte et rien d'autre.

**Pour ce qui est des principes OCP, LSP, ISP et DIP, nous ne pensons pas être en mesure d'affirmés qu'ils aient été
correctement appliqués.**
