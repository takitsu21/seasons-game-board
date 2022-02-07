# Règles conservées

- 30 premières cartes
- Niveau intermédiaire

# IA garantie

- IA par composition :
    - Se concentre sur des combos de cartes
    - Cristallise quoi qu’il arrive si elle peut
    - Invoquer le plus de cartes possible
    - Privilégie le gain de points en fin de partie grâce aux cartes
    - Joue avec le temps: fait avancer +- rapidement les saisons
    - Infliger le plus de malus possibles aux autres joueurs

# IA ambitieuses

- Monte Carlo
- Apprentissage:
    - Sur les méthodes de composition
    - Sur la méthode monte-carlo

# Journal de bord

## Lundi 29/11/2021 - Mercredi 1/12/2021

### Fix
- Heaume de Ragfield
- Boucle infinie de la stratégie combo
- Stratégie ChooseCardToActivateMalus
- Stratégie ChooseCardToActivateTime

### Modification
- Généralisation des stratégies (autant que possible) avec un abstractStrategy

### Ajout
- Création d’IA composées
- Ajout de contextes
- Ajout de quelques stratégies (on play)
- Ajout de fonctions dans la façade
- Ajout stratégie UseDéDeLaMalice


## Lundi 15/11/2021 - Dimanche 21/11/2021

### Fix
- Sample card ne recyclait pas 
- Ajouts de carte dans la défausse manquants

### Modification
- La signature de chooseCardNariaLaProphetesse
- Le constructeur de chooseCardsToActivatePrefCrystallize
- Ajout du fait que les stratégies puisse renvoyer null et passer à la suivante

### Ajout 
- Classes utilitaires:
  - ChooseFromPrefList permettant de faire des choix en fonction des cartes préférées de la stratégie employée
  - ChooseUtil généralise des méthodes pour différentes stratégies
  - Des listes de cartes en fonction du domaine de leur effet (si elles permettent d’avoir plus de cristaux, infliger des malus aux adversaires,...)
  - AbstractStrategy / Interface Strategy

- Stratégies:
  - PrefActivate: Totalité de la stratégie implémentée
  - PrefCardPoints: Totalité de la stratégie implémentée
  - PrefCrystallize: Totalité de la stratégie implémentée
  - Malus: Totalité de la stratégie implémentée
  - PrefInvoc: ajout des stratégies manquantes
  - Time: moitié des stratégie implémenté
  - Testes unitaire: Malus, prefCristallize, PrefActivate, prefCardPoints, PrefInvoc, Time


## Lundi 08/11/2021 - Dimanche 14/11/2021

### Fix

- Corne du Mendiant qui n’était jamais appelée
- Calice Divin qui ne défaussait pas les cartes
- Balance d’Ishtar qui ne changeait pas son état correctement
- Récupération des cartes activables sur un Board qui renvoyait toutes les cartes
- hasEnoughEnergy qui renvoyait un résultat ne prenant pas en compte la modification du prix d’invocation pas Main de la
  Fortune

### Ajout

- Stratégies :
    - Invocation: Totalité de la stratégie implémentée
    - Card Points: majorité de la stratégie implémentée
    - Malus: majorité de la stratégie implémentée
    - Combos: majorité de la stratégie est implémentée
    - Tests unitaires: card points, invocation, combos
- Renderers pour mieux suivre l'évolution de la partie


## Vendredi 05/11/2021 - Dimanche 07/11/2021

- Définition des règles que nous allons utiliser pour le développement des IA
- Choix des différents types d’IA (cf: IA garantie, IA ambitieuse).
- Création des user strories pour l’IA garantie.
- Création des issues et du kanban pour l’environnement de travail en rapport avec les IA.

### Fix

- Bottes temporelles
- Main de la fortune
- updateHand
