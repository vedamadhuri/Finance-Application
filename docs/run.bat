@ECHO OFF
IF "%1"=="start" (
    ECHO start  app name
    start "financeapplication" java -jar  C:\Users\grandhe.s\Desktop\Training_Project\financeapplication\target\financeapplication-0.0.1-SNAPSHOT.jar 
) ELSE IF "%1"=="stop" (

    ECHO stop financeapplication
    TASKKILL /FI "WINDOWTITLE eq financeapplication"
	)
 
pause

