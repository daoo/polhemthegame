{ projectiles:
  [ { name: "scalpel"
    , aoe: null
    , collides: true
    , damage: 100
    , duration: -1
    , gravity: false
    , hitbox: [ 1, 1 ]
    , range: 20
    , speed: 250
    , sprite: null
    , targets: 1
    , texture: null }

  , { name: "pistolbullet"
    , aoe: null
    , collides: true
    , damage: 50
    , duration: -1
    , gravity: false
    , hitbox: [ 3, 2 ]
    , range: -1
    , speed: 500
    , sprite: null
    , targets: 1
    , texture: "textures/projectiles/bullet.png" }

  , { name: "acinade"
    , aoe: 
      { radius: 100
      , keepEffect: true
      , explosionSprite:
        { animation: "explosion"
        , framerate: 8
        , offset: [ 0, 0 ]
        , sprite: "textures/projectiles/acinadehit.png"
        , tileSize: [ 200, 83 ] } }
    , collides: false
    , damage: 100
    , duration: 3.4
    , gravity: true
    , hitbox: [ 4, 4 ]
    , range: -1
    , speed: 200
    , sprite: null
    , targets: 1
    , texture: "textures/projectiles/acinade.png" }

  , { name: "ak47bullet"
    , aoe: null
    , collides: true
    , damage: 100
    , duration: -1
    , gravity: false
    , hitbox: [ 3, 2 ]
    , range: -1
    , speed: 1000
    , sprite: null
    , targets: 1
    , texture: "textures/projectiles/bullet.png" }

  , { name: "saltsyra"
    , aoe: null
    , collides: true
    , damage: 100
    , duration: -1
    , gravity: false
    , range: 250
    , speed: 250
    , sprite:
      { animation: "travel"
      , framerate: 8
      , tileSize: [ 50, 50 ]
      , sprite: "textures/projectiles/saltlauncher.png" }
    , targets: 1
    , texture: null }

  , { name: "casio"
    , aoe: null
    , collides: true
    , damage: 1000
    , duration: 2500
    , gravity: false
    , range: -1
    , speed: 0
    , sprite:
      { animation: "travel"
      , framerate: 8
      , tileSize: [ 680, 10 ]
      , sprite: "textures/projectiles/casio.png" }
    , targets: -1
    , texture: null }

  , { name: "mouse"
    , aoe: null
    , collides: true
    , damage: 5
    , duration: -1
    , gravity: false
    , range: -1
    , speed: 250
    , sprite: null
    , targets: 1
    , texture: "textures/projectiles/mouse.png" }

  , { name: "socialism"
    , aoe: null
    , collides: true
    , damage: 5
    , duration: -1
    , gravity: false
    , range: -1
    , speed: 250
    , sprite: null
    , targets: 1
    , texture: "textures/projectiles/socialism.png" }

  , { name: "ig"
    , aoe: null
    , collides: true
    , damage: 5
    , duration: -1
    , gravity: false
    , range: -1
    , speed: 250
    , sprite: null
    , targets: 1
    , texture: "textures/projectiles/ig.png" }
] }
