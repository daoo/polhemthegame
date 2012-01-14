{ weapons:
  { "scalpel":
    { automatic: false
    , clipSize: -1
    , muzzleOffset: { x: 4, y: 3 }
    , reloadTime: 0
    , rpm: 120
    , sprite:
      { framerate: 8
      , offset: { x: 0, y: -2 }
      , tileSize: { width: 10, height: 6 }
      , sprite: "textures/weapons/scalpel.png" }
    , projectile: "scalpel" }

  , "pistol":
    { automatic: false
    , clipSize: 12
    , muzzleOffset: { x: 8, y: 0 }
    , reloadTime: 2.0
    , rpm: 120
    , sprite:
      { framerate: 10
      , offset: { x: 0, y: -3 }
      , tileSize: { width: 9, height: 9 }
      , sprite: "textures/weapons/pistol.png" }
    , projectile: "pistolbullet" }

  , "acinade":
    { automatic: false
    , clipSize: -1
    , launchAngle: -45
    , muzzleOffset: { x: 3, y: 2 }
    , reloadTime: -1
    , rpm: 20
    , sprite:
      { framerate: 7
      , offset: { x: 0, y: -2 }
      , tileSize: { width: 9, height: 10 }
      , sprite: "textures/weapons/acinade.png" }
    , projectile: "acinade" }

  , "ak47":
    { automatic: true
    , clipSize: -1
    , muzzleOffset: { x: 11, y: -4 }
    , reloadTime: 1.0
    , rpm: 1600
    , sprite:
      { framerate: 8
      , offset: { x: -1, y: -5 }
      , tileSize: { width: 13, height: 8 }
      , sprite: "textures/weapons/ak47.png" }
    , projectile: "ak47bullet" }

  , "saltsyra":
    { automatic: true
    , clipSize: -1
    , muzzleOffset: { x: 10, y: -25 }
    , reloadTime: -1
    , rpm: 120
    , sprite:
      { framerate: 8
      , offset: { x: -10, y: -7 }
      , tileSize: { width: 22, height: 15 }
      , sprite: "textures/weapons/saltsyra.png" }
    , projectile: "saltsyra" }

  , "casio":
    { automatic: false
    , clipSize: -1
    , muzzleOffset: { x: 10, y: -3 }
    , reloadTime: -1
    , rpm: 60
    , sprite:
      { framerate: 8
      , offset: { x: 0, y: -2 }
      , tileSize: { width: 8, height: 6 }
      , sprite: "textures/weapons/casio.png" }
    , projectile: "casio" }

  , "mouse":
    { automatic: true
    , clipSize: -1
    , reloadTime: -1
    , rpm: 260
    , sprite:
      { framerate: 8
      , tileSize: { width: 26, height: 50 }
      , sprite: "textures/weapons/mouse.png" }
    , projectile: "mouse" }

  , "socialism":
    { automatic: true
    , clipSize: -1
    , reloadTime: -1
    , rpm: 260
    , sprite: null
    , projectile: "socialism" }

  , "ig":
    { automatic: true
    , clipSize: -1
    , muzzleOffset: { x: -20, y: -4 }
    , reloadTime: -1
    , rpm: 260
    , sprite: null
    , projectile: "ig" }
  }
}
