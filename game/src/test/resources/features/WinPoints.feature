#language: fr
#encoding: utf-8
Fonctionnalité: Gagner des cristaux

  Contexte:
    Etant donné un "joueur"


  Scénario: Gagne 3 cristaux
    Quand le joueur à 0 cristaux
    Et lance le dé qui tombe sur une face a 3 cristaux
    Alors il gagne 3 cristaux

  Scénario: Gagne 0 cristaux
    Quand le joueur à 0 cristaux
    Et lance le dé qui tombe sur une face a 0 cristaux
    Alors il gagne 0 cristaux

  Scénario: Gagne 6 critaux
    Quand le joueur à 2 cristaux
    Et lance le dé qui tombe sur une face a 6 cristaux
    Alors il gagne 6 cristaux