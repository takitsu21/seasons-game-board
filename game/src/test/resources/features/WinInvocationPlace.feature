#language: fr
#encoding: utf-8
Fonctionnalité: Gagner une place d'invocation

  Contexte:
    Etant donné un "joueur"

  Scénario: Gagne 1 place d'invocation
    Quand le joueur à 0 place d'invocation
    Et lance le dé qui tombe sur une face a "étoile"
    Alors il gagne 1 place d'invocation

  Scénario: Gagne 1 place d'invocation
    Quand le joueur à 5 place d'invocation
    Et lance le dé qui tombe sur une face a "étoile"
    Alors il gagne 1 place d'invocation

  Scénario: Gagne 0 place d'invocation
    Quand le joueur à 1 place d'invocation
    Et lance le dé qui tombe sur une face a "pas étoile"
    Alors il gagne 0 place d'invocation

  Scénario: Gagne 0 place d'invocation
    Quand le joueur à 15 place d'invocation
    Et lance le dé qui tombe sur une face a "étoile"
    Alors il gagne 0 place d'invocation