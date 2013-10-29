rem http://perconik.fiit.stuba.sk/tagAdm/Wcf/TagProfileWcfSvc.svc?singleWsdl

..\..\metro\bin\wsimport -extension -keep -Xnocompile -p com.gratex.perconik.services.tag -d src TagProfileWcfSvc.svc.xml
