# genetic-algorithm
This project implements a genetic algorithm on small particles in order to find the shortest path to one or several targets, and at the same time, avoid collisions with moving obstacles (they crash otherwise). The particles are sort of small rockets, they can deliver power to accelerate in a chosen direction.

Many thanks to Daniel Shiffman for his amazing [YouTube channel](https://www.youtube.com/user/shiffman). Here are the series of videos that inspired me to do this project : [9: Genetic Algorithms - The Nature of Code](https://www.youtube.com/playlist?list=PLRqwX-V7Uu6bJM3VgzjNV5YxVxUwzALHV).

### Usage

To play around with this project you can go in the file *Main.java* and vary the following parameters :

  * _populationSize_
  * _mutationRate_
  
These are the parameters that impacts the algorithm itself. You can also change the disposition of the obstacles and targets as well as their amount. Finally, you can try to modify the key functions of the algorithm in the file *DNA.java*:

  * _crossover_ : it defines how two organisms will transmit their DNA to their child.
  * _mutate_ : it defines how the DNA of one individual can evolve.

![alt text](https://github.com/vcoppe/genetic-algorithm/blob/master/ga.png "genetic-algorithm")

You are welcome to share any improvements you could have made !
