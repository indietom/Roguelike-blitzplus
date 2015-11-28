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
	
	Field randDm
	
	Field baseDm
	Field baseAcc
	Field baseDef
	
	Field hp
	
	Field level
	
	Field worthXp
	
	Field dropLoot
	
	Field chanceOfLoot
	
	Field chanceOfAttack
	
	Field hitDelay
	
	Field hurt
	
	Field active
	
	Field canAttack
	
	Field lootTypeOf
	Field lootSubTypeOf
	
	Field moved
	Field canMove
	
	Field pastX
	Field pastY
	
	Field currentTile
	
	Field destroy
End Type

Function addEnemy(x2, y2, typeOf2, level2)
	enemy.enemy = New enemy
	enemy\x = x2
	enemy\y = y2
	
	enemy\typeOf = typeOf2
	
	enemy\imx = frame(enemy\typeOf)
	enemy\imy = frame(1)
	
	enemy\name = enemyNames(enemy\typeOf)
	
	enemy\dm = 3
	
	Select enemy\typeOf
		
	End Select
End Function  

Function updateEnemy()
	For enemy.enemy = Each enemy
		enemy\renderX = enemy\x - cameraX
		enemy\renderY = enemy\y - cameraY
		
		For player.player = Each player
			If player\x - 16 = enemy\x And player\y = enemy\y And updateGame Then
				enemy\chanceOfAttack = Rand(7)
			End If
			If player\x + 16 = enemy\x And player\y = enemy\y And updateGame Then
				enemy\chanceOfAttack = Rand(7)
			End If
			If player\x = enemy\x And player\y - 16 = enemy\y And updateGame Then
				enemy\chanceOfAttack = Rand(7)
			End If
			If player\x = enemy\x And player\y + 16 = enemy\y And updateGame Then
				enemy\chanceOfAttack = Rand(7)
			End If
			
			If enemy\chanceOfAttack = 3 And enemy\hitDelay <= 0 Then
				player\hurt = True 
				enemy\randDm = Rand(enemy\dm)
				player\hp = player\hp - enemy\randDm
				player\dmTaken = enemy\randDm
				enemy\chanceOfAttack = 0
				enemy\hitDelay = 1
			End If
		Next
		
		If enemy\hitDelay >= 1 And updateGame Then
			enemy\hitDelay = enemy\hitDelay + 1
		End If
		If enemy\hitDelay >= 3 Then
			enemy\hitDelay = 0
		End If
		
		If enemy\destroy Then
			Delete enemy
		End If
	Next
End Function

Function drawEnemy()
	For enemy.enemy = Each enemy
		DrawImageRect(spritesheet, enemy\renderX, enemy\renderY, enemy\imx, enemy\imy, 16, 16)
		Text enemy\renderX, enemy\renderY, enemy\hitDelay
		Text enemy\renderX+16, enemy\renderY+15, enemy\chanceOfAttack
	Next
End Function 





























