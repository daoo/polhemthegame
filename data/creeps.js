{ creeps:
  [ { name: "bracke"
    , hitbox: { width: 40, height: 50 }
    , hitpoints: 100
    , speed: 80
    , sprites:
      [ { animation: "walk"
        , framerate: 8
        , offset: { x: 0, y: 0 }
        , sprite: "textures/creeps/bracke-walk.png"
        , tileSize: { width: 40, height: 50 } }
      , { animation: "death"
        , framerate: 8
        , offset: { x: -56, y: -50 }
        , sprite: "textures/creeps/bracke-death.png"
        , tileSize: { width: 174, height: 101 } } ]
    , moneyGain: 100
    , damage: 10 }

  , { name: "ester"
    , hitbox: { width: 25, height: 44 }
    , hitpoints: 100
    , speed: 100 
    , sprites:
      [ { animation: "walk"
        , framerate: 8
        , offset: { x: 0, y: 0 }
        , sprite: "textures/creeps/ester-walk.png"
        , tileSize: { width: 25, height: 44 } }
      , { animation: "death"
        , framerate: 8
        , offset: { x: 0, y: -26 }
        , sprite: "textures/creeps/ester-death.png"
        , tileSize: { width: 342, height: 70 } } ]
    , moneyGain: 100
    , damage: 10 }

  , { name: "gtg"
    , hitbox: { width: 112, height: 35 }
    , hitpoints: 200
    , speed: 150
    , sprites:
      [ { animation: "walk"
        , framerate: 8    
        , offset: { x: 0, y: 0 }
        , sprite: "textures/creeps/gtg-walk.png"
        , tileSize: { width: 112, height: 35 } }
      , { animation: "death"
        , framerate: 8
        , offset: { x: -19, y: -70 }
        , sprite: "textures/creeps/gtg-death.png"
        , tileSize: { width: 131, height: 105 } } ]
    , moneyGain: 100
    , damage: 10 }

  , { name: "ytc"
    , hitbox: { width: 11, height: 24 }
    , hitpoints: 50
    , speed: 100
    , sprites:
      [ { animation: "walk"
        , framerate: 8
        , offset: { x: 0, y: 0 }
        , sprite: "textures/creeps/ytc-walk.png"
        , tileSize: { width: 11, height: 24 } }
      , { animation: "death"
        , framerate: 8
        , offset: { x: -13, y: -3 }
        , sprite: "textures/creeps/ytc-death.png"
        , tileSize: { width: 42, height: 26 } } ]
    , moneygain: 75
    , damage: 10 } ] }

