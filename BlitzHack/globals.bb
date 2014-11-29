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

Const leftKey = 203
Const rightKey = 205
Const upKey = 200
Const downKey = 208

Global updateGame = False 

Global cameraX = 0
Global cameraY = 0
