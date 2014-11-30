Function clearColor()
	Color 255, 255, 255
End Function 

Function frame(frame2)
	Local frame3
	frame3 = 1 + frame2 * 16 + frame2
	Return frame3
End Function

Function frameSize(frame2, size)
	Local frame3
	frame3 = frame2 * size + 1 + frame2
	Return frame3
End Function

Function collision(x, y, w, h, x2, y2, w2, h2)
	If y >= y2 + h2 Then Return False 
	If x >= x2 + w2 Then Return False 
	If y + h <= y2 Then Return False
	If x + w <= x2 Then Return False   
 Return True 
End Function  

Function g(cell)
	Local g2
	g2 = 16*cell
	Return g2
End Function 

Function toRoman$(num)

	Local number$ = ""
	
	If num >= 10 Then
		number = "X" + toRoman(num-10)
		Return number
	End If
	If num >= 9 Then
		number = "IX" + toRoman(num-9)
		Return number
	End If
	If num >= 5 And killFunc = False Then
		number = "V" + toRoman(num-5)
		Return number
	End If
	If num >= 4 Then
		number = "IV" + toRoman(num-4)
		Return number
	End If
	If num >= 1 Then
		number = "I" + toRoman(num-1)
		Return number
	End If
	
	Return number$
	
End Function 