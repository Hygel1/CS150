------------------------------------------------------------------------
This is the project README file. Here, you should describe your project.
Tell the reader (someone who does not know anything about this project)
all they need to know. The comments should usually include at least:
------------------------------------------------------------------------

PROJECT TITLE: Project 2
PURPOSE OF PROJECT: DoublingList -- Project 2
VERSION or DATE: 8/29/24
AUTHORS: Sean McLoughlin

problems:
	- I currently have an array index out of bounds error that stops me from adding to some nodes
	- I experienced problems crossing between nodes when trying to push values between nodes
		- I was able to solve this issue by placing moving code from my Node class to my DoublingList class. This helped me achieve a broader scope at all times and think of the list as a full unit rather than individual nodes when necessary
	- there is currently an issue with Node passing in my 'push to front' operation in the remove method

	- I did not implement the NodeInfo class as this was mentioned as something that 'could be helpful' but did not seem like something that matched my concept of the list
	- Instead of nested classes, I decided to make my Node class it's own file as I would rather use getters and setters than acquire extended privileges -- coding this way feels more natural and although I understand how nested classes work and have worked with them before, I can more easily conceptualize my code when the classes are further separated from each other.