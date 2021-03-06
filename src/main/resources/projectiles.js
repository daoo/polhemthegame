{ projectiles:
  { "scalpel":
    { aoe: null
    , collides: true
    , damage: 100
    , duration: 60
    , gravity: false
    , hitbox: { width: 100, height: 100 }
    , range: 20
    , speed: 500
    , sprite: null
    , targets: 1
    , texture: null }

  , "pistolbullet":
    { aoe: null
    , collides: true
    , damage: 150
    , duration: 60000
    , gravity: false
    , hitbox: { width: 3, height: 2 }
    , range: 2000
    , speed: 2000
    , sprite: null
    , targets: 1
    , texture: "textures/projectiles/bullet.png" }

  , "acinade":
    { aoe: 
      { damage: 75
      , radius: 300
      , keepEffect: true
      , explosionSprite:
        { framerate: 8
        , offset: { x: -142, y: -141 }
        , sprite: "textures/projectiles/acinadehit.png"
        , tileSize: { width: 285, height: 283 } } }
    , collides: false
    , damage: 0
    , duration: 3000
    , gravity: true
    , hitbox: { width: 4, height: 4 }
    , range: 2000
    , speed: 200
    , sprite: null
    , targets: 1
    , texture: "textures/projectiles/acinade.png" }

  , "ak47bullet":
    { aoe: null
    , collides: true
    , damage: 25
    , duration: 60000
    , gravity: false
    , hitbox: { width: 3, height: 2 }
    , range: 2000
    , speed: 1000
    , sprite: null
    , targets: 1
    , texture: "textures/projectiles/bullet.png" }

  , "saltsyra":
    { aoe: null
    , collides: true
    , damage: 2
    , duration: 60000
    , gravity: false
    , hitbox: { width: 50, height: 50 }
    , range: 500
    , speed: 250
    , sprite:
      { framerate: 8
      , offset: { x: 0, y: 0 }
      , random: true
      , sprite: "textures/projectiles/saltlauncher.png"
      , tileSize: { width: 50, height: 50 } }
    , targets: 10
    , texture: null }

  , "casio":
    { aoe: null
    , collides: true
    , damage: 1000
    , duration: 2000
    , gravity: false
    , hitbox: { width: 680, height: 10 }
    , range: 0
    , speed: 0
    , sprite:
      { framerate: 8
      , offset: { x: 0, y: 0 }
      , sprite: "textures/projectiles/casio.png"
      , tileSize: { width: 680, height: 10 } }
    , targets: 1000
    , texture: null }

  , "mouse":
    { aoe: null
    , collides: true
    , damage: 5
    , duration: 60000
    , gravity: false
    , hitbox: { width: 16, height: 3 }
    , range: 2000
    , speed: 400
    , sprite: null
    , targets: 1
    , texture: "textures/projectiles/mouse.png" }

  , "socialism":
    { aoe: null
    , collides: true
    , damage: 5
    , duration: 60000
    , gravity: false
    , hitbox: { width: 35, height: 36 }
    , range: 2000
    , speed: 500
    , sprite: null
    , targets: 5
    , texture: "textures/projectiles/socialism.png" }

  , "ig":
    { aoe: null
    , collides: true
    , damage: 10
    , duration: 60000
    , gravity: false
    , hitbox: { width: 12, height: 8 }
    , range: 2000
    , speed: 600
    , sprite: null
    , targets: 1
    , texture: "textures/projectiles/ig.png" }
  }
}
