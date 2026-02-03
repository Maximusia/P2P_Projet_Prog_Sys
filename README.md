# P2P_Projet_Prog_Sys

structure du projet :
project/
 ├── shared/
 │    └── test.txt
 ├── downloads/
 ├── src/
 │    ├── chunk/
 │    │    └── Chunk.java
 │    ├── file/
 │    │    └── FileManager.java
 │    ├── protocol/
 │    │    └── ProtocolHandler.java
 │    ├── util/
 │    │    └── HashUtils.java
 │    └── MainDemo.java

_ Chunk.java: un morceau de fichier
_ HashUtils.java : le hash permet de verifier qu'un chunk n'est pas corrompu
_ FileManager.java : Il decoupe et reconstruit
_ ProtocolHandler.java : c'est ici qu'on verifie l'integrite du donnees recues
_ MainDemo.java: test du projet