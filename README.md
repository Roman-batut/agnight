# <center> Conception <center>

Le but serait de faire soit une carte interactive, soit une liste facilement lisible des endroits visitables avec l'AG Night de SBB CFF FFS, au départ d'une ville donnée (Lausanne) en partant le soir ou le matin.

Conditions possibles : 
- Nuit Blanche (départ le soir, arrivée le matin autorisée)
- Nombre de jours d'affilée \
  Faire les calculs pour les gares accessibles le jour d'avant

L’AG Night est valable à partir de 19h00.\
En semaine, l’abonnement est valable jusqu’à 5h00. \
Le week-end et les jours fériés, sa validité s’étend jusqu’à 7h00 heure d'arrivée.

### Etapes

- Trouver un moyen de récupérer les horaires de trains depuis une ville donnée vers toutes les autres villes de Suisse.
  - Accéder à l'API de SBB CFF FFS
  - Répertorier toutes les gares de Suisse
  - Permettre à l'utilisateur de choisir une gare de départ
  - Récupérer les horaires de trains depuis la gare de départ vers toutes les autres gares de Suisse \
  Couteux en ressources, chercher un moyen de limiter le nombre de requêtes
    - Sauvegarder les gares trops loin (arrivée après 5h00 ou 7h00)
    - Si une des gares est trop loin, ne pas la sauvegarder et ne pas chercher les horaires de trains depuis cette gare
  - Sauvegarder ces horaires de trains dans un fichier
- Afficher ces horaires de trains sur une carte ou une liste
