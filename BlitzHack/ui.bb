Include "globals.bb"
Include "functions.bb"
Include "player.bb"

Global eventTextCount
Global removeAll

Global invBoxCount
Global nextInvBox = 1
Global itemToAdd = -1
Global subItemToAdd = -1

Global storeItem 
Global itemToRemove
Global removeItem

Global moveItem
Global subMoveItem

Function drawUi()
	Color 117, 113, 97
	Rect g((420)/16), 0, 300, 480
	Rect 0, g((320-32)/16), 640, 220
	clearColor()
	
	For player.player = Each player
		Color 0, 255, 0
		Text 16, 480-75, "Attack: " + player\dm
		Text 16, 480-75+20, "Accuracy: " + player\acc
		Text 16, 480-75+40, "Defense: " + player\def
		Color 255, 255, 0
		Text 116, 480-75, "Level: " + player\level
		Text 116, 480-75+20, "Next level: " + (maxXp-player\currentXp)
		Text 116, 480-75+40, "Gold: " + player\gold
		Color 221, 69, 73
		DrawImageRect(spritesheet,8, g((320-32)/16)-16, 579, 18, 16, 16) 
		Rect 0, g((320-32)/16)-16, player\hp*10, 16
		Color 89, 125, 207
		Rect g((420)/16)-player\mana*10, g((320-32)/16)-16, player\mana*10, 16
		Color 100, 100, 100
		Rect 0, g((320-32)/16)-20, 100, 4
		Rect 100, g((320-32)/16)-20, 4, 20
		Rect g((420)/16)-100, g((320-32)/16)-20, 100, 4
		Rect g((420)/16)-104, g((320-32)/16)-20, 4, 20
		clearColor() 
	Next
	
	drawNpcUi()
	
End Function 

Function drawNpcUi()
	For npc.npc = Each npc 
		If npc\talking Then
				Local textX = 630 - StringWidth(npc\dialog$) - 10
				DrawImageRect(spritesheet, textX, 240-1, 595, 1, 9, 18)
				For i = 0 To StringWidth(npc\dialog$)/3
					DrawImageRect(spritesheet, textX-1+9+i*3, 240-1, 599, 1, 3, 18)
				Next
				DrawImageRect(spritesheet, textX-1+9+(StringWidth(npc\dialog$)/3)*3, 240-1, 604, 1, 9, 18)
				Text textX, 240, npc\dialog
				If KeyHit(useKey) Then
					If npc\typeOf <> merchant Then 
						npc\used = True 
					End If 
					For player.player = Each player 
						player\canMoveAtAll = True 
						If npc\typeOf = priest Then
							player\hp = 10
						End If
						If npc\typeOf = king Then
							player\gold = player\gold + 50
						End If
					Next
					npc\talking = False
				End If
			End If
		Next
End Function 

Type eventText
	Field x
	Field y
	
	Field tag
	
	Field txt$
	
	Field r
	Field g
	Field b
	
	Field destroy
End Type

Function addEventText(x2, y2, txt2$)
	eventText.eventText = New eventText
	eventTextCount = eventTextCount + 1
	eventText\tag = eventTextCount
	eventText\x = x2
	eventText\y = y2 + 22 * eventTextCount
	
	eventText\txt$ = txt2
End Function

Function drawEvenText()
	For eventText.eventText = Each eventText
		Text eventText\x, eventText\y, eventText\txt
	Next
End Function

Function updateEventText()
	For eventText.eventText = Each eventText 
		If eventTextCount > 10 Then
			removeAll = True 	
		End If
		
		If removeAll Then
			eventText\destroy = True
		End If
		
		If eventText\destroy Then
			eventTextCount = eventTextCount - 1
			Delete eventText
		End If
	Next
	If eventTextCount <= 0 Then
		removeAll = False 
	End If
End Function 

Type invBox
	Field x
	Field y
	
	Field itemImx
	Field itemImy
	
	Field imx
	Field imy
	
	Field typeOf
	Field subTypeOf
	
	Field specialType
	
	Field filled
	Field tag
	
	Field destroy
End Type

Function addInvBox(x2, y2)
	invBox.invBox = New invBox
	invBox\x = x2
	invBox\y = y2
	invBox\imx = 595
	invBox\imy = 1
	
	invBox\typeOf = -1
	invBox\subTypeOf = -1
	
	invBoxCount = invBoxCount +1
	
	invBox\tag = invBoxCount
End Function 

Function addInvBoxS(x2, y2, specialType2)
	invBox.invBox = New invBox
	invBox\x = x2
	invBox\y = y2
	invBox\imx = 595
	invBox\imy = 1
	
	invBox\typeOf = -1
	invBox\subTypeOf = -1
	
	invBox\specialType = specialType2
	
	If invBox\specialType >= 0 Then
		invBox\imy = frameSize(invBox\specialType, 18) 
	End If
	
	invBoxCount = invBoxCount + 1
	
	invBox\tag = invBoxCount
End Function 

Function drawInvBox()
	For invBox.invBox = Each invBox
		DrawImageRect(spritesheet, invBox\x, invBox\y, invBox\imx, invBox\imy, 18, 18)
		If invBox\filled Then
			DrawImageRect(spritesheet, invBox\x+1, invBox\y+1, invBox\itemImx, invBox\ItemImy, 16, 16)
		End If
	Next
	If moveItem <> -1 Then 
		DrawImageRect(spritesheet, MouseX()+3, MouseY()+3, frame(subMoveItem), frame(3+moveItem), 16, 16)
	End If 
End Function

Function updateInvBox()
	For invBox.invBox = Each invBox
		If invBox\typeOf = -1 Or invBox\subTypeOf = -1 Then
			invBox\filled = False 
		End If	
		If invBox\typeOf >= 0 Or invBox\subTypeOf >= 0 Then
			invBox\filled = True 
		End If
		
		invBox\itemImx = frame(invBox\subTypeOf)
		invBox\itemImy = frame(3 + invBox\typeOf)
		
		If collision(invBox\x, invBox\y, 18, 18, MouseX(), MouseY(), 8, 8) Then
			If MouseHit(1) Then
				For player.player = Each player
					If invBox\typeOf = food And player\hp < 10 Then
						If invBox\subTypeOf <> 0 Then 
							If player\hp + invBox\subTypeOf >= 10 Then
								player\hp = 10
							Else 
								player\hp = player\hp + invBox\subTypeOf
							End If 
						Else
							player\hp = 10
						End If
						invBox\typeOf = -1
						invBox\subTypeOf = -1
						updateGame = True
					End If
				Next
			End If	
			If MouseHit(2) Then
				invBox\typeOf = -1
				invBox\subTypeOf = -1
			End If 
			If MouseHit(3) Then
				If invBox\filled And moveItem = -1 Then
					moveItem = invBox\typeOf
					subMoveItem = invBox\subTypeOf
					invBox\typeOf = -1
					invBox\subTypeOf = -1
				End If
				If invBox\filled = False Then
					invBox\typeOf = moveItem
					invBox\subTypeOf = subMoveItem 
					moveItem = -1
					subMoveItem = -1
				End If 
			End If
		End If 
		
		If invBox\tag = nextInvBox And storeItem Or invBox\tag < nextInvBox And invBox\filled = False And storeItem Then
			invBox\typeOf = itemToAdd
			invBox\subTypeOf = subItemToAdd
			
			itemToAdd = -1
			subItemToAdd = -1
			
			nextInvBox = nextInvBox + 1
			storeItem = False
		End If
		
		For player.player = Each player 
			Select invBox\specialType 
				Case 1
					If invBox\typeOf >= heavy And invBox\typeOf <= wand Then
						player\subHandEquipment = invBox\subTypeOf+1
					Else
						player\subHandEquipment = 0
					End If
				Case 2
					If invBox\typeOf = helmet Then 
						player\helmetEquipment = invBox\subTypeOf+1
					Else
						player\helmetEquipment = 0
					End If
				Case 3
					If invBox\typeOf = armor Or invBox\typeOf = robe Then 
						player\subArmorEquipment = invBox\subTypeOf+1
					Else
						player\subArmorEquipment = 0
					End If
			End Select
		Next
		
		If clearGui And invBox\specialType  = -1 Then
			invBox\destroy = True 
			clearGui = True 
		End If
		
		If invBox\tag = itemToRemove And removeItem Then
			invBox\typeOf = -1
			invBox\subTypeOf = -1
			removeItem = False
		End If
		
		If invBox\destroy Then
			invBoxCount = invBoxCount - 1
			Delete invBox
		End If
	Next
End Function 





























