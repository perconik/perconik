rem http://perconik.fiit.stuba.sk/AstRcs/AstRcsWcfSvc.svc?singleWsdl

wsimport -keep -s src -Xnocompile AstRcsWcfSvc.svc.wsdl -b AstRcsWcfSvc.svc.jaxws
