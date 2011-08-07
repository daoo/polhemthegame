{ weapons:
  [ { name: "scalpel" 
    , automatic: false
    , clipSize: -1
    , muzzleOffset: { x: 4, y: 3 }
    , reloadTime: 0
    , rpm: 120
    , sprite:
      { animation: "fire"
      , framerate: 8
      , offset: { x: 0, y: -2 }
      , tileSize: { width: 10, height: 6 }
      , sprite: "textures/weapons/scalpel.png" }
    , projectile: "scalpel" }

  , { name: "pistol"
    , automatic: false
    , clipSize: 12
    , muzzleOffset: { x: 8, y: 0 }
    , reloadTime: 2.0
    , rpm: 120
    , sprite:
      { animation: "fire"
      , framerate: 10
      , offset: { x: 0, y: -3 }
      , tileSize: { width: 9, height: 9 }
      , sprite: "textures/weapons/pistol.png" }
    , projectile: "pistolbullet" }

  , { name: "acinade"
    , automatic: false
    , clipSize: -1
    , launchAngle: -45
    , muzzleOffset: { x: 3, y: 2 }
    , reloadTime: -1
    , rpm: 20
    , sprite:
      { animation: "fire"
      , framerate: 7
      , offset: { x: 0, y: -2 }
      , tileSize: { width: 9, height: 10 }
      , sprite: "textures/weapons/acinade.png" }
    , projectile: "acinade" }

  , { name: "ak47"
    , automatic: true
    , clipSize: 30
    , muzzleOffset: { x: 11, y: -4 }
    , reloadTime: 1.0
    , rpm: 400
    , sprite:
      { animation: "fire"
      , framerate: 8
      , offset: { x: -1, y: -5 }
      , tileSize: { width: 13, height: 8 }
      , sprite: "textures/weapons/ak47.png" }
    , projectile: "ak47bullet" }

  , { name: "saltsyra"
    , automatic: true
    , clipSize: -1
    , muzzleOffset: { x: 0, y: 0 }
    , reloadTime: -1
    , rpm: 120
    , sprite:
      { animation: "fire"
      , framerate: 8
      , offset: { x: 0, y: 0 }
      , tileSize: { width: 26, height: 37 }
      , sprite: "textures/weapons/saltsyra.png" }
    , projectile: "saltsyra" }

  , { name: "casio"
    , automatic: false
    , clipSize: -1
    , reloadTime: -1
    , rpm: 12
    , sprite:
      { animation: "fire"
      , framerate: 8
      , tileSize: { width: 26, height: 37 }
      , sprite: "textures/weapons/casio.png" }
    , projectile: "casio" }

  , { name: "mouse"
    , automatic: true
    , clipSize: -1
    , reloadTime: -1
    , rpm: 260
    , sprite:
      { animation: "fire"
      , framerate: 8
      , tileSize: { width: 26, height: 50 }
      , sprite: "textures/weapons/mouse.png" }
    , projectile: "mouse" }

  , { name: "socialism"
    , automatic: true
    , clipSize: -1
    , reloadTime: -1
    , rpm: 260
    , sprite: null
    , projectile: "socialism" }

  , { name: "ig"
    , automatic: true
    , clipSize: -1
    , reloadTime: -1
    , rpm: 260
    , sprite: null
    , projectile: "ig" }
] }
