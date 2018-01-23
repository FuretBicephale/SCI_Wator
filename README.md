# SCI_Wator
##### Nicolas CACHERA et Samuel GRANDSIR

### Wator

Cette application a pour but de simuler un environnement 2D discret constitué de poissons (thons et requins).

Les poissons sont des agents qui se déplacent dans huit directions, de la même façon que les billes à la différence que les poissons choisissent, à chaque tour, la case où ils se déplacent. En effet, à chaque tour, le poisson analyse son environnement (Son voisinage de Moore d'ordre 1) et repère les cases vides. Il se déplacera alors vers une de ses cases, choisie aléatoirement.

Les poissons peuvent aussi se reproduire. En effet, ils possèdent une fréquence de reproduction de façon à ce qu'ils donnent naissance tous les X tours si il y a au moins une case vide autour d'eux. A chaque début de tour, si les conditions sont réunies (Au moins X tours depuis la dernière naissance et au moins une case vide dans son voisinage), le poisson créera un autre poisson de la même espece dans une case voisine vide choisie aléatoirement parmis les cases vides avant de se déplacer. Sinon, il se déplace sans donner naissance à un poisson et retentera le tour suivant.

Les requins sont des poissons ayant la particularité de manger des poissons de type thon. Si un requin ne mange pas pendant un nombre pré-défini de tours, il meurt. Il est donc nécessaire que chaque requin mange dès que possible. Pour ce faire, le requin va analyser son environnement pour trouver les thons voisins. Si il en trouve, il mange un de ces poissons, choisi aléatoirement, et se déplace sur sa case. Sinon, il se déplace et retentera la prochaine fois ou meurt si il n'a pas mangé depuis trop longtemps.

Dans la vue, les thons sont représentés par des cercles gris et les requins par des cercles bleus.

A la fin de la simulation, deux graphes sont générés. Le premier (population), constitué de deux courbes, donne le nombre de thons et de requins en fonction du tour. Le second (ratio) donne le ratio entre les thons et les requins. Afin d'afficher ces graphes, il est possible d'utiliser les deux lignes de commandes suivantes dans le dossier SCI_WATOR :
* gnuplot population
* gnuplot ratio

##### Pour compiler (dossier src) :
* javac ./\*/\*.java

##### Pour créer le jar (dossier src) :
* jar cvfe Wator.jar wator.Simulation ./\*/\*.class

##### Pour exécuter (dossier src) :
* java -jar Wator.jar \<nb\_cases\_largueur\> \<nb\_cases\_hauteur\> \<taille\_cases\> \<toric (true/false)\> \<latence en ms\> \<nb\_thons\> \<nb\_requins\> [\<nb\_tours\>]

##### Exemple :
* java -jar Wator.jar 50 50 10 true 100 500 100 : Environnement torique de 50 sur 50 cases de 10 pixels de côté contenant 500 thons et 100 requins. L'environnement fait 500 pixels sur 500 pixels. La simulation dure indéfiniment et attends 100 ms entre chaque tour.
