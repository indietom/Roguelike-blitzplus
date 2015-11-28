Include "globals.bb"
Include "functions.bb"
Include "item.bb"
Include "npc.bb"
Include "hitEffect.bb"

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
	
	Field hurt 
	Field dmTaken
	
	Field direction
	
	Field cantMoveLeft
	Field cantMoveRight
	Field cantMoveUp
	Field cantMoveDown
	
	Field canMoveAtAll
	
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
	
	player\canMoveAtAll = True 
	
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
		
		; You could have stopped it blitz
		If player\canMoveAtAll Then
			If KeyHit(useKey) Then
				FlushKeys()
				For item.item = Each item
					If player\x = item\x And player\y = item\y Then
						If item\typeOf <> loot Then 
							itemToAdd = item\typeOf
							subItemToAdd = item\subTypeOf
							storeItem = True
						Else 
							player\gold = player\gold + item\worth
						End If
						updateGame = True
						item\destroy = True 
					End If
				Next
			End If
		End If
		If player\canMoveAtAll Then 
		If player\cantMoveLeft = False Then
			If KeyHit(leftKey) Then
				player\direction = 3
				player\x = player\x - g(1)
				updateGame = True
			End If 
		End If
		If player\cantMoveRight = False Then
			If KeyHit(rightKey) Then 
				player\direction = 4
				player\x = player\x + g(1)
				updateGame = True
			End If 
		End If
		If player\cantMoveUp = False Then
			If KeyHit(upKey) Then
				player\direction = 2
				player\y = player\y - g(1)
				updateGame = True
			End If
		End If
		If player\cantMoveDown = False Then
			If KeyHit(downKey) Then 
				player\direction = 1
				player\y = player\y + g(1)
				updateGame = True
			End If
		End If
		End If
		
		For npc.npc = Each npc
			If player\x - 16 = npc\x And player\y = npc\y Then
				If KeyHit(leftKey) And npc\used = False Then
					npc\talking = True
					updateGame = True
				End If
			End If 
			If player\x + 16 = npc\x And player\y = npc\y Then
				If KeyHit(rightKey) And npc\used = False Then
					npc\talking = True
					updateGame = True
				End If
			End If 
			If player\x = npc\x And player\y + 16 = npc\y Then 
				If KeyHit(downKey) And npc\used = False Then
					npc\talking = True
					updateGame = True
				End If
			End If 
			If player\x = npc\x And player\y - 16 = npc\y Then
				If KeyHit(upKey) And npc\used = False Then
					npc\talking = True
					updateGame = True
				End If
			End If 
			If npc\talking Then player\canMoveAtAll = False 
		Next 

		If player\hp > 10 Then
			player\hp = 10
		End If
		
		player\dm = player\subHandEquipment + player\baseDm + 1
		player\def = player\subArmorEquipment+player\helmetEquipment + player\baseDef + 1
		
		If player\hurt Then 
			addAllHitEffects(player\x, player\y, 3)
			player\hurt = False 
		End If
		
		cameraX = player\x - 416/2
		cameraY = player\y - 288/2	
	Next
End Function

Function drawPlayer()
	For player.player = Each player
		DrawImageRect(spritesheet, player\renderX, player\renderY, player\imx, player\imy, 16, 16)
		Text 0, 0, player\cantMoveDown
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




