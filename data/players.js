{ players:
  [ { name: "Blue"
    , handOffset: { x: 12, y: 15 }
    , hitbox: { width: 18, height: 37 }
    , hitpoints: 100
    , speed: 100
    , sprites:
      [ { animation: "walk"
        , framerate: 10
        , offset: { x: 0, y: 0 }
        , sprite: "textures/players/blue-walk.png"
        , tileSize: { width: 18, height: 37 } }
      , { animation: "death"
        , framerate: 10
        , offset: { x: 0, y: 0 }
        , sprite: "textures/players/blue-death.png"
        , tileSize: { width: 50, height: 38 } } ]
    , startWeapon: "ak47"
    , startMoney: 100 }

  , { name: "Red"
    , handOffset: { width: 12, height: 15 }
    , hitbox: { width: 18, height: 37 }
    , hitpoints: 100
    , speed: 100
    , sprites:
      [ { animation: "walk"
        , framerate: 8
        , offset: { x: 0, y: 0 }
        , sprite: "textures/players/red-walk.png"
        , tileSize: { width: 18, height: 37 } }
      , { animation: "death"
        , framerate: 8
        , offset: { x: 0, y: 0 }
        , sprite: "textures/players/red-death.png"
        , tileSize: { width: 60, height: 70 } } ]
    , startWeapon: "scalpel"
    , startMoney: 100 } ] }
