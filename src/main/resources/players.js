{ players:
  { "blue":
    { handOffset: { x: 12, y: 15 }
    , startMoney: 0
    , startWeapon: "scalpel"
    , unit:
      { hitbox: { width: 18, height: 37 }
      , orientation: LEFT
      , hitpoints: 100
      , speed: 125
      , sprites:
        { "walk":
          { framerate: 10
          , offset: { x: 0, y: 0 }
          , sprite: "textures/players/blue-walk.png"
          , tileSize: { width: 18, height: 37 } }
        , "death":
          { framerate: 8
          , offset: { x: 0, y: -1 }
          , sprite: "textures/players/blue-death.png"
          , tileSize: { width: 50, height: 38 } }
        }
      }
    }

  , "red":
    { handOffset: { x: 12, y: 15 }
    , startMoney: 0
    , startWeapon: "scalpel"
    , unit:
      { hitbox: { width: 18, height: 37 }
      , hitpoints: 100
      , speed: 125
      , sprites:
        { "walk":
          { framerate: 8
          , offset: { x: 0, y: 0 }
          , sprite: "textures/players/red-walk.png"
          , tileSize: { width: 18, height: 37 } }
        , "death":
          { framerate: 8
          , offset: { x: 0, y: -30 }
          , sprite: "textures/players/red-death.png"
          , tileSize: { width: 60, height: 67 } }
        }
      }
    }
  }
}
