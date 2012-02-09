{ bosses:
  { "bobby":
    { fireLength: 2.0
    , handOffset: { x: 3, y: 21 }
    , locationX: 200
    , weapon: "ig"
    , unit:
      { hitbox: { width: 22, height: 50 }
      , hitpoints: 750
      , speed: 100
      , sprites:
        { "walk":
          { framerate: 8
          , offset: { x: 0, y: 0 }
          , sprite: "textures/bosses/bobby-walk.png"
          , tileSize: { width: 22, height: 48 } }
        , "death":
          { framerate: 8
          , offset: { x: -86, y: -50 }
          , sprite: "textures/bosses/bobby-death.png"
          , tileSize: { width: 187, height: 100 } }
        }
      }
    }

  , "stefan":
    { fireLength: 2.0
    , handOffset: { x: 0, y: 0 }
    , locationX: 100
    , weapon: "socialism"
    , unit:
      { hitbox: { width: 10, height: 29 }
      , hitpoints: 500
      , speed: 100
      , sprites:
        { "walk":
          { framerate: 8
          , offset: { x: 0, y: 0 }
          , sprite: "textures/bosses/stefan-walk.png"
          , tileSize: { width: 10, height: 29 } }
        , "death":
          { framerate: 8
          , offset: { x: 0, y: 0 }
          , sprite: "textures/bosses/stefan-death.png"
          , tileSize: { width: 10, height: 29 } }
        }
      }
    }

  , "datateknikern":
    { fireLength: 2.0
    , handOffset: { x: 0, y: 0 }
    , locationX: 100
    , weapon: "mouse"
    , unit:
      { hitbox: { width: 28, height: 50 }
      , hitpoints: 200
      , speed: 100
      , sprites:
        { "walk":
          { framerate: 8
          , offset: { x: 0, y: 0 }
          , sprite: "textures/bosses/wheelchairguy-walk.png"
          , tileSize: { width: 28, height: 50 } }
        , "death":
          { framerate: 8
          , offset: { x: 0, y: 0 }
          , sprite: "textures/bosses/wheelchairguy-death.png"
          , tileSize: { width: 100, height: 100 } }
        }
      }
    }
  }
}
