rem http://perconik.fiit.stuba.sk/UserActivity/ActivitySvc.svc?singleWsdl

wsimport -keep -s src -Xnocompile ActivitySvc.svc.wsdl -b ActivitySvc.svc.jaxws
