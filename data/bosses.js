{ bosses:
  [ { name: "bobby"
    , handOffset: { x: 0, y: 0 }
    , hitbox: { width: 22, height: 50 }
    , hitpoints: 750
    , speed: 100
    , sprites:
      [ { animation: "walk"
        , framerate: 8
        , offset: { x: 0, y: 0 }
        , sprite: "textures/bosses/bobby-walk.png"
        , tileSize: { width: 22, height: 50 } }
      , { animation: "death"
        , framerate: 8
        , offset: { x: 0, y: 0 }
        , sprite: "textures/bosses/bobby-death.png"
        , tileSize: { width: 200, height: 200 } } ]
    , locationX: -200
    , weapon: "ig"
    , fireLength: 2.0 }

  , { name: "Stefan"
    , handOffset: { x: 0, y: 0 }
    , hitbox: { width: 11, height: 30 }
    , hitBoints: 500
    , speed: 100
    , sprites:
      [ { animation: "walk"
        , framerate: 8
        , offset: { x: 0, y: 0 }
        , sprite: "textures/bosses/stefan-walk.png"
        , tileSize: { width: 11, height: 30 } }
      , { animation: "death"
        , framerate: 8
        , offset: { x: 0, y: 0 }
        , sprite: "textures/bosses/stefan-death.png"
        , tileSize: { width: 11, height: 30 } } ]
    , locationX: -100
    , weapon: "socialism"
    , fireLength: 2.0 }

  , { name: "datateknikern"
    , handOffset: { x: 0, y: 0 }
    , hitbox: { width: 28, height: 50 }
    , hitpoints: 200
    , speed: 100
    , sprites:
      [ { animation: "walk"
        , framerate: 8
        , offset: { x: 0, y: 0 }
        , sprite: "textures/bosses/wheelchairguy-walk.png"
        , tileSize: { width: 28, height: 50 } }
      , { animation: "death"
        , framerate: 8
        , offset: { x: 0, y: 0 }
        , sprite: "textures/bosses/wheelchairguy-death.png"
        , tileSize: { width: 100, height: 100 } } ]
    , locationX: -100
    , weapon: "mouse"
    , fireLength: 2.0 } ] }
