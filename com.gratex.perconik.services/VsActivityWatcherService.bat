rem http://localhost:7979/VsActivityWatcherService?singleWsdl

wsimport -extension -keep -s src -Xnocompile http://localhost:7979/VsActivityWatcherService?singleWsdl -b VsActivityWatcherService.jaxws
