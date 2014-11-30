Include "functions.bb"

SeedRnd MilliSecs() 

Type npc
	Field x
	Field y
	
	Field renderX
	Field renderY
	
	Field imx
	Field imy
	
	Field moved
	
	Field canMove
	
	Field typeOf
	
	Field name$
	
	Field dialog$
	Field talking
	
	Field used
	
	Field pastX
	Field pastY
	
	Field currentTile
	Field pastTile
	
	Field destroy
End Type

Function addNpc(x2, y2, typeOf2)
	npc.npc = New npc
	npc\x = x2
	npc\y = y2
	npc\typeOf = typeOf2
	
	npc\imx = frame(npc\typeOf)
	npc\imy = frame(2)
	
	Select npc\typeOf
		Case 0
			npc\name = "King Karl the " + toRoman(Rand(1, 11))
			npc\dialog = "I am " + npc\name 
		Case 1
			npc\name = "Priest"
			npc\dialog = "I am a " + npc\name + " and I can heal you!"
		Case 2
			npc\name = "Merchant"
			npc\dialog = "I am a " + npc\name + " what are you buying?"
	End Select
End Function 

Function updateNpc()
	For npc.npc = Each npc
		npc\renderX = npc\x - cameraX
		npc\renderY = npc\y - cameraY
		
		If npc\moved Then
			npc\moved = False
		End If
		
		If KeyHit(2) Then
			npc\moved = True
			npc\pastX = npc\x
			npc\pastY = npc\y
			npc\x = npc\x + g(1)
		End If
		
		If npc\destroy Then
			Delete npc
		End If
	Next
End Function

Function drawNpc()
	For npc.npc = Each npc
		DrawImageRect(spritesheet, npc\renderX, npc\renderY, npc\imx, npc\imy, 16, 16)
	Next
End Function 













