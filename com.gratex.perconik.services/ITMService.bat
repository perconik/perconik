rem http://perconik.fiit.stuba.sk/ITM/ITMService.svc?singleWsdl

..\..\metro\bin\wsimport -extension -keep -Xnocompile -p com.gratex.perconik.services.itm -d src ITMService.svc.xml
