#language: fr
#encoding: utf-8
Fonctionnalité: Changement d'année

  Contexte:
    Etant donné un "joueur" veux pouvoir changer d'année


  Scénario: Passage de l'année 1 à 2
    Quand le curseur est à la case 11
    Et dans l'année 1
    Et lance le dé qui tombe sur une face a 1 points pour avancer dans L'année
    Alors il passe à l'année 2

  Scénario: Passage de l'année 2 à 3
    Quand le curseur est à la case 22
    Et dans l'année 2
    Et lance le dé qui tombe sur une face a 2 points pour avancer dans L'année
    Alors il passe à l'année 3

  Scénario: Pas de changement d'année
    Quand le curseur est à la case 10
    Et dans l'année 1
    Et lance le dé qui tombe sur une face a 1 points pour avancer dans L'année
    Alors il passe à l'année 1

  Scénario: Pas de changement d'année
    Quand le curseur est à la case 10
    Et dans l'année 1
    Et lance le dé qui tombe sur une face a 1 points pour avancer dans L'année
    Alors il passe à l'année 1

  Scénario: Pas de changement d'année
    Quand le curseur est à la case 21
    Et dans l'année 3
    Et lance le dé qui tombe sur une face a 3 points pour avancer dans L'année
    Alors il passe à l'année 3