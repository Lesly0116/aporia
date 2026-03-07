
# Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
# Click nbfs://nbhost/SystemFileSystem/Templates/Other/Dockerfile to edit this template

# Étape 1 : Build de l'application avec Maven
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Définir le répertoire de travail
WORKDIR /app

# Copier les fichiers de configuration Maven d'abord (pour optimiser le cache)
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# Télécharger les dépendances (cette étape sera mise en cache si pom.xml n'a pas changé)
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline -B

# Copier le code source
COPY src src

# Compiler et packager l'application
RUN ./mvnw clean package -DskipTests

# Étape 2 : Créer l'image d'exécution
FROM eclipse-temurin:21-jre-jammy

# Définir le répertoire de travail
WORKDIR /app

# Créer un utilisateur non-root pour la sécurité
RUN addgroup --system --gid 1001 appuser && \
    adduser --system --uid 1001 --gid 1001 appuser

# Copier le jar depuis l'étape de build
COPY --from=build --chown=appuser:appuser /app/target/*.jar app.jar

# Copier le fichier .env.example (optionnel, pour référence)
COPY --chown=appuser:appuser .env.example .env.example

# Exposer le port sur lequel l'application tourne (par défaut 8080)
EXPOSE 8080

# Basculer vers l'utilisateur non-root
USER appuser

# Commande pour lancer l'application
# Les variables d'environnement seront injectées par Render
ENTRYPOINT ["java", "-jar", "app.jar"]
