{ bosses:
  [ { name: "bobby"
    , handOffset: [ 0, 0 ]
    , hitBox: [ 22, 50 ]
    , hitPoints: 750
    , speed: 100
    , sprites:
      [ { animation: "walk"
        , framerate: 8
        , offset: [ 0, 0 ]
        , sprite: "textures/bosses/bobby-walk.png"
        , tileSize: [ 22, 50 ] }
      , { animation: "death"
        , framerate: 8
        , offset: [ 0, 0 ]
        , sprite: "textures/bosses/bobby-death.png"
        , tileSize: [ 200, 200 ] } ]
    , locationX: -200
    , weapon: "ig"
    , fireLength: 2.0 }

  , { name: "Stefan"
    , handOffset: [ 0, 0 ]
    , hitBox: [ 11, 30 ]
    , hitBoints: 500
    , speed: 100
    , sprites:
      [ { animation: "walk"
        , framerate: 8
        , offset: [ 0, 0 ]
        , sprite: "textures/bosses/stefan-walk.png"
        , tileSize: [ 11, 30 ] }
      , { animation: "death"
        , framerate: 8
        , offset: [ 0, 0 ]
        , sprite: "textures/bosses/stefan-death.png"
        , tileSize: [ 11, 30 ] } ]
    , locationX: -100
    , weapon: "socialism"
    , fireLength: 2.0 }

  , { name: "Datateknikern"
    , hitBox: [ 28, 50 ]
    , hitPoints: 200
    , speed: 100
    , sprites:
      [ { animation: "walk"
        , framerate: 8
        , offset: [ 0, 0 ]
        , sprite: "textures/bosses/wheelchairguy-walk.png"
        , tileSize: [ 28, 50 ] }
      , { animation: "death"
        , framerate: 8
        , offset: [ 0, 0 ]
        , sprite: "textures/bosses/wheelchairguy-death.png"
        , tileSize: [ 100, 100 ] } ]
    , locationX: -100
    , weapon: "mouse"
    , fireLength: 2.0 } ] }
