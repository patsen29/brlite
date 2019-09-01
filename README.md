# Baseball Reference Lite

This is a simple app to quickly look up team rosters, or player stats.

### [Check out the current build](https://brlite.herokuapp.com)

---

Requirements to build:
- Java 8
- Maven

Steps to build:
- `mvn package`
- `java -jar target/brlite-1.0-SNAPSHOT-jar-with-dependencies.jar`
- Open http://localhost:4567 in the browser.
- Or, set an environment variable `PORT` to select a custom port.
- This build will autodeploy at https://brlite.herokuapp.com

---

This app relies on the MLB API, and its use acknowledges agreement to the terms posted at http://gdx.mlb.com/components/copyright.txt