; SpeedText 2
; Copyright Christian Klaussner
; 
; SpeedText.bb

Const TEXT_ANSI = 0
Const TEXT_UTF8 = 1

Const TEXT_LEFT	= 0
Const TEXT_CENTER	= 1
Const TEXT_RIGHT	= 2

Const TEXT_TOP	= 0
Const TEXT_MIDDLE	= 1
Const TEXT_BOTTOM	= 2

Const TEXT_DEFAULT			= 0
Const TEXT_NONANTIALIASED	= 1
Const TEXT_ANTIALIASED		= 2
Const TEXT_CLEARTYPE		= 3

Const TEXT_WORDWRAP	= 1
Const TEXT_DONTCLIP	= 2

Function InitSpeedText()
    TextInitialize BackBuffer()
End Function

Function DeInitSpeedText()
    TextDeinitialize()
End Function

Function Text(x%,y%,txt$,cx% = TEXT_LEFT,cy% = TEXT_TOP,encoding = TEXT_ANSI)
    TextSetColor ColorRed(),ColorGreen(),ColorBlue()
    TextDraw(x%, y%, txt$, cx%, cy%, encoding%)
End Function

Function SetFont(font%)
    TextSetFont(font%)
End Function

Function FreeFont(font%)
    TextFreeFont(font%)
End Function

Function StringHeight(txt%,encoding = TEXT_ANSI)
    Return TextStringHeight%(text$, encoding%)
End Function

Function StringWidth(txt%,encoding = TEXT_ANSI)
    Return TextStringWidth%(text$, encoding%)
End Function

Function FontWidth()
	Return TextFontWidth()
End Function

Function FontHeight()
	Return TextFontHeight()
End Function


