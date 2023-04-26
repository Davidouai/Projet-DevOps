[![Java CI with Maven](https://github.com/HugolinBouhineau/Projet-DevOps/actions/workflows/maven.yml/badge.svg)](https://github.com/Davidouai/Projet-DevOps/actions/workflows/maven.yml)
[![Coverage](.github/badges/jacoco.svg)](https://github.com/HugolinBouhineau/Projet-DevOps/actions/workflows/maven.yml)

# Projet-DevOps
Java equivalent of python's Panda library test

Library features :
- Dataframe creation from object table or CSV file
- Dataframe display (first lines, last lines or all)
- Dataframe selection : filter dataframe by column labels or lines indexes. Possibility to filter lines by matching a value

Tools used : 
- Github actions for git workflow
- Github pages for website hosting
- Cicirello for code coverage badge generation https://github.com/cicirello/jacoco-badge-generator
- Maven for project building
- JUnit for unit testing
- Jacoco for code coverage

Devops features : 
- Automatically launch tests when pushing on main branch
- Automatically generate code coverage report when pushing on main branch
- Display results of tests and code coverage in README using badges
- Automatically deploy a website using github pages that displays the README, available here : https://hugolinbouhineau.github.io/Projet-DevOps/

PR proceeding : 
- Classic feature branch with code review required to merge

Feedback :
- Setup of devops tools is a bit hard at the beginning but you are happy when it finally works (e.g code coverage with badge)
- Commit history is a bit sketchy mostly because it was our first time using devops tools
