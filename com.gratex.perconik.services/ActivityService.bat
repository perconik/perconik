rem http://perconik.fiit.stuba.sk/UserActivity/ActivitySvc.svc?singleWsdl

..\..\metro\bin\wsimport -extension -keep -Xnocompile -p com.gratex.perconik.services.activity -d src ActivitySvc.svc.xml
