;SCP - Containment Breach Mod 
;By MDGAME(MDStudio) Link: http://mdstudio.free.nf/
;original author: Regalis Link: https://github.com/Regalis11/
;Mod author: MorningDemon Link: https://github.com/MorningDemon515/ 

Local InitErrorStr$ = ""
If FileSize("FastText.dll")=0 Then InitErrorStr=InitErrorStr+ "FastText.dll"+Chr(13)+Chr(10)
If FileSize("FastExt.dll")=0 Then InitErrorStr=InitErrorStr+ "FastExt.dll"+Chr(13)+Chr(10)
If FileSize("FreeImage.dll")=0 Then InitErrorStr=InitErrorStr+ "FreeImage.dll"+Chr(13)+Chr(10)

If Len(InitErrorStr)>0 Then
	RuntimeError "The following DLLs were not found in the game directory:"+Chr(13)+Chr(10)+Chr(13)+Chr(10)+InitErrorStr
EndIf


Include "Source code\Main.bb"