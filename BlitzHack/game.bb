Include "player.bb"
Include "functions.bb"
Include "globals.bb"
Include "item.bb"
Include "ui.bb"
Include "container.bb"
Include "enemy.bb"
Include "npc.bb"
Include "hitEffect.bb"

Graphics 640, 480, 8, 2

SeedRnd MilliSecs()

Global frametimer=CreateTimer(60)
Global starttime=MilliSecs(),elapsedtime,fpscounter,curfps

Global updateGameDelay

Global readInfo = 1

Dim map(10, 10)
Data 1,2,3,4,5,6,7,8,9,10
Data 12,12,13,14,15,16,12,0,12,0
Data 1,12,12,12,12,12,12,12,12,12
Data 1,12,12,12,12,12,12,12,12,12
Data 1,12,12,0,3,2,2,2,4,12
Data 1,12,0,12,1,12,0,12,1,0
Data 0,12,0,12,1,12,0,12,1,0
Data 0,12,0,12,5,2,2,2,6,0
Data 0,12,0,12,0,12,0,12,0,0
Data 0,12,0,12,0,12,0,12,0,0

Dim mapC(10, 10)
Data 1,1,1,1,1,1,1,12,0,0
Data 1,12,0,12,0,12,0,12,0,0
Data 1,12,0,12,0,12,0,12,0,0
Data 1,12,0,12,0,12,0,12,0,0
Data 1,12,0,12,0,1,1,1,12,0
Data 1,12,0,12,0,12,0,12,0,0
Data 0,12,0,12,0,12,0,12,0,0
Data 0,12,0,12,0,12,0,12,0,0
Data 0,12,0,12,0,12,0,12,0,0
Data 0,12,0,12,0,12,0,12,0,0

For i = 0 To 9
	addItem(g(2), g(20)+i*g(1), sword, i)
	addItem(g(4), g(20)+i*g(1), helmet, i)
	addItem(g(6), g(20)+i*g(1), armor, i)
	addItem(g(7), g(20)+i*g(1), food, i)
	addEnemy(g(12), g(20)+i*g(1), i, 0)
	addContainer(g(10), g(20)+i*g(1), i)
	If i < 3 Then
		addNpc(g(2), g(5)+i*g(1), i)
	End If
Next
addPlayer(g(3), g(3), 2, 1)

Function drawMap()
	For y = 1 To 10
		For x = 1 To 10
			If readInfo = 1 Read map(x,y)  
		 	For i = 0 To 20
				If map(x, y) = i Then
					DrawImageRect(spritesheet, (x*16)-cameraX, (y*16)-cameraY, i*16, 307, 16, 16)
				End If
			Next
		Next
	Next
	
	For y = 1 To 10
		For x = 1 To 10
			If readInfo = 1 Read mapC(x,y)  
				For npc.npc = Each npc
					If npc\x = x*16 And npc\y = y*16 Then
						mapC(x, y) = 1
					End If
					If npc\pastX = x*16 And npc\pastY = y*16 Then
						mapC(x, y) = 0
					End If
				Next	
			
				For player.player = Each player
					If player\x-g(1) = g(x) And player\y = g(y) And mapC(x,y) = 1
						player\cantMoveLeft = True 
					End If
					If player\x-g(1) = g(x) And player\y = g(y) And mapC(x,y) <> 1	
						player\cantMoveLeft = False  
					End If
				
					If player\x+g(1) = g(x) And player\y = g(y) And mapC(x,y) = 1
						player\cantMoveRight = True 
					End If
					If player\x+g(1) = g(x) And player\y = g(y) And mapC(x,y) <> 1
						player\cantMoveRight = False  
					End If
				
					If player\y-g(1) = g(y) And player\x = g(x) And mapC(x,y) = 1
						player\cantMoveUp = True 
					End If
					If player\y-g(1) = g(y) And player\x = g(x) And mapC(x,y) <> 1
						player\cantMoveUp = False  
					End If
				
					If player\y+g(1) = g(y) And player\x = g(x) And mapC(x,y) = 1
						player\cantMoveDown = True 
					End If
					If player\y+g(1) = g(y) And player\x = g(x) And mapC(x,y) <> 1
						player\cantMoveDown = False  
					End If
				Next
			Next
		Next
	readInfo = 0
End Function 

Function draw()
	drawMap()
	drawItem()
	drawContainer()
	drawPlayer()
	drawEnemy()
	drawNpc()
	
	drawHitEffect()
	drawHitEffectText()
	
	drawUi()
	drawInvBox()
	drawEvenText()
End Function

Function update()
	updatePlayer()
	updateItem()
	updateEventText()
	updateInvBox()
	updateContainer()
	updateEnemy()
	updateNpc()
	updateHitEffectText()
	updateHitEffect()
End Function 

Function ini()
	For i = 0 To 18
		addInvBox(i*22, g((320-32)/16)+g(3))
		addInvBox(i*22, g((320-32)/16)+g(4)+4)
		addInvBox(i*22, g((320-32)/16)+g(5)+8)
	Next
	For i = 1 To 3
		addInvBoxS(232, g((320-32)/16)+g(5)+18 + i * 20, i) 
	Next
End Function 

ini()

While Not KeyHit(1)

	Cls 
		WaitTimer(frametimer)
		
		draw()
		
		update()

		DrawImageRect(spritesheet, MouseX(), MouseY(), 579, 1, 8, 8)
		HidePointer()
		
		If KeyDown(5) Then
			Text 0, 0, updateGame
		End If
		
		updateGame = False 
		
	Flip

Wend