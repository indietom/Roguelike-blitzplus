Include "globals.bb"
Include "functions.bb"
Include "item.bb"

Type player
	Field x
	Field y
	
	Field renderX
	Field renderY
	
	Field imx
	Field imy
	
	Field class
	
	Field tag
	
	Field dm
	Field acc
	Field def
	
	Field baseDm
	Field baseAcc
	Field baseDef
	
	Field handEquipment
	Field subHandEquipment
	
	Field helmetEquipment
	
	Field armorEquipment
	Field subArmorEquipment 
	
	Field level
	Field currentXp
	Field maxXp
	
	Field gold
	
	Field mana
	Field hp
	
	Field direction
	
	Field cantMoveLeft
	Field cantMoveRight
	Field cantMoveUp
	Field cantMoveDown
	
	Field destroy
End Type

Function addPlayer(x2, y2, class2, tag2)
	player.player = New player
	player\x = x2
	player\y = y2
	player\renderX = x2
	player\renderY = y2
	player\class = class2
	player\tag = tag2
	
	player\imx = frame(player\class)
	player\imy = 1
	
	player\hp = 5
	player\mana = 10
	
	player\handEquipment = 0
	player\subHandEquipment = 0
	player\armorEquipment = 0
	player\subArmorEquipment = 0
	player\helmetEquipment = 0
	
	Select player\class
		Case archer
			player\acc = 10
			player\dm = 4
			player\def = 2
		Case barbarian
			player\acc = 3
			player\dm = 10
			player\def = 6
		Case knight
			player\acc = 5
			player\dm = 5
			player\def = 5
		Case wizard
			player\acc = 7
			player\dm = 7
			player\def = 1
	End Select
	
	player\baseAcc = player\acc
	player\baseDm = player\dm
	player\baseDef = player\def
End Function 

Function updatePlayer()
	For player.player = Each player
		player\renderX = player\x - cameraX
		player\renderY = player\y - cameraY
		
		If KeyHit(45) Then
			For item.item = Each item
				If player\x = item\x And player\y = item\y Then
					If item\typeOf <> loot Then 
						itemToAdd = item\typeOf
						subItemToAdd = item\subTypeOf
						storeItem = True
					Else 
						player\gold = player\gold + item\worth
						
					End If
					item\destroy = True 
				End If
			Next
		End If
		
		If KeyHit(leftKey) And player\cantMoveLeft = False Then
			player\direction = 3
			player\x = player\x - g(1)
		End If
		If KeyHit(rightKey) And player\cantMoveRight = False Then
			player\direction = 4
			player\x = player\x + g(1)
		End If
		If KeyHit(upKey) And player\cantMoveUp = False Then
			player\direction = 2
			player\y = player\y - g(1)
		End If
		If KeyHit(downKey) And player\cantMoveDown = False Then
			player\direction = 1
			player\y = player\y + g(1)
		End If
		
		If player\hp > 10 Then
			player\hp = 10
		End If
		
		player\dm = player\subHandEquipment + player\baseDm + 1
		player\def = player\subArmorEquipment+player\helmetEquipment + player\baseDef + 1
		
		cameraX = player\x - 416/2
		cameraY = player\y - 288/2	
	Next
End Function

Function drawPlayer()
	For player.player = Each player
		DrawImageRect(spritesheet, player\renderX, player\renderY, player\imx, player\imy, 16, 16)
	Next
End Function

Function playerEquipmentUi()
	For player.player = Each player
		For i = 0 To 2
			DrawImageRect(spritesheet, 250, 480-75+i*20, 595, frameSize(i+1, 18), 18, 18)
		Next
		DrawImageRect(spritesheet, 250+1, (480-75+0*20)+1, frame(player\subHandEquipment), frame(3 + player\handEquipment) , 16, 16)
		DrawImageRect(spritesheet, 250+1, (480-75+1*20)+1, frame(player\helmetEquipment), frame(11) , 16, 16)
		DrawImageRect(spritesheet, 250+1, (480-75+2*20)+1, frame(player\subArmorEquipment), frame(11 + player\armorEquipment) , 16, 16)
	Next
End Function 








