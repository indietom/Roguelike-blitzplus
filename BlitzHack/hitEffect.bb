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
		
		hitEffectText\g = hitEffectText\g + 6
		hitEffectText\b = hitEffectText\b + 6
	
		hitEffectText\y = hitEffectText\y - hitEffectText\speed
		
		hitEffectText\speed = hitEffectText\speed + 0.01
		
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

Function updateHitEffect()
	For hitEffect.hitEffect = Each hitEffect
		hitEffect\lifeTime = hitEffect\lifeTime + 1
		If hitEffect\lifeTime >= hitEffect\maxLifeTime Then
			hitEffect\destroy = True 		
		End If
		
		hitEffect\renderX = hitEffect\x - cameraX
		hitEffect\renderY = hitEffect\y - cameraY
		
		If hitEffect\destroy Then
			Delete hitEffect
		End If
	Next
End Function  		

Function drawHitEffect()
	For hitEffect.hitEffect = Each hitEffect 
		DrawImageRect(spritesheet, hitEffect\renderX, hitEffect\renderY, 528, 1, 16, 16)
	Next 
End Function 

Function addAllHitEffects(x2#, y2#, infoText2$)
	addHitEffectText(x2#+4, y2#, infoText2$)
	addHitEffect(x2#, y2#)	
End Function 











































	
		
		
		
		
		
		































