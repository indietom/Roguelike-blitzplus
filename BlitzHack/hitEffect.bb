Include "globals.bb"
Include "functions.bb"

Type hitEffectText
	Field x#
	Field y#
	
	Field renderX#
	Field renderY#
	
	Field speed#
	
	Field infoText$
	
	Field r
	Field g
	Field b
	
	Field destroy
End Type

Function addHitEffectText(x2#, y2#, infoText2$)
	hitEffectText.hitEffectText = New hitEffectText
	hitEffectText\x = x2
	hitEffectText\y = y2
	
	hitEffectText\infoText = infoText2
	
	hitEffectText\r = 255
	hitEffectText\g = 0
	hitEffectText\b = 0
End Function

Function drawHitEffectText()
	For hitEffectText.hitEffectText = Each hitEffectText
		Color hitEffectText\r, hitEffectText\g, hitEffectText\b
		Text hitEffectText\renderX, hitEffectText\renderY, hitEffectText\infoText
		clearColor()
	Next 
End Function

Function updateHitEffectText()
	For hitEffectText.hitEffectText = Each hitEffectText 
		If hitEffectText\g >= 250 Then
			hitEffectText\destroy = True 
		End If
		
		hitEffectText\g = hitEffectText\g + 10
		hitEffectText\b = hitEffectText\b + 10
	
		hitEffectText\y = hitEffectText\y - hitEffectText\speed
		
		hitEffectText\speed = hitEffectText\speed + 0.5
		
		hitEffectText\renderX = hitEffectText\x - cameraX
		hitEffectText\renderY = hitEffectText\y - cameraY
		
		If hitEffectText\destroy Then
			Delete hitEffectText
		End If
	Next
End Function	

Type hitEffect
	Field x
	Field y
	
	Field renderX
	Field renderY
	
	Field lifeTime
	Field maxLifeTime
	
	Field destroy
End Type 

Function addHitEffect(x2, y2)
	hitEffect.hitEffect = New hitEffect
	hitEffect\x = x2
	hitEffect\y = y2
	
	hitEffect\maxLifeTime = 16
End Function 

Function updatHitEffect()
	
End Function  	
















































	
		
		
		
		
		
		
































