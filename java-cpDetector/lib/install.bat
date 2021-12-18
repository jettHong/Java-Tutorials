set version=1.0.10
set groupId=cpdetector
set artifactId=cpdetector
call mvn install:install-file -Dfile=%artifactId%_%version%.jar -DgroupId=%groupId% -DartifactId=%artifactId% -Dversion=%version% -Dpackaging=jar
pause