# Baseball Reference Lite

This is a simple app to quickly look up team rosters, or player stats.

### [Check out the current build](https://brlite.herokuapp.com)

---

Requirements to build:
- Java 8
- Maven

Steps to build:
- Build from root directory (where this README.md resides):
- `mvn package`
- `java -jar target/brlite-1.0-SNAPSHOT-jar-with-dependencies.jar`
- Open http://localhost:4567 in the browser.
- Or, set an environment variable `PORT` to select a custom port.
- This build will autodeploy at https://brlite.herokuapp.com

---

Potential improvements:
- Caching calls, to avoid unneeded API calls
- Throttling API calls to prevent abuse
- Keep flat file database for historical stats
- Add standings to the league page, to be a bit more useful
- Fetch 40-man rosters, to have more players available
- Player search, to find players without links
- Add secondary info to player pages, such as sprint speed, pitcher repertoire, scouting reports, 
    defensive/WAR metrics, and/or projections
- Add tabs on the frontend, to help paginate stats
- Add minor league stats

Other undocumented features:
- In addition to the 30 MLB teams, the /team/{teamId} endpoint also allows for any numeric teamId, which can be used for minor league rosters.
- The /player/{playerId} endpoint works for all MLBAM ids, including minor leaguers. But for now, only major league stats are displayed.
  

---

This app relies on the MLB API, and its use acknowledges agreement to the terms posted at http://gdx.mlb.com/components/copyright.txt