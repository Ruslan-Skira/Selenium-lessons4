#!/bin/sh
# Variables
#FNAME=@artifact@-@version@
F_NAME="automation-1.0"
F_EXT=".zip"
ROOT_DIR="/opt/trt/tmp/reader-test-jdk9"
SRC=$ROOT_DIR/$F_NAME$F_EXT

# Functions
AssignTime()
{
    eval "$1=`date '+_%Y_%m_%d_%H_%M_%S'`"
}
CopyBackup () 
{
    BACKUP_DIR=$ROOT_DIR/backup
    echo "$BACKUP_DIR"
    if [[ ! -e $BACKUP_DIR ]]; then
	mkdir $BACKUP_DIR
    fi
    
    F_NAME_SUFFIX=""
    AssignTime F_NAME_SUFFIX
    DST=$BACKUP_DIR/$F_NAME$F_NAME_SUFFIX$F_EXT
    echo $SRC
    echo $DST
    cp $SRC $DST
}
MakeBuild()
{    
    BUILD_DIR=$ROOT_DIR/build
    if [ -d $BUILD_DIR ]; then 
	rm -Rf $BUILD_DIR; 
    fi
    #unzip to build dir
   cp $SRC $BUILD_DIR
   #cd /d %buildDir%
   jar -xvf $SRC
   rm -f $BUILD_DIR/$F_NAME$F_EXT
}
CopyBackup
MakeBuild