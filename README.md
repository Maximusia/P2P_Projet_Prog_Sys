# P2P_Projet_Prog_Sys

# Projet P2P : Développeur Fichiers / Protocole

## 1. Présentation générale

Ce projet s’inscrit dans la réalisation d’un système de partage de fichiers en **pair-à-pair (P2P)**, inspiré du fonctionnement de BitTorrent.

Le **rôle 2** est responsable de :

* la gestion des fichiers partagés
* le découpage des fichiers en morceaux (*chunks*)
* la définition du protocole d’échange des données
* la vérification de l’intégrité des données
* la reconstruction des fichiers téléchargés

Ce module est indépendant du réseau : il ne gère ni sockets, ni connexions TCP.

---

## 2. Objectifs 

Les objectifs principaux sont :

* Découper un fichier en plusieurs chunks de taille fixe
* Associer un hash à chaque chunk pour garantir l’intégrité
* Simuler l’échange de chunks via un protocole P2P simple
* Vérifier les chunks reçus à l’aide du hash
* Reconstruire le fichier final à partir des chunks valides

---

## 3. Organisation du projet

```
project/
 ├── shared/               # Fichiers à partager
 │    └── test.txt
 ├── downloads/            # Fichiers reconstruits
 │    └── test_rebuilt.txt
 ├── src/
 │    ├── chunk/
 │    │    └── Chunk.java
 │    ├── file/
 │    │    └── FileManager.java
 │    ├── protocol/
 │    │    └── ProtocolHandler.java
 │    ├── util/
 │    │    └── HashUtils.java
 │    └── MainDemo.java    # Programme de démonstration
```

---

## 4. Description des classes

### 4.1 Chunk.java

Représente un morceau de fichier.

Attributs :

* `fileId` : identifiant du fichier
* `index` : position du chunk dans le fichier
* `data` : données binaires du chunk
* `hash` : hash SHA-256 du chunk

Cette classe permet de manipuler un morceau de fichier de manière structurée.

---

### 4.2 HashUtils.java

Classe utilitaire chargée de calculer le hash SHA-256 des données.

Rôle :

* Garantir l’intégrité des chunks
* Détecter toute modification ou corruption des données

---

### 4.3 FileManager.java

Classe centrale.

Fonctionnalités principales :

* Découper un fichier en chunks (`splitFile`)
* Reconstruire un fichier à partir des chunks (`rebuildFile`)

Le découpage se fait à l’aide d’une constante `CHUNK_SIZE`, définissant la taille maximale d’un chunk.

---

### 4.4 ProtocolHandler.java

Implémente le protocole P2P simplifié.

Fonctions :

* Construire un message `DATA` à partir d’un chunk
* Analyser un message reçu
* Vérifier l’intégrité du chunk reçu grâce au hash

Le protocole est textuel et utilise l’encodage Base64 pour transporter les données binaires.

---

### 4.5 MainDemo.java

Programme principal de démonstration.

Il permet de :

1. Découper un fichier en chunks
2. Simuler l’envoi et la réception des chunks
3. Vérifier l’intégrité des données reçues
4. Reconstruire le fichier final
5. Comparer le fichier reconstruit avec l’original

---

## 5. Démonstration du fonctionnement

### Étapes de la démonstration :

1. Placer un fichier `test.txt` dans le dossier `shared/`
2. Lancer la classe `MainDemo`
3. Observer les messages de la console
4. Vérifier que le fichier `test_rebuilt.txt` est créé dans `downloads/`
5. Vérifier que les deux fichiers sont identiques

---

## 6. Tests réalisés

* Test de découpage et reconstruction du fichier
* Test d’égalité entre fichier original et fichier reconstruit
* Test de détection d’un chunk corrompu (modification volontaire des données)
* Test de variation de la taille des chunks (`CHUNK_SIZE`)

---

## 7. Conclusion

Cette partie assure une gestion fiable des fichiers dans le système P2P.

Grâce au découpage en chunks et à l’utilisation de hash cryptographiques, il garantit :

* l’intégrité des données
* la reconstruction correcte des fichiers
* une base solide pour l’échange de fichiers en pair-à-pair

Ce module peut être connecté au module réseau  pour une implémentation complète du système P2P.
