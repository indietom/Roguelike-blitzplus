Global spritesheet
spritesheet = LoadImage("spritesheet.bmp")

MaskImage(spritesheet, 255, 0, 255)

Const archer = 0
Const barbarian = 1
Const knight = 2
Const wizard = 3

Const heavy = 0
Const sword = 1
Const knife = 2
Const longRange = 3
Const wand = 4
Const ammo = 5
Const loot = 6
Const food = 7
Const helmet = 8
Const armor = 9
Const robe = 10
Const openTools = 11

Const chest = 0
Const bigChest = 1
Const bag = 2

Const eyeMonster = 0
Const minion = 1
Const soldier = 2
Const snake = 3
Const bat = 4
Const skeleton = 5
Const prisoner = 6
Const evilWizard = 7
Const satan = 8
Const viking = 9

Dim enemyNames$(viking)
enemyNames(0) = "Eye monster" 
enemyNames(1) = "Minon" 
enemyNames(2) = "Soldier"
enemyNames(3) = "Snake"
enemyNames(4) = "Bat"
enemyNames(5) = "Skeleton"
enemyNames(6) = "Prisoner"
enemyNames(7) = "Evil Wizard"
enemyNames(8) = "Satan"
enemyNames(9) = "Viking"  

Const king = 0
Const priest = 1
Const merchant = 2

Const leftKey = 30
Const rightKey = 32
Const upKey = 17
Const downKey = 31
Const useKey = 18

Global updateGame = False 

Global cameraX = 0
Global cameraY = 0