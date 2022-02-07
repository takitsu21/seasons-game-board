#language: fr
#encoding: utf-8
Fonctionnalité: Evolution de la main

  Contexte:
    Etant donné un inventaire je veux que mes cartes en main évolue en fonction des années.

  Scénario: Initialisation
    Quand la main est initialisé
    Alors l'inventaire à 3 cartes en main

  Scénario: Passage de 3 à 6 cartes
    Quand la main est initialisé
    Et le curseur est dans l'année 1
    Et le curseur passe à l'année 2
    Alors l'inventaire passe de 3 à 6 cartes en main

  Scénario: Passage de 6 à 9 cartes
    Quand la main est initialisé
    Et le curseur est dans l'année 2
    Et le curseur passe à l'année 3
    Alors l'inventaire passe de 6 à 9 cartes en main

  Scénario: Passage de 3 à 9 cartes
    Quand la main est initialisé
    Et le curseur est dans l'année 1
    Et le curseur passe à l'année 3
    Alors l'inventaire passe de 3 à 9 cartes en main


