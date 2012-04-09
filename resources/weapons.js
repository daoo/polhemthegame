{ weapons:
  { "scalpel":
    { automatic: false
    , clipSize: -1
    , launchAngle: 0
    , muzzleOffset: { x: 7, y: 3 }
    , reloadTime: 0
    , rpm: 120
    , spread: 0
    , sprite:
      { framerate: 8
      , offset: { x: 0, y: -2 }
      , sprite: "textures/weapons/scalpel.png"
      , tileSize: { width: 10, height: 6 } }
    , projectile: "scalpel" }

  , "pistol":
    { automatic: false
    , clipSize: 12
    , launchAngle: 0
    , muzzleOffset: { x: 8, y: 0 }
    , reloadTime: 3000
    , rpm: 100
    , spread: 0
    , sprite:
      { framerate: 10
      , offset: { x: 0, y: -3 }
      , sprite: "textures/weapons/pistol.png"
      , tileSize: { width: 9, height: 9 } }
    , projectile: "pistolbullet" }

  , "acinade":
    { automatic: false
    , clipSize: -1
    , launchAngle: -45
    , muzzleOffset: { x: 3, y: 2 }
    , reloadTime: 0
    , rpm: 20
    , spread: 5
    , sprite:
      { framerate: 7
      , offset: { x: 0, y: -2 }
      , sprite: "textures/weapons/acinade.png"
      , tileSize: { width: 9, height: 10 } }
    , projectile: "acinade" }

  , "ak47":
    { automatic: true
    , clipSize: 27
    , launchAngle: 0
    , muzzleOffset: { x: 11, y: -3 }
    , reloadTime: 2000
    , rpm: 600
    , spread: 0
    , sprite:
      { framerate: 25
      , offset: { x: -1, y: -5 }
      , sprite: "textures/weapons/ak47.png"
      , tileSize: { width: 15, height: 8 } }
    , projectile: "ak47bullet" }

  , "saltsyra":
    { automatic: true
    , clipSize: 100
    , launchAngle: 0
    , muzzleOffset: { x: 10, y: -25 }
    , reloadTime: 15000
    , rpm: 2500
    , spread: 5
    , sprite:
      { framerate: 8
      , offset: { x: -10, y: -7 }
      , sprite: "textures/weapons/saltsyra.png"
      , tileSize: { width: 20, height: 15 } }
    , projectile: "saltsyra" }

  , "casio":
    { automatic: false
    , clipSize: -1
    , launchAngle: 0
    , muzzleOffset: { x: 10, y: -3 }
    , reloadTime: 0
    , rpm: 60
    , spread: 0
    , sprite:
      { framerate: 8
      , offset: { x: 0, y: -2 }
      , sprite: "textures/weapons/casio.png"
      , tileSize: { width: 8, height: 6 } }
    , projectile: "casio" }

  , "mouse":
    { automatic: true
    , clipSize: -1
    , launchAngle: 0
    , muzzleOffset: { x: 17, y: -3 }
    , reloadTime: 0
    , rpm: 260
    , spread: 20
    , sprite:
      { framerate: 8
      , offset: { x: -2, y: -4 }
      , sprite: "textures/weapons/mouse.png"
      , tileSize: { width: 18, height: 9 } }
    , projectile: "mouse" }

  , "socialism":
    { automatic: true
    , clipSize: -1
    , launchAngle: 0
    , muzzleOffset: { x: 10, y: -18 }
    , reloadTime: 0
    , rpm: 600
    , spread: 20
    , sprite:
      { framerate: 100
      , offset: { x: 5, y: -18 }
      , sprite: "textures/weapons/socialism.png"
      , tileSize: { width: 35, height: 36 } }
    , projectile: "socialism" }

  , "ig":
    { automatic: true
    , clipSize: -1
    , launchAngle: 0
    , muzzleOffset: { x: 6, y: -4 }
    , reloadTime: 0
    , rpm: 260
    , spread: 20
    , sprite:
      { framerate: 100
      , offset: { x: 0, y: 0 }
      , sprite: "textures/null.png"
      , tileSize: { width: 1, height: 1 } }
    , projectile: "ig" }
  }
}
