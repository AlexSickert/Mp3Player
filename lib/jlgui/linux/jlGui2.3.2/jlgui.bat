rem ################################
rem #  jlGui WIN32 Launch Script   #
rem #                              #
rem #   http://www.javazoom.net    #
rem ################################
rem Modify JAVA_HOME according to your J2SE installation.

set JAVA_HOME=d:\java\jdk1.4.2
set JLGUI_HOME=.
rem Uncomment line below to enable DEBUG level for traces.
rem set LOGIMPL=%JLGUI_HOME%\classes

rem You should not need to modify the script beyond this point.
rem ---------------------------------------------------------------------------------
set JAVALAYER=%JLGUI_HOME%\lib\jl1.0.jar
set MPEGSPI=%JLGUI_HOME%\lib\mp3spi1.9.2.jar
set VORBISSPI=%JLGUI_HOME%\lib\jorbis-0.0.13.jar;%JLGUI_HOME%\lib\jogg-0.0.7.jar;%JLGUI_HOME%\lib\tritonus_share.jar;%JLGUI_HOME%\lib\vorbisspi1.0.1.jar
set JSPEEXSPI=%JLGUI_HOME%\lib\jspeex0.9.3.jar
set BASICPLAYER=%JLGUI_HOME%\lib\basicplayer2.3.jar
set JLGUI=%JLGUI_HOME%\jlgui2.3.2.jar
set LOG=%JLGUI_HOME%\lib\commons-logging-api.jar
set PATH=%JAVA_HOME%\bin;%PATH%
set CLASSPATH=%LOG%;%JLGUI%;%VORBISSPI%;%JAVALAYER%;%MPEGSPI%;%JSPEEXSPI%;%BASICPLAYER%;%LOGIMPL%
set JAVA=%JAVA_HOME%\bin\java
set CMD=%JAVA% -classpath %CLASSPATH% javazoom.jlgui.player.amp.Player
%CMD%
rem ---------------------------------------------------------------------------------