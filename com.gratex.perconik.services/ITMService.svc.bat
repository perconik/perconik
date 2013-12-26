rem http://perconik.fiit.stuba.sk/ITM/ITMService.svc?singleWsdl

wsimport -keep -s src -Xnocompile ITMService.svc.wsdl -b ITMService.svc.jaxws
