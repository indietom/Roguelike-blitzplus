Include "globals.bb"
Include "functions.bb"

Type item
	Field x
	Field y
	
	Field renderX
	Field renderY
	
	Field imx
	Field imy
	
	Field typeOf
	Field subTypeOf
	
	Field name
	
	Field worth
	Field hpWorth
	
	Field destroy
	
End Type

Function addItem(x2, y2, typeOf2, subTypeOf2)
	item.item = New item
	item\x = x2
	item\y = y2
	item\renderX = x2
	item\renderY = y2
	
	item\typeOf = typeOf2
	item\subTypeOf = subTypeOf2
	
	If item\typeOf = food Then
		item\hpWorth = item\subTypeOf
		If item\subTypeOf = 0 Then
			item\hpWorth = 5
		End If 
	End If
	
	If item\typeOf = loot Then
		item\worth = item\subTypeOf * 10
	End If
	
	item\imx = frame(item\subTypeOf)
	item\imy = frame(3 + item\typeOf) 
End Function

Function updateItem()
	For item.item = Each item
		item\renderX = item\x - cameraX
		item\renderY = item\y - cameraY
		If item\destroy Then
			Delete item
		End If
	Next
End Function

Function drawItem()
	For item.item = Each item
		DrawImageRect(spritesheet, item\renderX, item\renderY, item\imx, item\imy, 16, 16)
	Next
End Function 










