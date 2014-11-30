Include "player.bb"
Include "ui.bb"
Include "globals.bb"

Type enemy
	Field x
	Field y
	
	Field imx
	Field imy
	
	Field name$
	
	Field renderX
	Field renderY
	
	Field typeOf
	
	Field direction
	
	Field dm
	Field acc
	Field def
	
	Field baseDm
	Field baseAcc
	Field baseDef
	
	Field level
	
	Field worthXp
	
	Field dropLoot
	
	Field chanceOfLoot
	
	Field hurt
	
	Field active
	
	Field lootTypeOf
	Field lootSubTypeOf
	
	Field moved
	Field canMove
	
	Field pastX
	Field pastY
	
	Field currentTile
	
	Field destroy
End Type

Function updateEnemy()
	For enemy.enemy = Each enemy
		enemy\renderX = enemy\x - cameraX
		enemy\renderY = enemy\y - cameraY
		
		If enemy\destroy Then
			Delete enemy
		End If
	Next
End Function

Function drawEnemy()
	For enemy.enemy = Each enemy
		DrawImageRect(spritesheet, enemy\renderX, enemy\renderY, enemy\imx, enemy\imy, 16, 16)
	Next
End Function 


































