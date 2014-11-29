Include "player.bb"
Include "ui.bb"

Type enemy
	Field x
	Field y
	
	Field name$
	
	Field renderX
	Field renderY
	
	Field imx
	Field imy
	
	Field typeOf
	
	Field direction
	
	Field dm
	Field acc
	Field def
	
	Field baseDm
	Field baseAcc
	Field baseDef
	
	Field level
	
	Field worthXp
	
	Field dropLoot
	
	Field chanceOfLoot
	
	Field hurt
	
	Field active
	
	Field lootTypeOf
	Field lootSubTypeOf
	
	Field destroy
End Type

Function updateEnemy()
	For enemy.enemy = Each enemy
	
	Next
End Function

Function drawEnemy()
	For enemy.enemy = Each enemy
	
	Next
End Function 