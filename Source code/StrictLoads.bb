; ID: 2975
; Author: RifRaf, further modified by MonocleBios
; Date: 2012-09-11 11:44:22
; Title: Safe Loads (b3d) ;strict loads sounds more appropriate IMO
; Description: Get the missing filename reported

;safe loads for mav trapping media issues




;basic wrapper functions that check to make sure that the file exists before attempting to load it, raises an RTE if it doesn't
;more informative alternative to MAVs outside of debug mode, makes it immiediately obvious whether or not someone is loading resources
;likely to cause more crashes than 'clean' CB, as this prevents anyone from loading any assets that don't exist, regardless if they are ever used
;added zero checks since blitz load functions return zero sometimes even if the filetype exists
Function LoadImage_Strict(file$)
	If FileType(file$)<>1 Then RuntimeError "Image " + file$ + " missing. "
	tmp = LoadImage(file$)
	If tmp = 0 Then RuntimeError "Failed to load Image: " + file$ 
	Return tmp
End Function

Function LoadSound_Strict(file$)
	If FileType(file$)<>1 Then RuntimeError "Sound " + file$ + " not found."
    tmp = LoadSound(file$)
	If tmp = 0 Then RuntimeError "Failed to load Sound:" + file$ 
	Return tmp
End Function

Function LoadMesh_Strict(File$,parent=0)
	If FileType(File$)<>1 Then RuntimeError "3D Mesh " + File$ + " not found."
	tmp = LoadMesh(File$, parent)
	If tmp = 0 Then RuntimeError "Failed to load 3D Mesh: " + File$ 
	Return tmp  
End Function   

Function LoadAnimMesh_Strict(File$,parent=0)
	DebugLog File
	If FileType(File$)<>1 Then RuntimeError "3D Animated Mesh " + File$ + " not found."
	tmp = LoadAnimMesh(File$, parent)
	If tmp = 0 Then RuntimeError "Failed to load 3D Animated Mesh: " + File$ 
	Return tmp
End Function   

;don't use in LoadRMesh, as Reg does this manually there. If you wanna fuck around with the logic in that function, be my guest 
Function LoadTexture_Strict(File$,flags=1)
	If FileType(File$)<>1 Then RuntimeError "Texture " + File$ + " not found."
	tmp = LoadTexture(File$, flags)
	If tmp = 0 Then RuntimeError "Failed to load Texture: " + File$ 
	Return tmp 
End Function   

Function LoadBrush_Strict(file$,flags,u#=1.0,v#=1.0)
	If FileType(file$)<>1 Then RuntimeError "Brush Texture " + file$ + "not found."
	tmp = LoadBrush(file$, flags, u, v)
	If tmp = 0 Then RuntimeError "Failed to load Brush: " + file$ 
	Return tmp 
End Function 

Type Stream
	Field sfx%
	Field chn%
End Type

Const Mode% = 2
Const TwoD% = 8192

Function StreamSound_Strict(file$,volume#=1.0,custommode=Mode)
	If FileType(file$)<>1
		CreateConsoleMsg("Sound " + Chr(34) + file$ + Chr(34) + " not found.")
		If ConsoleOpening
			ConsoleOpen = True
		EndIf
		Return 0
	EndIf
	
	Local st.Stream = New Stream
	
	st\chn = PlayMusic(File)

	If st\chn = -1
		CreateConsoleMsg("Failed to stream Sound (returned -1): " + Chr(34) + file$ + Chr(34))
		If ConsoleOpening
			ConsoleOpen = True
		EndIf
		Return -1
	EndIf
	
	ChannelVolume(st\chn, Volume * 1.0)
	
	Return Handle(st)
End Function

Function StopStream_Strict(streamHandle%)
	Local st.Stream = Object.Stream(streamHandle)
	
	If st = Null Then Return

	If st\chn=0 or st\chn=-1
		CreateConsoleMsg("Failed to stop stream Sound: Return value "+st\chn)
		Return
	EndIf
	
	StopChannel(st\CHN)

	Delete st
End Function

Function IsStreamPlaying_Strict(streamHandle%)
	Local st.Stream = Object.Stream(streamHandle)
	
	If st = Null
		;CreateConsoleMsg("Failed to find stream Sound: Unknown Stream")
		Return
	EndIf
	If st\chn=0 or st\chn=-1
		CreateConsoleMsg("Failed to find stream Sound: Return value "+st\chn)
		Return
	EndIf
	
	Return(ChannelPlaying(st\CHN))	
End Function


;~IDEal Editor Parameters:
;~C#Blitz3D