{ "level": "Defend What Matters Most!"
, "constraints": [ 0, 100, 0, 0 ]
, "background": "textures/levels/polhem.png"
, "states": [ "beginning", "creeps", "boss transition", "boss", "finished" ]
, "textStates":
  [ { "state": "beginning"
    , "type": "text"
    , "text": "textures/text/defendwhatmattersmost.png"
    , "duration": 2.5 }
  , { "state": "boss transition"
    , "type": "text"
    , "text": "textures/text/whosentanetsend.png"
    , "duration": 2.5 }
  , { "state": "finished"
    , "text": "textures/text/levelcomplete.png"
    , "type": "text"
    , "duration": 2.5 } ]
, "creepStates":
  [ { "state": "creeps"
    , "type": "creeps"
    , "creeps": 
      [ { "creep": "ester", "spawnTime": 1.0 }
      , { "creep": "bracke", "spawnTime": 2.0 }
      , { "creep": "ester", "spawnTime": 3.0 }
      , { "creep": "bracke", "spawnTime": 4.0 }
      , { "creep": "ytc", "spawnTime": 5.0 }
      , { "creep": "ester", "spawnTime": 9.0 }
      , { "creep": "bracke", "spawnTime": 10.0 }
      , { "creep": "gtg", "spawnTime": 11.0 }
      , { "creep": "ester", "spawnTime": 13.0 }
      , { "creep": "bracke", "spawnTime": 14.0 }
      , { "creep": "ytc", "spawnTime": 15.0 }
      , { "creep": "ester", "spawnTime": 16.0 }
      , { "creep": "bracke", "spawnTime": 19.0 }
      , { "creep": "ester", "spawnTime": 20.0 }
      , { "creep": "ytc", "spawnTime": 21.0 }
      , { "creep": "bracke", "spawnTime": 23.0 }
      , { "creep": "ester", "spawnTime": 24.0 }
      , { "creep": "bracke", "spawnTime": 26.0 }
      , { "creep": "ester", "spawnTime": 27.0 }
      , { "creep": "gtg", "spawnTime": 28.0 }
      , { "creep": "bracke", "spawnTime": 29.0 } ] } ]
, "bossStates":
  [ { "state": "boss"
    , "type": "boss"
    , "boss": "datateknikern" } ] }

