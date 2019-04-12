# Gizmo - from noobs for professionals


![Gizmo](https://www.mopop.org/media/2258/cla_artifacts_gizmo-0633_1200x630.jpg?anchor=center&mode=crop&width=1140&height=550&rnd=131826407410000000)

*Help me*

* This super program will allow you create project-name-based boards to help you control your workflow. This project is only for educational purposes only.

<hr>

### TODO
* Implement basic class sceletons
* Possibility to create boards, lists and cards
* Possibility to manage boards, lists and cards
* Assign roles to users connected to boards
* Moving cards between lists
* Change board background for free!
* Change cards priority
* If God will help us we will implement comments system foreach cards
<hr>

### Schedule
#### 13.04.2019
User will be able to:
* create account.
* create own board and select current.
#### 27.04.2019
User will be able to:
* create lists and cards
* assign roles to users connected to boards
* change card priority
* change board background for free!
#### 11.05.2019
User will be able to:
* move cards between lists
* edit components
* comment cards

## Installation

### Requirements
- java 8
- gradle
- mysql database

### Instructions
1. Copy `src/main/resources/application-dev.properties.dist` to `src/main/resources/application-dev.properties`
2. Fill or change required configuration in `src/main/resources/application-dev.properties`
3. Start application server run `./gradlew bootRun` 

You can now go to [http://localhost:8080/](http://localhost:8080/). 

To gracefully shutdown server run `curl -X POST localhost:8080/actuator/shutdown`.  


*(image source: https://www.mopop.org/media/2258/cla_artifacts_gizmo-0633_1200x630.jpg?anchor=center&mode=crop&width=1140&height=550&rnd=131826407410000000)*
