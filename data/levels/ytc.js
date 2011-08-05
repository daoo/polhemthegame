{ level: "YTC"
, constraints: [ 0, 100, 0, 0 ]
, background: "textures/levels/polhem.png"
, states: [ "beginning", "creeps", "boss transition", "boss", "finished" ]
, textStates:
  [ { state: "beginning"
    , type: "text"
    , text: "textures/text/ytc.png"
    , duration: 2.5 }
  , { state: "boss transition"
    , type: "text"
    , text: "textures/text/donteventrytogetbackin.png"
    , duration: 2.5 }
  , { state: "finished"
    , text: "textures/text/levelcomplete.png"
    , type: "text"
    , duration: 2.5 } ]
, creepStates:
  [ { state: "creeps"
    , type: "creeps"
    , creeps: 
      [ { creep: "ester", spawnTime: 1.0 }
      , { creep: "ytc", spawnTime: 1.50 }
      , { creep: "ytc", spawnTime: 1.60 }
      , { creep: "ytc", spawnTime: 1.70 }
      , { creep: "ytc", spawnTime: 1.80 }
      , { creep: "ester", spawnTime: 2.0 }
      , { creep: "ytc", spawnTime: 3.80 }
      , { creep: "ytc", spawnTime: 3.90 }
      , { creep: "ytc", spawnTime: 4.0 }
      , { creep: "ytc", spawnTime: 4.0 }
      , { creep: "ytc", spawnTime: 4.50 }
      , { creep: "ytc", spawnTime: 4.60 }
      , { creep: "ytc", spawnTime: 4.70 }
      , { creep: "ytc", spawnTime: 4.80 }
      , { creep: "ytc", spawnTime: 4.90 }
      , { creep: "ytc", spawnTime: 5.0 }
      , { creep: "ytc", spawnTime: 5.10 }
      , { creep: "ytc", spawnTime: 5.10 }
      , { creep: "ester", spawnTime: 6.0 }
      , { creep: "ester", spawnTime: 7.0 }
      , { creep: "ester", spawnTime: 10.0 }
      , { creep: "ytc", spawnTime: 15.0 }
      , { creep: "ytc", spawnTime: 15.0 }
      , { creep: "ytc", spawnTime: 15.0 }
      , { creep: "ytc", spawnTime: 15.0 }
      , { creep: "ytc", spawnTime: 15.0 }
      , { creep: "ester", spawnTime: 15.30 }
      , { creep: "ytc", spawnTime: 15.50 }
      , { creep: "ytc", spawnTime: 15.50 }
      , { creep: "ytc", spawnTime: 15.50 }
      , { creep: "ester", spawnTime: 15.60 }
      , { creep: "ytc", spawnTime: 16.0 }
      , { creep: "ytc", spawnTime: 17.0 }
      , { creep: "ester", spawnTime: 18.50 }
      , { creep: "ytc", spawnTime: 18.0 }
      , { creep: "ytc", spawnTime: 19.0 }
      , { creep: "ytc", spawnTime: 20.0 }
      , { creep: "ester", spawnTime: 20.0 }
      , { creep: "ester", spawnTime: 20.0 }
      , { creep: "ytc", spawnTime: 21.0 }
      , { creep: "ytc", spawnTime: 21.10 }
      , { creep: "ytc", spawnTime: 21.20 }
      , { creep: "ytc", spawnTime: 21.30 }
      , { creep: "ytc", spawnTime: 21.40 }
      , { creep: "ytc", spawnTime: 21.50 }
      , { creep: "ytc", spawnTime: 21.60 }
      , { creep: "ytc", spawnTime: 23.0 }
      , { creep: "ytc", spawnTime: 23.0 }
      , { creep: "ytc", spawnTime: 23.0 }
      , { creep: "ytc", spawnTime: 23.0 }
      , { creep: "ytc", spawnTime: 23.0 }
      , { creep: "ytc", spawnTime: 23.0 }
      , { creep: "ester", spawnTime: 23.0 }
      , { creep: "ester", spawnTime: 23.0 } ] } ]
, bossStates:
  [ { state: "boss"
    , type: "boss"
    , boss: "stefan" } ] }

