#language: fr
#encoding: utf-8
Fonctionnalité: Gagner des points de prestige

  Contexte:
    Etant donné un "joueur"


  Scénario: Gagne 10 points de prestige
    Quand le joueur à 0 points de prestige
    Et a une carte qui rapporte 10 points de prestige sur le plateau
    Alors il gagne à 10 points de prestige

  Scénario: Gagne 10 points de prestige
    Quand le joueur à 10 points de prestige
    Et a une carte qui rapporte 10 points de prestige sur le plateau
    Alors il gagne à 20 points de prestige

  Scénario: Gagne 0 points de prestige
    Quand le joueur à 0 points de prestige
    Et a une carte qui rapporte 0 points de prestige sur le plateau
    Alors il gagne à 0 points de prestige

  Scénario: Gagne 15 points de prestige
    Quand le joueur à 0 points de prestige
    Et a une carte qui rapporte 10 points de prestige sur le plateau
    Et a une deuxieme carte qui rapporte 5 points de prestige sur le plateau
    Alors il gagne à 15 points de prestige
