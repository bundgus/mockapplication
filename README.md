# mockapplication
Automatically exported from code.google.com/p/mockapplication

About Mock Application

Introduction
This mock application is meant to be used as a stand-in for J2EE 1.4 applications during distributed system testing. By eliminating the complexity of a 'real' distributed, Java distributed enterprise application it is possible to isolate and tune other components of a distributed application architecture. The deployment process for this 'mock' application consists of un-deploying the 'real' application, renaming the war file containing the 'mock' application to that of the 'real' application's context path, and deploying the 'mock' application in its place. Any http page requests are serviced with a single, simple page of fixed size. When any requests for JPG, GIF, and PNG images are made, a model image is returned for each type of image. Similarly, request for CSS files are serviced with an actual CSS file.

