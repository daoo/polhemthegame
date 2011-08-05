{ weapons:
  [ { name: "scalpel" 
    , automatic: false
    , clipSize: -1
    , muzzleOffset: [ 4, 3 ]
    , reloadTime: 0
    , rpm: 120
    , sprite:
      { animation: "fire"
      , framerate: 8
      , offset: [ 0, -2 ]
      , tileSize: [ 10, 6 ]
      , sprite: "textures/weapons/scalpel.png" }
    , projectile: "scalpel" }

  , { name: "pistol"
    , automatic: false
    , clipSize: 12
    , muzzleOffset: [ 8, 0 ]
    , reloadTime: 2.0
    , rpm: 120
    , sprite:
      { animation: "fire"
      , framerate: 10
      , offset: [ 0, -3 ]
      , tileSize: [ 9, 9 ]
      , sprite: "textures/weapons/pistol.png" }
    , projectile: "pistolbullet" }

  , { name: "acinade"
    , automatic: false
    , clipSize: -1
    , launchAngle: -45
    , muzzleOffset: [ 3, 2 ]
    , reloadTime: -1
    , rpm: 20
    , sprite:
      { animation: "fire"
      , framerate: 7
      , offset: [ 0, -2 ]
      , tileSize: [ 9, 10 ]
      , sprite: "textures/weapons/acinade.png" }
    , projectile: "acinade" }

  , { name: "ak47"
    , automatic: true
    , clipSize: 30
    , muzzleOffset: [ 11, -4 ]
    , reloadTime: 1.0
    , rpm: 400
    , sprite:
      { animation: "fire"
      , framerate: 8
      , offset: [ -1, -5 ]
      , tileSize: [ 13, 8 ]
      , sprite: "textures/weapons/ak47.png" }
    , projectile: "ak47bullet" }

  , { name: "saltsyra"
    , automatic: true
    , clipSize: -1
    , reloadTime: -1
    , rpm: 120
    , sprite:
      { animation: "fire"
      , framerate: 8
      , tileSize: [ 26, 37 ]
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
      , tileSize: [ 26, 37 ]
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
      , tileSize: [ 26, 50 ]
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
