Include "player.bb"
Include "ui.bb"

Global clearGui

Type container
	
	Field x
	Field y
	
	Field renderX
	Field renderY
	
	Field imx
	Field imy
	
	Field locked
	
	Field createdGui
	
	Field typeOf
	
	Field destroy
	
End Type

Function addContainer(x2, y2, typeOf2)
	container.container = New container
	container\x = x2
	container\y = y2
	
	container\typeOf = typeOf2
	
	container\imx = frame(container\typeOf)
	container\imy = frame(15)
End Function 

Function drawContainer()
	For container.container = Each container
		DrawImageRect(spritesheet, container\renderX, container\renderY, container\imx, container\imy, 16, 16)
	Next
End Function

Function updateContainer()
	For container.container = Each container
		container\renderX = container\x - cameraX
		container\renderY = container\y - cameraY
		
		For player.player = Each player
			If player\x = container\x And player\y = container\y Then
				clearGui = False 
				If container\createdGui = False Then
					For i = 0 To 2
						For j = 0 To 2
							addInvBoxS(300+i*20, 100+j*20, -1)
						Next
					Next
					container\createdGui = True 
				End If
			End If
			If player\x <> container\x Or player\y <> container\y Then
				If container\createdGui = True Then
					clearGui = True				
					container\createdGui = False 
				End If
				container\createdGui = False 
			End If			
		Next
		
	Next
End Function 