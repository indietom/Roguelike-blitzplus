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