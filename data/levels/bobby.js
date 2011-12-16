{ level: "Bobby"
, states: [ "beginning", "creeps", "boss transition", "boss", "finished" ]
, textStates:
  [ { state: "beginning"
    , type: "text"
    , text: "textures/text/laptoploosers.png"
    , duration: 2.5 }
  , { state: "boss transition"
    , type: "text"
    , text: "textures/text/bobby.png"
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
      , { creep: "bracke", spawnTime: 1.50 }
      , { creep: "ester", spawnTime: 2.0 }
      , { creep: "bracke", spawnTime: 2.0 }
      , { creep: "ytc", spawnTime: 2.50 }
      , { creep: "ester", spawnTime: 3.0 }
      , { creep: "bracke", spawnTime: 4.0 }
      , { creep: "gtg", spawnTime: 4.0 }
      , { creep: "ester", spawnTime: 5.0 }
      , { creep: "bracke", spawnTime: 6.0 }
      , { creep: "ytc", spawnTime: 7.0 }
      , { creep: "ester", spawnTime: 8.0 }
      , { creep: "bracke", spawnTime: 9.0 }
      , { creep: "ester", spawnTime: 9.0 }
      , { creep: "ytc", spawnTime: 10.0 }
      , { creep: "bracke", spawnTime: 10.50 }
      , { creep: "ester", spawnTime: 11.0 }
      , { creep: "bracke", spawnTime: 12.0 }
      , { creep: "ester", spawnTime: 12.0 }
      , { creep: "gtg", spawnTime: 13.0 }
      , { creep: "bracke", spawnTime: 14.0 } ] } ]
, bossStates:
  [ { state: "boss"
    , type: "boss"
    , boss: "bobby" } ] }

