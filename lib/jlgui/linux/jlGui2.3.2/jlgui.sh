#!/bin/sh
################################
#   jlGui Un*x Launch Script   #
#                              #
#   http://www.javazoom.net    #
################################
# Modify JAVA_HOME according to your J2SE installation.

JAVA_HOME=/usr/java/jdk1.4.2
JLGUI_HOME=.
# Uncomment line below to enable DEBUG level for traces.
# set LOGIMPL=$JLGUI_HOME/classes

# You should not need to modify the script beyond this point.
# ---------------------------------------------------------------------------------
JAVALAYER=$JLGUI_HOME/lib/jl1.0.jar
MPEGSPI=$JLGUI_HOME/lib/mp3spi1.9.2.jar
VORBISSPI=$JLGUI_HOME/lib/jorbis-0.0.13.jar:$JLGUI_HOME/lib/jogg-0.0.7.jar:$JLGUI_HOME/lib/tritonus_share.jar:$JLGUI_HOME/lib/vorbisspi1.0.1.jar
JSPEEXSPI=$JLGUI_HOME/lib/jspeex0.9.3.jar
BASICPLAYER=$JLGUI_HOME/lib/basicplayer2.3.jar
JLGUI=$JLGUI_HOME/jlgui2.3.2.jar
LOG=$JLGUI_HOME/lib/commons-logging-api.jar
PATH=$JAVA_HOME/bin:$PATH
CLASSPATH=$LOG:$JLGUI:$VORBISSPI:$JAVALAYER:$MPEGSPI:$JSPEEXSPI:$BASICPLAYER:$LOGIMPL
JAVA=$JAVA_HOME/bin/java
$JAVA -classpath $CLASSPATH javazoom.jlgui.player.amp.Player
# ---------------------------------------------------------------------------------