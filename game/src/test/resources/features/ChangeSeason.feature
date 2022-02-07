#language: fr
#encoding: utf-8
Fonctionnalité: Changement de saison

  Contexte:
    Etant donné un "joueur" veux pouvoir changer de saison


  Scénario: Passage de la saison hiver à printemps
    Quand le curseur est à la case 2 de saison "hiver"
    Et lance le dé qui tombe sur une face a 1 points pour avancer dans la saison
    Alors il passe à la saison "printemps"

  Scénario: Passage de la saison printemps à été
    Quand le curseur est à la case 0 de saison "printemps"
    Et lance le dé qui tombe sur une face a 3 points pour avancer dans la saison
    Alors il passe à la saison "été"

  Scénario: Passage de la saison été à automne
    Quand le curseur est à la case 3 de saison "été"
    Et lance le dé qui tombe sur une face a 2 points pour avancer dans la saison
    Alors il passe à la saison "automne"

  Scénario: Passage de la saison automne à hiver
    Quand le curseur est à la case 2 de saison "automne"
    Et lance le dé qui tombe sur une face a 1 points pour avancer dans la saison
    Alors il passe à la saison "hiver"


  Scénario: Pas de changement de saison
    Quand le curseur est à la case 1 de saison "automne"
    Et lance le dé qui tombe sur une face a 1 points pour avancer dans la saison
    Alors il passe à la saison "automne"