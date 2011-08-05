{ players:
  [ { name: "Blue"
    , handOffset: [ 12, 15 ]
    , hitBox: [ 18, 37 ]
    , hitPoints: 100
    , speed: 100
    , sprites:
      [ { animation: "walk"
        , framerate: 10
        , offset: [ 0, 0 ]
        , sprite: "textures/players/blue-walk.png"
        , tileSize: [ 18, 37 ] }
      , { animation: "death"
        , framerate: 10
        , offset: [ 0, 0 ]
        , sprite: "textures/players/blue-death.png"
        , tileSize: [ 50, 38 ] } ]
    , startWeapon: "pistol"
    , startMoney: 100 }

  , { name: "Red"
    , handOffset: [ 12, 15 ]
    , hitBox: [ 18, 37 ]
    , hitPoints: 100
    , speed: 100
    , sprites:
      [ { animation: "walk"
        , framerate: 8
        , offset: [ 0, 0 ]
        , sprite: "textures/players/red-walk.png"
        , tileSize: [ 18, 37 ] }
      , { animation: "death"
        , framerate: 8
        , offset: [ 0, 0 ]
        , sprite: "textures/players/red-death.png"
        , tileSize: [ 60, 70 ] } ]
    , startWeapon: "scalpel"
    , startMoney: 100 } ] }
